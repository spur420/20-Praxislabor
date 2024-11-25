package Main;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {
    
    public Window(int breite, int hoehe, String titel, Game game) {
        JFrame frame = new JFrame(titel);

        frame.setPreferredSize(new Dimension(breite, hoehe));
        frame.setMinimumSize(new Dimension(breite, hoehe));
        frame.setMaximumSize(new Dimension(breite, hoehe));
        frame.setResizable(false);
        frame.setTitle(titel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);                                                    //hinzufügen von allem was in Game ist (durch Canvas)
        frame.setVisible(true);
    }
}