import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet ps;
    private BufferedImage player_sheet = null;

    public BufferedImage[] player = new BufferedImage[1];

    public Texture() {
        BufferedImageLoader loader = new BufferedImageLoader();

        try{
            player_sheet = loader.loadImage("/goggy.png");
        } catch(Exception e) {
            e.printStackTrace();
        }
        ps = new SpriteSheet(player_sheet);
        getTextures();
    }

    private void getTextures() {
        player[0] = ps.grabImage(1, 1, 120, 120);
    }
}
