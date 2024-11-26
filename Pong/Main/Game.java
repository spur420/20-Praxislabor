package Main;                           // Definiert das Paket, zu dem diese Klasse gehört
import java.awt.Canvas;                 // Importiert die Zeichenfläche Canvas
import java.awt.Color;                  // Ermöglicht Verwendung von Farben
import java.awt.Font;                   // Ermöglicht Nutzung von Schriftarten
import java.awt.Graphics;               // Zum Zeichnen von 2D-Elementen
import java.awt.image.BufferStrategy;   // Vermeidet Flackern

import Eingaben.KeyHandler;             // Importieren der verschiedenen Klassen
import Objekte.Ball;
import Objekte.Gegner;
import Objekte.ID;
import Objekte.Player;

public class Game extends Canvas implements Runnable {              // wird dadurch zur Komponente und lässt sich einem Frame zuordnen
                                                                    //Runnable enthält Methode public void run()
    //Variablen
    public static int breite =1000, hoehe = 700;    // statische Variable für Breite und Höhe
    private String title = "Pong Tutorial";         // Titel des Spielfensters
    private Thread thread;                          // Damit mehrere Threads parallel laufen
    private boolean isRunning = false;              // Kontrolle ob das Spiel läuft
    public static int playerPoint = 0;              // Punktestand Spieler
    public static int gegnerPoint = 0;              // Punktestand Gegner

    // Instanzen
    private Handler handler;                                               // Verwaltet Spielobjekte
    private KeyHandler keyHandler;                                         // Verarbeitet Tastatureingaben

    private static final long serialVersionUID = -4594745848274477315L;     // Serialisierung von Objekten, nötig in Canvas

    // Konstruktor
    public Game() {

        init();                                                     // initialisiert die notwendigen Objekte
        Window window = new Window(breite, hoehe, title, this);     // Erstellt das Spielfenster
        start();                                                    // startet das Spiel
    }

    private void init() {                       // Initialisierungsmethode
        handler = new Handler();                // erstellt Handler Instanz
        keyHandler = new KeyHandler();          // erstellt KeyHandler Instanz

        this.addKeyListener(keyHandler);        // fügt KeyHandler hinzu, um Tastatureingaben zu registrieren

        handler.addObject(new Player(30, hoehe/2 - 65, ID.Player, keyHandler));                 // Erstellt Spieler
        handler.addObject(new Gegner(breite - 50, (hoehe/2) - 65, ID.Gegner, handler));           // Erstellt Gegner
        handler.addObject(new Ball((breite/2) - 15 - 16, (hoehe/2) - 15, ID.Ball, handler));      // Erstellt Ball

    }

    //Methoden
    private synchronized void start() {         // startet den Spiel Thread
        if(isRunning) {                         // verhindert doppeltes Starten
            return;
        }
        
            thread = new Thread(this);          // erstellt einen neuen Thread und übergibt das Objekt
            thread.start();                     // startet den Thread und ruft die Methode run() auf
            isRunning = true;                   // setzt den Status auf läuft
        

    }
    private synchronized void stop() {          // stoppt den Spiel Thread
        if(!isRunning) {                        // verhindert doppeltes Stoppen
            return;
        }
        try {
        thread.join();                          // wartet bis der Thread vollständig beendet ist
        } catch (InterruptedException e) {      // Fehlerbehandlung für Unterbrechungen
            e.printStackTrace();
        }
        
    }
    // Hintergrund und Grafik
    private void render() {                                     // holt aktuelle Buffer Strategie
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {                                        // erstellt wenn noch keine existiert
            this.createBufferStrategy(3);                       // erstellt Buffer-Strategie mit 3 Puffern
            return;                                             // verlässt die Methode
        }

        // Graphics um 2D Dinge darzustellen
        Graphics g = bs.getDrawGraphics();                      // holt das Grafikobjekt zum Zeichnen

        g.setColor(Color.BLACK);                                // setzt Zeichenfarbe auf schwarz
        g.fillRect(0, 0, breite, hoehe);                        // füllt das Spielfenster mit schwarzem Rechteck

        // Zeichnet die Mittellinie
        int y = 0;                                              // Y-Position für die Liniensegmente
        g.setColor(Color.WHITE);                                // setzt die Farbe auf weiß
        for (int i = 0; i < 19 ; i++ ) {                        // zeichnet 19 Segmente
            g.fillRect(breite/2 - 13, y, 26, 26);               // jedes Segment ist ein kleines Rechteck
            y += 40;                                            // Verschiebt Y-Position für das nächste Element
        }

        // Zeichnet die Punktestände
        g.setFont(new Font("Arial", Font.PLAIN, 90));                                           // Setzt Schriftfarbe und Größe
        g.setColor(Color.WHITE);                                                                // Setzt Farbe auf weiß
        g.drawString(String.valueOf(playerPoint), Game.breite / 4 - 30, 90);                    // zeichnet Spielerpunkte
        g.drawString(String.valueOf(gegnerPoint), Game.breite - (Game.breite/4) - 30, 90);      // zeichnet Gegnerpunkte

        handler.render(g);          // Zeichnet alle Spielobjekte
        bs.show();                  // zeigt Pufferinhalt an
        g.dispose();                // gibt Grafikressourcen wieder frei
    }
    // Bewegung der Objekte (Aktualisierung)
    private void tick() {
        handler.tick();                         // aktualisiert den Zustand aller Spielobjekte
    }
    
    @Override
    public void run() {                             // Hauptspiel Schleife (game loop)
    this.requestFocus();                            // Fordert Fokus für Spielfenster an
    long lastTime = System.nanoTime();              // holt aktuelle Zeit in Nanosekunden
    double fps = 60.0;                              // Ziel Framerate
    double ns = 1000000000 / fps;                   // Zeit in Nanosekunden pro Frame
    double delta = 0;                               // Zähler für verstrichene Zeit
    long timer = System.currentTimeMillis();        // Timer für Sekundenanzeige
    int frames = 0;                                 // zählt die generierten Frames
    
    while (isRunning) {                         // Solange das Spiel läuft                
        long now = System.nanoTime();           // Holt die aktuelle Zeit
        delta += (now - lastTime) / ns;         // Addiert die vergangene Zeit
        lastTime = now;                         // aktualisiert die Zeit

        while (delta >= 1) {                    // Wenn ein Frame verarbeitet werden sollte:
            tick();                             // aktualisiert den Spielzustand
            delta--;                            // reduziert den Delta-Wert
        }

        render();           // zeichnet das Spiel
        frames++;           // erhöht den Frame-Zähler

        if (System.currentTimeMillis() - timer > 1000) {        // nach einer Sekunde:
            timer += 1000;                                      // Aktualisiert den Timer
            frames = 0;                                         // setzt den Frame-Zähler zurück
        }
    }
    stop();                 // stoppt das Spiel sobald isRunning false wird
    }

    // main Methode
    public static void main(String[] args) {
        new Game();                                     // erstellt eine neue Instanz des Spiels und startet es
    }
}

