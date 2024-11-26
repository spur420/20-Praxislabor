package Objekte;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Eingaben.KeyHandler;
import Main.Game;

public class Player extends GameObject {

    //Variablen
    private final float acc = 1f;
    private final float dcc = 0.3f;                                     // Gleiteffekt des Players beeinflussen

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
        if(keyHandler.key[0] == true) {                                 // == true ist optional
            if (y > 0) {
                velY -= acc;
            }
        } else if (keyHandler.key[1]) {
            if (y - 130 < Game.hoehe) {
                velY += acc;
            }
        } else if (!keyHandler.key[0] && !keyHandler.key[1]) {
            if(velY > 0) {
                velY -= dcc;
            } else if(velY < 0) {
                velY += dcc;
            }
        }
        velY = getMinMaxVel(velY, -5, 5);                           // Geschwindigkeit der Bewegung, maximal 5 px
    }
    private float getMinMaxVel(float value, float min, float max) {
        if(value >= max) {
            value = max;
        }
        if(value <= min) {
            value = min;
        }
        return value;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,15,130);
    }

}
