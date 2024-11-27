import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Fenster3 {
    public static void main(String[] args) {
        // Hauptfenster
        JFrame frame = new JFrame("Pong Spiel Startmenü");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new GridLayout(6, 1));

        // Überschrift
        JLabel titleLabel = new JLabel("Willkommen zu Pong!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 50));
        frame.add(titleLabel);

        // Radiobuttons für Spielmodus
        JRadioButton gegenPCButton = new JRadioButton("Gegen den Computer spielen");
        gegenPCButton.setFont(new Font("Roboto", Font.BOLD, 20));
        JRadioButton gegenSpielerButton = new JRadioButton("Gegen einen anderen Spieler spielen");
        gegenSpielerButton.setFont(new Font("Roboto", Font.BOLD, 20));
        ButtonGroup modusGruppe = new ButtonGroup();
        modusGruppe.add(gegenPCButton);
        modusGruppe.add(gegenSpielerButton);
        frame.add(gegenPCButton);
        frame.add(gegenSpielerButton);

        // Eingabefelder für Spielernamen
        JTextField spieler1Name = new JTextField("Spieler 1 Name");
        spieler1Name.setFont(new Font("Roboto", Font.BOLD, 20));
        JTextField spieler2Name = new JTextField("Spieler 2 Name (nur für Spieler vs. Spieler)");
        spieler2Name.setFont(new Font("Roboto", Font.BOLD, 20));
        spieler2Name.setEnabled(false); // Anfangs deaktiviert
        frame.add(spieler1Name);
        frame.add(spieler2Name);

        // Methode zum Hinzufügen eines FocusListeners für Platzhalter
        addPlaceholderBehavior(spieler1Name, "Spieler 1 Name");
        addPlaceholderBehavior(spieler2Name, "Spieler 2 Name (nur für Spieler vs. Spieler)");

        // Aktiviert das Eingabefeld für Spieler 2 nur, wenn "Gegen Spieler" gewählt wird
        gegenSpielerButton.addActionListener(e -> spieler2Name.setEnabled(true));
        gegenPCButton.addActionListener(e -> {
            spieler2Name.setEnabled(false);
            spieler2Name.setText("Spieler 2 Name (nur für Spieler vs. Spieler)"); // Platzhalter zurücksetzen
        });

        // Start-Button
        JButton startButton = new JButton("Spiel starten");
        startButton.setFont(new Font("Roboto", Font.BOLD, 30));
        frame.add(startButton);

        // Aktion für den Start-Button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String modus = gegenPCButton.isSelected() ? "Computer" : "anderen Spieler";
                String name1 = spieler1Name.getText();
                String name2 = spieler2Name.isEnabled() ? spieler2Name.getText() : "Computer";

                JOptionPane.showMessageDialog(frame,
                        "Spielmodus: " + modus + "\n" +
                                "Spieler 1: " + name1 + "\n" +
                                "Spieler 2: " + name2,
                        "Spiel Startet",
                        JOptionPane.INFORMATION_MESSAGE);

                // Hier kannst du die Logik hinzufügen, um das eigentliche Pong-Spiel zu starten.
            }
        });

        // Fenster sichtbar machen
        frame.setVisible(true);
    }

    // Methode zum Hinzufügen eines FocusListeners für Platzhalterverhalten
    private static void addPlaceholderBehavior(JTextField textField, String placeholder) {
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Wenn der Text dem Platzhalter entspricht, leeren
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Wenn das Feld leer ist, den Platzhalter wieder anzeigen
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });
    }
}
