package src.Objekte;                                // Definiert das Paket, zu dem diese Klasse gehört

import java.awt.Color;                          // Ermöglicht Definition von Farben
import java.awt.Graphics;                       // Bietet Methoden zum Zeichnen von 2D-Grafiken
import java.awt.Rectangle;                      // Definiert Begrenzungsrechtecke

import src.Eingaben.KeyHandler;
import src.Main.Game;

public class Player extends GameObject {

    // Variablen
    private final float acc = 1f;               // Beschleunigungsrate für die Bewegung
    private final float dcc = 0.3f;             // Verzögerungsrate / Gleiteffekt des Players beeinflussen

    // Instanz
    public KeyHandler keyHandler;               // Verweist auf den KeyHandler zur Abfrage der Tastatureingaben

    // Konstruktor
    public Player(float x, float y, ID id, KeyHandler keyHandler) {
        super(x, y, id);                                                    // Ruft den Konstruktor der Basisklasse GameObject auf
        this.keyHandler = keyHandler;                                       // Initialisiert den KeyHandler
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);                       // legt Farbe des Spielers fest
        g.fillRect((int)x, (int)y, 15, 130);            // legt Größe des Spielers fest

    }

    @Override
    public void tick() {
        y += velY;                              // Aktualisiert Position des Spielers basierend auf velY

        // Bewegung nach oben
        if(keyHandler.key[0] == true) {                             // Prüft ob Taste "W" gedrückt wird (== true ist optional)
            if (y > 0) {                                            // Verhindert, dass Spieler oberen Rand verlässt
                velY -= acc;                                        // Erhöht Geschwindigkeit nach oben
            }
        } else if (keyHandler.key[1]) {                             // Prüft ob Taste "S" gedrückt wird
            if (y - 130 < Game.hoehe) {                             // Verhindert, dass Spieler unteren Rand verlässt
                velY += acc;                                        // Erhöht Geschwindigkeit nach unten
            }
        } else if (!keyHandler.key[0] && !keyHandler.key[1]) {
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
        return value;                                                   // Gibt die begrenzte Geschwindikeit zurück
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,15,130);         // Gibt ein Rechteck zurück, das die Begrenzung des Spielers beschreibt
    }

}
