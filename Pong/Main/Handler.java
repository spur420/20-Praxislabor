package Main;
import java.awt.Graphics;
import java.util.ArrayList;

import Objekte.GameObject;

public class Handler {
    public ArrayList<GameObject> object = new ArrayList<GameObject>();

    public void addObject (GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    public void render(Graphics g) {
        for (int i = 0 ; i < object.size(); i++) {
            this.object.get(i).render(g);
        }
    }

    public void tick() {
        for (int i = 0 ; i < object.size(); i++) {
            this.object.get(i).tick();
        }
    }

}
