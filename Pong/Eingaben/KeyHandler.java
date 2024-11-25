package Eingaben;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean key[] = new boolean[2];

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) {
            key[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            key[1] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            key[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            key[1] = false;
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    

}
