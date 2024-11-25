package Objekte;
import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject {

    public Ball(float x, float y, ID id) {
        super(x, y, id);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int)x, (int)y, 30, 30);    
    }

    @Override
    public void tick() {
        
    }
    

}
