package src.Main;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {
    
    public Window(int breite, int hoehe, String titel, Game game) {
        JFrame frame = new JFrame(titel);                                   // erstellt ein neues JFrame (Fenster) und setzt den Titel

        frame.setPreferredSize(new Dimension(breite, hoehe));               // setzt bevorzugte Fenstergröße
        frame.setMinimumSize(new Dimension(breite, hoehe));                 // setzt minimale Fenstergröße
        frame.setMaximumSize(new Dimension(breite, hoehe));                 // setzt maximale Fenstergröße
        frame.setResizable(false);                                          // verhindert dass Festergröße von Hand geändert werden kann
        frame.setTitle(titel);                                              // setzt Fenstertitel
        frame.setLocationRelativeTo(null);                                  // positioniert Fenster in der Mitte des Bildschirms
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               // beendet die Anwendung, wenn das Fenster geschlossen wird
        frame.add(game);                                                    // hinzufügen von allem was in Game ist (durch Canvas)
        frame.setVisible(true);                                             // macht das Fenster sichtbar
    }
}