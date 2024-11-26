package Objekte;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import Main.Handler;

import Main.Game;

public class Ball extends GameObject {

    private Handler handler;                            // Instanz - Referenz auf den Handler der alle Objekte verwaltet

    // Konstruktor
    public Ball(float x, float y, ID id, Handler handler) {
        super(x, y, id);                                        // Ruft Konstruktor der Basisklasse GameObject auf
        this.handler = handler;                                 // Initialisiert den Handler

        velX = -4;                              // Setzt Anfangsgeschwindigkeit des Balls in X-Richtung
        velY = 4;                               // Setzt Anfangsgeschwindigkeit des Balls in Y-Richtung
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);                                // Setzt die Farbe des Balls auf weiß
        g.fillOval((int)x, (int)y, 30, 30);                     // Zeichnet Ball als Kreis mit d=30px
    }

    @Override
    public void tick() {
        x += velX;                          // Aktualisiert die X-Position basierend auf der Geschwindigkeit
        y += velY;                          // Aktualisiert die Y-Position basierend auf der Geschwindigkeit

        // Abprallen am unteren Rand
        if(y >= Game.hoehe - 60) {          // Wenn Ball den unteren Rand erreicht:
            velY -= 4;                      // Ändert Richtung der Geschwindigkeit nach oben
        }
        
        // Abprallen am oberen Rand
        if(y <= 0) {                        // Wenn Ball den oberen Rand erreicht:
            velY += 4;                      // Ändert Richtung der Geschwindigkeit nach unten
        }
    
        // Player Kollision mit dem Ball
    for(int i = 0; i < handler.object.size() ; i++) {                           // Durchläuft alle Spielobjekte
        if(handler.object.get(i).getId() == ID.Player) {                        // Prüft ob Objekt der Spieler ist
            if(getBounds().intersects(handler.object.get(i).getBounds())) {     // Kollisionserkennung
                velX += 4;                                                      // Erhöht Geschwindigkeit nach recht (weg vom Spieler)
            }

        }
    }
    // Gegner Kollision mit dem Ball
    for(int i = 0; i < handler.object.size() ; i++) {                           // Durchläuft alle Spielobjekte
        if(handler.object.get(i).getId() == ID.Gegner) {                        // Prüft ob des Objekt ein Gegner ist
            if(getBounds().intersects(handler.object.get(i).getBounds())) {     // Kollisionserkennung
                velX -= 4;                                                      // Erhöht die Geschwindigkeit nach links (vom Gegner weg)
            }
        }
    }
    

    // Punkt für den Gegner, Ball verlässt Spielfeld links
    if(x < 0) {                                                 // Wenn Ball die linke Seite verlässt:
        x = (Game.breite/2) - (30/2);                           // setzt den Ball zurück in die Mitte
        velX = 4;                                               // setzt Geschwindigkeit Richtung Gegner
        Game.gegnerPoint++;                                     // Erhöht Punktzahl des Gegners
    }

    // Ball über rechte Seite, Spieler erziehlt einen Punkt
    if(x > Game.breite - 20) {                                  // Wenn Ball die rechte Seite verlässt:
        Game.playerPoint++;                                     // Erhöht Punktzahl des Spielers
        x = (Game.breite/2) - (30/2);                           // setzt Ball zurück in die Mitte
        velX = -4;                                              // setzt Geschwindigkeit Richtung Spieler
    }

    x += velX;              // Aktualisiert die X-Position erneut um Bewegung zu vervollständigen
    y += velY;              // Aktualisiert die Y-Position erneut
}

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 30, 30);  // Gibt ein Begrenzungsdreieck zurück das Position und Größe des Balls beschreibt
    }
    

}

