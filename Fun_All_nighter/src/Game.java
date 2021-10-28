import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Game extends Canvas implements Runnable{

    // Static variables because they should be visible
    // anywhere.
    public static final int HEIGHT = 280;
    public static final int WIDTH = HEIGHT * 37/20;
    public static float DeltaTime = 0.f;
    public static Helper helper = new Helper();
    public static Texture tex = new Texture();

    // Private because they should only be seen by Game
    // methods.
    private boolean running = true;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static Texture getInstance() {
        return tex;
    }

    // Creates a new thread that starts.
    public void start() {
        running = true;
        new Thread(this).start();
    }

    // Exits the program. Only should be used
    // in other game methods. Methods in other
    // classes should not be able to call this.
    public void stop() {
        running = false;
    }

    // Game loop that thread calls because
    // Game implements runnable.
    public void run() {
        long lastime = System.nanoTime();
        double AmountOfTicks = 60;
        double ns = 1000000000 / AmountOfTicks;
        double delta = 0;
        int frames = 0;
        double time = System.currentTimeMillis();

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastime) / ns;
            lastime = now;
            DeltaTime = (float)delta;

            if(delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
                if(System.currentTimeMillis() - time >= 1000) {
                    //System.out.println("fps:" + frames);
                    time += 1000;
                    frames = 0;
                }
            }
        }
    }

    // Called every frame.
    public void tick() {
        for(int i=0; i < helper.objects.size(); i++) {
            helper.objects.get(i).tick();
        }
    }

    // Renders every frame.
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            requestFocus();
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(hints);
        g.drawImage(image, 0,0,null);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);

        // Draw Graphics here
        for(int i=0; i < helper.objects.size(); i++) {
            helper.objects.get(i).render(g);
        }

        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        Game game = new Game();
        game.setMinimumSize(new Dimension(WIDTH*2, HEIGHT * 2));
        game.setMaximumSize(new Dimension(WIDTH*2, HEIGHT * 2));
        game.setPreferredSize(new Dimension(WIDTH*2, HEIGHT * 2));

        KeyHandler keys = new KeyHandler(helper, game);

        game.addKeyListener(keys);

        JFrame frame = new JFrame("PogChamp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //--Include game objects below here-----
        helper.add(new Player(ID.Player, new Vec2(WIDTH, HEIGHT), 40, 70, Color.BLACK));

        //-------- Above here -----------------
        game.start();
    }

    public static float clamp(float var, float min, float max) {
        if(var > max)
            return max;
        else if (var < min)
            return min;
        else
            return var;
    }
}
