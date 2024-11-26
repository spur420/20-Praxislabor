package Objekte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Game;
import Main.Handler;





public class Gegner extends GameObject{

    // Instanz
    private Handler handler;

    public Gegner(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 15, 130);
    }

    @Override
    public void tick() {                                                        // automatische Gegnerbewegung
        y += velY;

        for (int i = 0; i < handler.object.size(); i++) {
            if ( handler.object.get(i).getId() == ID.Ball) {
                if (handler.object.get(i).getY() == this.getY()) {

                } else if (handler.object.get(i).getY() > this.getY()) {        //Wenn Ball unterhalb des Gegners, Gegner down
                    if(y + 160 > Game.hoehe) {                                  // Gegner geht nicht über den unteren Rand hinaus
                        velY = 0;
                    } else {
                        velY += 2;
                    }
                    velY += 2;                                                  //Geschwindigkeit Gegner
                } else if (handler.object.get(i).getY() < this.getY()) {        //Wenn Ball oberhalb des Gegners, Gegner up
                    if(y < 0) {
                        velY = 0;
                    } else {
                        velY -= 2;
                    }
                    
                }
            }
        }
        velY = getMinMaxVel(velY, -4, 4);
    }

    private float getMinMaxVel(float value, float min, float max) {
        if (value >= max) {
            value = max;
        }
        if (value <= min) {
            value = min;
        }
        return value;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,15,130);
    }

}
