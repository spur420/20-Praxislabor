package Eingaben;                           // definiert Paket zu dem diese Klasse gehört
import java.awt.event.KeyEvent;             // ermöglicht Zugriff auf Informationen über Tastendrücke
import java.awt.event.KeyListener;          // implementierung des KeyListeners für Tastatureingaben

public class KeyHandler implements KeyListener {                // Keylistener-Interface für Tastatureingaben

    public boolean key[] = new boolean[2];                      // Array für die beiden Richtungen oben und unten

    @Override                                                   // 3 vorgefertigte abstrakte Methoden
    public void keyPressed(KeyEvent e) {                        // Wird aufgerufen wenn eine Taste gedrückt wird
        if(e.getKeyCode() == KeyEvent.VK_W) {                   // e ist die Parametervariable & VK_W ist virtual key W (Taste W)
            key[0] = true;                                      // setzt Zustand der Taste W auf gedrückt
        } else if (e.getKeyCode() == KeyEvent.VK_S) {           // prüft ob die gedrückte Taste S ist
            key[1] = true;                                      // setzt Zustand der Taste S auf gedrückt
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {                       // Taste loslassen
        if (e.getKeyCode() == KeyEvent.VK_W) {                  // prüft ob losgelassene Taste "W" ist
            key[0] = false;                                     // setzt Zustand der Taste "W" auf nicht gedrückt
        } else if (e.getKeyCode() == KeyEvent.VK_S) {           // prüft ob losgelassene Taste "S" ist
            key[1] = false;                                     // setzt Zustand der Taste "S" auf nicht gedrückt
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {                          // Taste antippen, keine Funktion in diesem Spiel
        
    }
    

}
