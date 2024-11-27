package src.Objekte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import src.Main.Game;
import src.Eingaben.KeyHandler;


public class Player2 extends GameObject{

    // Variablen
    private final float acc = 1f;               // Beschleunigungsrate für die Bewegung
    private final float dcc = 0.3f;             // Verzögerungsrate / Gleiteffekt des Players beeinflussen

    private KeyHandler keyHandler;

    // Konstruktor
    public Player2(float x, float y, ID id, KeyHandler keyHandler) {
        super(x, y, id);
        this.keyHandler = keyHandler;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);                        // Setzt die Farbe des Player2
        g.fillRect((int)x, (int)y, 15, 130);            // Legt Form des Player2 fest
    }

    @Override
    public void tick() {
        y += velY;                              // Aktualisiert Position des Spielers basierend auf velY

        // Bewegung nach oben
        if(keyHandler.key2[0] == true) {                             // Prüft ob Taste "W" gedrückt wird (== true ist optional)
            if (y > 0) {                                            // Verhindert, dass Spieler oberen Rand verlässt
                velY -= acc;                                        // Erhöht Geschwindigkeit nach oben
            } else {
                velY = 0;
            }
        } else if (keyHandler.key2[1]) {                             // Prüft ob Taste "S" gedrückt wird
            if (y + 180 < Game.hoehe) {                             // Verhindert, dass Spieler unteren Rand verlässt
                velY += acc;                                        // Erhöht Geschwindigkeit nach unten
            } else {
                velY = 0;
            }
        } else if (!keyHandler.key2[0] && !keyHandler.key2[1]) {
            if(velY > 0) {                                          // Wenn sich der Spieler nach unten bewegt:
                velY -= dcc;                                        // Reduziert die Geschwindigkeit nach unten
            } else if(velY < 0) {                                   // Wenn sich der Spieler nach oben bewegt:
                velY += dcc;                                        // Reduziert die Geschwindigkeit nach oben
            }
        }
        velY = getMinMaxVel(velY, -5, 5);                           // Begrenzung Bewegungsgeschwindigkeit von -5 bis 5 px
    }

    // Methode zur Begrenzung der Geschwindigkeit
    private float getMinMaxVel(float value, float min, float max) {
        if(value >= max) {                                              // Wenn maximale Geschwindigkeit überschritten wird
            value = max;                                                // Setze sie auf das Maximum
        }

        if(value <= min) {                                              // Wenn maximale Geschwindigkeit unterschritten wird
            value = min;                                                // Setze sie auf das Minimum
        }
        return value;                                                   // Gibt die begrenzte Geschwindigkeit zurück
    }

    // Gibt Rechteck zurück, dass die Position und Größe des Gegners beschreibt
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,15,130);
    }

}
