import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import GameObject.Ball;
import GameObject.Gegner;
import GameObject.ID;
import GameObject.Player;

public class Game extends Canvas implements Runnable {

    //Variablen
    public static int breite = 800, hoehe = 600;
    private String title = "Pong Tutorial";
    private Thread thread;
    private boolean isRunning = false;

    // Instanzen
    private Handler handler;

    private static final long serialVersionUID = -4594745848274477315L;

    // Konstruktor
    public Game() {
        init();
        Window window = new Window(breite, hoehe, title, this);
        start();
    }

    private void init() {
        handler = new Handler();

        handler.addObject(new Player(30,hoehe/2 - 65, ID.Player));

        handler.addObject(new Gegner(breite - 50, (hoehe/2) - 65, ID.Gegner));

        handler.addObject(new Ball((breite/2) - 15 - 16, (hoehe/2) - 15, ID.Ball));

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

        handler.render(g);

        bs.show();
        g.dispose();
    }
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

