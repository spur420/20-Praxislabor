package GameObject;
import java.awt.Color;
import java.awt.Graphics;





public class Gegner extends GameObject{

    public Gegner(float x, float y, ID id) {
        super(x, y, id);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 15, 130);
    }

    @Override
    public void tick() {
        
    }

}
