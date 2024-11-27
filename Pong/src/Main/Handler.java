package src.Main;
import java.awt.Graphics;
import java.util.ArrayList;

import src.Objekte.GameObject;

public class Handler {
    
    // Liste zur Speicherung aller Spielobjekte
    public ArrayList<GameObject> object = new ArrayList<GameObject>();

    // Fügt der Liste ein Spielobjekt hinzu
    public void addObject (GameObject object) {
        this.object.add(object);
    }

    // Entfernt ein Spielobjekt aus der Liste
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    // Zeichnet alle Spielobjekte auf den Bildschirm
    public void render(Graphics g) {
        for (int i = 0 ; i < object.size(); i++) {
            this.object.get(i).render(g);
        }
    }

    // Aktualisiert die Logik aller Spielobjekte
    public void tick() {
        for (int i = 0 ; i < object.size(); i++) {
            this.object.get(i).tick();
        }
    }

}
