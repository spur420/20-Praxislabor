package Main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import Eingaben.KeyHandler;
import Objekte.Ball;
import Objekte.Gegner;
import Objekte.ID;
import Objekte.Player;

public class Game extends Canvas implements Runnable {                                  // wird dadurch zur Komponente und lässt sich einem Frame zuordnen
                                                                                        //Runnable enthält Methode public void run()
    //Variablen
    public static int breite =1000, hoehe = 700;
    private String title = "Pong Tutorial";
    private Thread thread;
    private boolean isRunning = false;
    public static int playerPoint = 0;
    public static int gegnerPoint = 0;

    // Instanzen
    private Handler handler;
    private KeyHandler keyHandler;                                                      // Objekt keyHandler verweist auf die Klasse KeyHandler

    private static final long serialVersionUID = -4594745848274477315L;

    // Konstruktor
    public Game() {
        init();
        Window window = new Window(breite, hoehe, title, this);
        start();
    }

    private void init() {                                                               // Initialisierung
        handler = new Handler();
        keyHandler = new KeyHandler();

        this.addKeyListener(keyHandler);                                                // addKeyListener aus der Klasse Canvas

        handler.addObject(new Player(30, hoehe/2 - 65, ID.Player, keyHandler));

        handler.addObject(new Gegner(breite - 50, (hoehe/2) - 65, ID.Gegner, handler));

        handler.addObject(new Ball((breite/2) - 15 - 16, (hoehe/2) - 15, ID.Ball, handler));

    }
    //Methoden
    private synchronized void start() {
        if(isRunning) {
            return;
        }
        
            thread = new Thread(this);
            thread.start();
            isRunning = true;
        

    }
    private synchronized void stop() {
        if(!isRunning) {
            return;
        }
        try {
        thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    //Hintergrund
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        //Graphics um 2D Dinge darzustellen
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, breite, hoehe);

        // Mittellinie
        int y = 0;
        g.setColor(Color.WHITE);
        for (int i = 0; i < 19 ; i++ ) {
            g.fillRect(breite/2 - 13, y, 26, 26);

            y += 40;

        }

        // Punkte
        g.setFont(new Font("Arial", Font.PLAIN, 90));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(playerPoint), Game.breite / 4 - 30, 90);
        g.drawString(String.valueOf(gegnerPoint), Game.breite - (Game.breite/4) - 30, 90);

        handler.render(g);

        bs.show();
        g.dispose();
    }
    // Bewegung der Objekte
    private void tick() {
        handler.tick();

    }
    
    @Override
    public void run() {
        // game loop
    this.requestFocus();
    long lastTime = System.nanoTime();
    double fps = 60.0;
    double ns = 1000000000 / fps;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    
    while (isRunning) {
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;
        while (delta >= 1) {
            tick();
            delta--;

        }
        render();
        frames++;

        if (System.currentTimeMillis() - timer > 1000) {
            timer += 1000;
            frames = 0;
        }
    }
    stop();
    }
    // main Methode
    public static void main(String[] args) {
        new Game();
    }
}

