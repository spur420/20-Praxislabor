package Eingaben;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {                // Keylistener-Interface für Tastatureingaben

    public boolean key[] = new boolean[2];                      // Array für die beiden Richtungen oben und unten

    @Override                                                   // 3 vorgefertigte abstrakte Methoden
    public void keyPressed(KeyEvent e) {                        // Taste drücken
        if(e.getKeyCode() == KeyEvent.VK_W) {                   // e ist die Parametervariable & VK_W ist virtual key W (Taste W)
            key[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            key[1] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {                       // Taste loslassen
        if (e.getKeyCode() == KeyEvent.VK_W) {
            key[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            key[1] = false;
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {                          // Taste antippen
        
    }
    

}
