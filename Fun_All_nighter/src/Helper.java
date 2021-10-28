import java.util.ArrayList;

public class Helper {

    public ArrayList<GameObject> objects;

    public Helper() {
        objects = new ArrayList<>();
    }

    public void add(GameObject o) {
        objects.add(o);
    }

    public void remove(GameObject o) {
        objects.remove(o);
    }
}
