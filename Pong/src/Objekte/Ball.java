package src.Objekte;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import src.Main.Handler;

import src.Main.Game;
import src.Main.Game2;

public class Ball extends GameObject {

    private Handler handler;                            // Instanz - Referenz auf den Handler der alle Objekte verwaltet
    private Game game;
    private Game2 game2;

    // Konstruktor
    public Ball(float x, float y, ID id, Handler handler, Game game) {
        super(x, y, id);                                        // Ruft Konstruktor der Basisklasse GameObject auf
        this.handler = handler;                                 // Initialisiert den Handler
        this.game = game;
        this.game2 = null;
        velX = -4;                              // Setzt Anfangsgeschwindigkeit des Balls in X-Richtung
        velY = 4;                               // Setzt Anfangsgeschwindigkeit des Balls in Y-Richtung
    }
    public Ball(float x, float y, ID id, Handler handler, Game2 game2) {
        super(x, y, id);                                        // Ruft Konstruktor der Basisklasse GameObject auf
        this.handler = handler;                                 // Initialisiert den Handler
        this.game2 = game2;
        this.game = null;
        velX = -4;                              // Setzt Anfangsgeschwindigkeit des Balls in X-Richtung
        velY = 4;                               // Setzt Anfangsgeschwindigkeit des Balls in Y-Richtung
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);                                // Setzt die Farbe des Balls auf weiß
        g.fillOval((int)x, (int)y, 30, 30);                     // Zeichnet Ball als Kreis mit d=30px
    }

    @Override
    public void tick() {
        x += velX;                          // Aktualisiert die X-Position basierend auf der Geschwindigkeit
        y += velY;                          // Aktualisiert die Y-Position basierend auf der Geschwindigkeit

        // Abprallen am unteren Rand
        if(y >= Game.hoehe - 80) {          // Wenn Ball den unteren Rand erreicht:
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

        // Spieler 2 Kollision mit dem Ball
        if(handler.object.get(i).getId() == ID.Player2) {
            if(getBounds().intersects(handler.object.get(i).getBounds())) {
                velX -=4;
            }
        }
    }
    

    // Punkt für den Gegner, Ball verlässt Spielfeld links
    if ( game != null) {
        if(x <= 0) {                                                 // Wenn Ball die linke Seite verlässt:
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
}

    // Punkt für Player 2, Ball verlässt Spielfeld links
    if ( game2 != null) {
        if(x <= 0) {                                                 // Wenn Ball die linke Seite verlässt:
            x = (Game2.breite/2) - (30/2);                           // setzt den Ball zurück in die Mitte
            velX = 4;                                               // setzt Geschwindigkeit Richtung Gegner
            Game2.player2Point++;                                     // Erhöht Punktzahl des Gegners
    }

    // Ball über rechte Seite, Spieler erziehlt einen Punkt
        if(x > Game2.breite - 20) {                                  // Wenn Ball die rechte Seite verlässt:
            Game2.playerPoint++;                                     // Erhöht Punktzahl des Spielers
            x = (Game2.breite/2) - (30/2);                           // setzt Ball zurück in die Mitte
            velX = -4;                                              // setzt Geschwindigkeit Richtung Spieler
    }
}

}

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 30, 30);  // Gibt ein Begrenzungsdreieck zurück das Position und Größe des Balls beschreibt
    }
    

}

