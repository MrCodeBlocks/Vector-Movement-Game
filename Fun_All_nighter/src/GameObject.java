import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class GameObject {

    public Vec2 position;
    public ID id;
    public boolean right, left, up, down, rr, rl;

    public GameObject(ID id, Vec2 p) {
        position = p;
        this.id = id;
        right = false;
        left = false;
        up = false;
        down = false;
        rr = false;
        rl = false;
    }

    public abstract void tick();
    public abstract void render(Graphics2D g);
    public abstract Rectangle GetBounds();
}
