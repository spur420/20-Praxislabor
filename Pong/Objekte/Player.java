package Objekte;


import java.awt.Color;
import java.awt.Graphics;

import Eingaben.KeyHandler;
import Main.Game;

public class Player extends GameObject {

    //Variablen
    private final float acc = 1f;
    private final float dcc = 0.5f;

    //Instanz
    public KeyHandler keyHandler;

    public Player(float x, float y, ID id, KeyHandler keyHandler) {
        super(x, y, id);
        this.keyHandler = keyHandler;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 15, 130);

    }

    @Override
    public void tick() {
        y += velY;

        //nach oben
        if(keyHandler.key[0]) {
            if (y > 0) {
                velY -= acc;
            }
        } else if (keyHandler.key[1]) {
            if (y + 130 < Game.hoehe) {
                velY += acc;
            }
        }
        
    }

}
