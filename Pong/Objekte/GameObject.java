package Objekte;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    // Eigenschaften des Spielobjekts
    protected float x, y, velX, velY;                   // Position des Spielobjekts (x,y) Geschwindigkeit in x- und y-Richtung
    protected ID id;                                    // Typ des Objekts

    // Konstruktor
    public GameObject(float x, float y, ID id) {
        setX(x);
        setY(y);
        setId(id);
    }

    // Abstrakte Methoden die erbende Klassen implementieren
    public abstract void render(Graphics g);
    public abstract void tick();
    public abstract Rectangle getBounds();

    // Setter und Getter
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

}
