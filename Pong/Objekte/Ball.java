package Objekte;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import Main.Handler;

import Main.Game;

public class Ball extends GameObject {

    private Handler handler;                            // Instanz

    public Ball(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = -4;
        velY = 4;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int)x, (int)y, 30, 30);    
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y >= Game.hoehe - 60) {                          // Abprallen am unteren Rand
            velY -= 4;
        }
        
        if(y <= 0) {                                        // Abprallen am oberen Rand
            velY += 4;
        }

    for(int i = 0; i < handler.object.size() ; i++) {        // Player Kollision mit dem Ball
        if(handler.object.get(i).getId() == ID.Player) {
            if(getBounds().intersects(handler.object.get(i).getBounds())) {
                velX += 4;
            }

        }
    }
    for(int i = 0; i < handler.object.size() ; i++) {
        if(handler.object.get(i).getId() == ID.Gegner) {
            if(getBounds().intersects(handler.object.get(i).getBounds())) {
                velX -= 4;
            }
        }
    }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 30, 30);
    }
    

}
