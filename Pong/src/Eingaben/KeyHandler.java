package src.Eingaben;                           // definiert Paket zu dem diese Klasse gehört

import java.awt.event.KeyEvent;             // ermöglicht Zugriff auf Informationen über Tastendrücke
import java.awt.event.KeyListener;          // implementierung des KeyListeners für Tastatureingaben

public class KeyHandler implements KeyListener {                // Keylistener-Interface für Tastatureingaben

    // Player 1 Bewegungen
    public boolean key1[] = new boolean[2];                         // Player 1: W, S
    public boolean key2[] = new boolean[2];                         // Player 2: Pfeiltasten

    @Override                                                   // 3 vorgefertigte abstrakte Methoden
    public void keyPressed(KeyEvent e) {                        // Wird aufgerufen wenn eine Taste gedrückt wird
        if(e.getKeyCode() == KeyEvent.VK_W) {                   // e ist die Parametervariable & VK_W ist virtual key W (Taste W)
            key1[0] = true;                                      // setzt Zustand der Taste W auf gedrückt
        } else if (e.getKeyCode() == KeyEvent.VK_S) {           // prüft ob die gedrückte Taste S ist
            key1[1] = true;                                      // setzt Zustand der Taste S auf gedrückt
        }
        if(e.getKeyCode() == KeyEvent.VK_UP) {               // e ist die Parametervariable & VK_W ist virtual key W (Taste W)
            key2[0] = true;                                     // setzt Zustand der Taste W auf gedrückt
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {     // prüft ob die gedrückte Taste S ist
            key2[1] = true;                                     // setzt Zustand der Taste S auf gedrückt
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {                       // Taste loslassen
        if (e.getKeyCode() == KeyEvent.VK_W) {                  // prüft ob losgelassene Taste "W" ist
            key1[0] = false;                                     // setzt Zustand der Taste "W" auf nicht gedrückt
        } else if (e.getKeyCode() == KeyEvent.VK_S) {           // prüft ob losgelassene Taste "S" ist
            key1[1] = false;                                     // setzt Zustand der Taste "S" auf nicht gedrückt
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {              // prüft ob losgelassene Taste "W" ist
            key2[0] = false;                                    // setzt Zustand der Taste "W" auf nicht gedrückt
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {     // prüft ob losgelassene Taste "S" ist
            key2[1] = false;                                    // setzt Zustand der Taste "S" auf nicht gedrückt
        }
    }    
    
    @Override
    public void keyTyped(KeyEvent e) {                          // Taste antippen, keine Funktion in diesem Spiel
        
    }
    

}
