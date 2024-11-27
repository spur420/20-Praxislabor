package src.Objekte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import src.Main.Game;
import src.Main.Handler;





public class Gegner extends GameObject{

    // Instanzvariable des Handlers der alle Spielobjekte verwaltet
    private Handler handler;

    // Konstruktor
    public Gegner(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);                        // Setzt die Farbe des Gegners
        g.fillRect((int)x, (int)y, 15, 130);            // Legt Form des Gegners fest
    }

    @Override
    public void tick() {                        // automatische Gegnerbewegung
        y += velY;                              // aktualisiert Y-Position basierend auf der vertikalen Geschwindigkeit

        for (int i = 0; i < handler.object.size(); i++) {
            if ( handler.object.get(i).getId() == ID.Ball) {                    // Prüft ob das aktuelle Objekt ein Ball ist
                if (handler.object.get(i).getY() == this.getY()) {              /* Wenn Ball auf der selben Position wie der Gegner
                                                                                    keine Aktion erforderlich */

                } else if (handler.object.get(i).getY() > this.getY()) {        //Wenn Ball unterhalb des Gegners, Gegner down
                    if(y + 180 > Game.hoehe) {                                  // Gegner geht nicht über den unteren Rand hinaus
                        velY = 0;                                               // Geschwindigkeit wird auf 0 gesetzt
                    } else {
                        velY += 2;                                              // Gegner bewegt sich nach unten
                    }
                    velY += 2;                                                 //Geschwindigkeit Gegner
                } else if (handler.object.get(i).getY() < this.getY()) {        //Wenn Ball oberhalb des Gegners, Gegner up
                    if(y < 0) {
                        velY = 0;                                               // Geschwindigkeit wird auf 0 gesetzt
                    } else {
                        velY -= 2;                                              // Gegner bewegt sich nach oben
                    }
                    
                }
            }
        }
        velY = getMinMaxVel(velY, -4, 4);               // Begrenzung der Geschwindigkeit auf einen Bereich
    }

    private float getMinMaxVel(float value, float min, float max) {
        if (value >= max) {                     // Wenn der Wert die obere Grenze überschreitet:
            value = max;                        // Setzt ihn auf die maximale Geschwindigkeit
        }
        if (value <= min) {                     // Wenn der Wert die untere Grenze überschreitet:
            value = min;                        // Setzt ihn auf die minimale Geschwindigkeit
        }
        return value;                           // gibt begrenzte Geschwindigkeit zurück
    }

    // Gibt Rechteck zurück, dass die Position und Größe des Gegners beschreibt
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,15,130);
    }

}
