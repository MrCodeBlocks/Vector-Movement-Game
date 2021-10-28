import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Handler;

public class KeyHandler extends KeyAdapter {

    private Helper helper;
    private Game game;

    public KeyHandler(Helper helper, Game game) {
        this.helper = helper;
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i=0; i< helper.objects.size(); i++) {
            GameObject temp = helper.objects.get(i);
            if(temp.id == ID.Player) {
                if(key == KeyEvent.VK_W) {
                    temp.up = true;
                }
                if(key == KeyEvent.VK_D) {
                    temp.rr = true;
                }
                if(key == KeyEvent.VK_A) {
                    temp.rl = true;
                }
                if(key == KeyEvent.VK_ESCAPE) {
                    System.exit(1);
                }
                if(key == KeyEvent.VK_S) {
                    temp.down = true;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i=0; i< helper.objects.size(); i++) {
            GameObject temp = helper.objects.get(i);
            if(temp.id == ID.Player) {
                if(key == KeyEvent.VK_W) {
                    temp.up = false;
                }
                if(key == KeyEvent.VK_D) {
                    temp.rr = false;
                }
                if(key == KeyEvent.VK_A) {
                    temp.rl = false;
                }
                if(key == KeyEvent.VK_S) {
                    temp.down = false;
                }
            }
        }
    }
}
