import javax.swing.*; // Importiert Klassen für grafische Benutzeroberflächen (GUI) aus dem Swing-Paket.

import src.Main.Game;

import java.awt.*; // Importiert Klassen für grafische Komponenten und Layouts.
import java.awt.event.ActionEvent; // Importiert Klassen für Ereignisbehandlung von Aktionen.
import java.awt.event.ActionListener; // Importiert die Schnittstelle für ActionListener.
import java.awt.event.FocusEvent; // Importiert Klassen für Fokusereignisse.
import java.awt.event.FocusListener; // Importiert die Schnittstelle für FocusListener.

public class Fenster5 { // Definiert eine öffentliche Klasse namens Fenster5.
    public static void main(String[] args) { // Startpunkt der Anwendung.
        // Hauptfenster
        JFrame frame = new JFrame("Pong Spiel Startmenü"); // Erstellt ein Hauptfenster mit Titel "Pong Spiel Startmenü".
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beendet das Programm, wenn das Fenster geschlossen wird.
        frame.setSize(800, 500); // Setzt die Größe des Fensters auf 800 x 500 Pixel.
        frame.setLayout(new GridLayout(6, 1)); // Verwendet ein Rasterlayout mit 6 Zeilen und 1 Spalte.
        frame.setLocationRelativeTo(null); // positioniert Fenster in der Mitte des Bildschirms

        // Überschrift
        JLabel titleLabel = new JLabel("Willkommen zu Pong!", SwingConstants.CENTER); // Erstellt ein zentriertes Label mit Text "Willkommen zu Pong!".
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 50)); // Setzt die Schriftart des Labels auf "Roboto", fett, Größe 50.
        frame.add(titleLabel); // Fügt das Label dem Fenster hinzu.

        // Radiobuttons für Spielmodus
        JRadioButton gegenPCButton = new JRadioButton("Gegen den Computer spielen"); // Erstellt einen Radiobutton für den Modus "Gegen Computer spielen".
        gegenPCButton.setFont(new Font("Roboto", Font.BOLD, 20)); // Setzt die Schriftart des Radiobuttons.
        JRadioButton gegenSpielerButton = new JRadioButton("Gegen einen anderen Spieler spielen"); // Erstellt einen Radiobutton für den Modus "Gegen Spieler spielen".
        gegenSpielerButton.setFont(new Font("Roboto", Font.BOLD, 20)); // Setzt die Schriftart des zweiten Radiobuttons.
        ButtonGroup modusGruppe = new ButtonGroup(); // Erstellt eine Gruppe für die Radiobuttons, um nur eine Auswahl zuzulassen.
        modusGruppe.add(gegenPCButton); // Fügt den ersten Radiobutton der Gruppe hinzu.
        modusGruppe.add(gegenSpielerButton); // Fügt den zweiten Radiobutton der Gruppe hinzu.

        // Standardauswahl: Gegen den Computer spielen
        gegenPCButton.setSelected(true); // Setzt den Radiobutton "Gegen Computer spielen" als Standardauswahl.

        frame.add(gegenPCButton); // Fügt den ersten Radiobutton dem Fenster hinzu.
        frame.add(gegenSpielerButton); // Fügt den zweiten Radiobutton dem Fenster hinzu.

        // Eingabefelder für Spielernamen
        JTextField spieler1Name = new JTextField("Spieler 1 Name"); // Erstellt ein Textfeld mit Platzhalter "Spieler 1 Name".
        spieler1Name.setFont(new Font("Roboto", Font.BOLD, 20)); // Setzt die Schriftart des Textfelds.
        JTextField spieler2Name = new JTextField("Spieler 2 Name (nur für Spieler vs. Spieler)"); // Erstellt ein Textfeld mit Platzhalter für Spieler 2.
        spieler2Name.setFont(new Font("Roboto", Font.BOLD, 20)); // Setzt die Schriftart des Textfelds.
        spieler2Name.setEnabled(false); // Deaktiviert das Textfeld für Spieler 2 standardmäßig.
        frame.add(spieler1Name); // Fügt das Textfeld für Spieler 1 dem Fenster hinzu.
        frame.add(spieler2Name); // Fügt das Textfeld für Spieler 2 dem Fenster hinzu.

        // Methode zum Hinzufügen eines FocusListeners für Platzhalter
        addPlaceholderBehavior(spieler1Name, "Spieler 1 Name"); // Fügt Platzhalterverhalten für Spieler 1 hinzu.
        addPlaceholderBehavior(spieler2Name, "Spieler 2 Name (nur für Spieler vs. Spieler)"); // Fügt Platzhalterverhalten für Spieler 2 hinzu.

        // Aktiviert das Eingabefeld für Spieler 2 nur, wenn "Gegen Spieler" gewählt wird
        gegenSpielerButton.addActionListener(e -> spieler2Name.setEnabled(true)); // Aktiviert das Textfeld für Spieler 2, wenn "Gegen Spieler" ausgewählt wird.
        gegenPCButton.addActionListener(e -> { // Fügt Aktion hinzu, wenn "Gegen Computer" ausgewählt wird.
            spieler2Name.setEnabled(false); // Deaktiviert das Textfeld für Spieler 2.
            spieler2Name.setText("Spieler 2 Name (nur für Spieler vs. Spieler)"); // Setzt den Platzhaltertext zurück.
        });

        // Start-Button
        JButton startButton = new JButton("Spiel starten"); // Erstellt einen Button mit Text "Spiel starten".
        startButton.setFont(new Font("Roboto", Font.BOLD, 30)); // Setzt die Schriftart des Buttons.
        frame.add(startButton); // Fügt den Button dem Fenster hinzu.

        // Aktion für den Start-Button
        startButton.addActionListener(new ActionListener() { // Fügt einen ActionListener für den Button hinzu.
            @Override
            public void actionPerformed(ActionEvent e) { // Führt die Aktion aus, wenn der Button geklickt wird.
                String modus = gegenPCButton.isSelected() ? "Computer" : "anderen Spieler"; // Überprüft den ausgewählten Spielmodus.
                String name1 = spieler1Name.getText(); // Liest den Text aus dem Textfeld für Spieler 1.
                String name2 = spieler2Name.isEnabled() ? spieler2Name.getText() : "Computer"; // Liest den Text für Spieler 2 oder setzt auf "Computer".

                JOptionPane.showMessageDialog(frame, // Zeigt eine Dialogbox mit den eingegebenen Spielinformationen.
                        "Spielmodus: " + modus + "\n" +
                        "Spieler 1: " + name1 + "\n" +
                        "Spieler 2: " + name2,
                        "Spiel Startet",
                        JOptionPane.INFORMATION_MESSAGE);

                // Hier kannst du die Logik hinzufügen, um das eigentliche Pong-Spiel zu starten.
                new Game();
            }
        });

        // Fenster sichtbar machen
        frame.setVisible(true); // Macht das Fenster sichtbar.
    }

    // Methode zum Hinzufügen eines FocusListeners für Platzhalterverhalten
    private static void addPlaceholderBehavior(JTextField textField, String placeholder) { // Methode zum Verwalten von Platzhaltertext in Textfeldern.
        textField.addFocusListener(new FocusListener() { // Fügt einen FocusListener für das Textfeld hinzu.
            @Override
            public void focusGained(FocusEvent e) { // Reaktion, wenn das Textfeld den Fokus erhält.
                if (textField.getText().equals(placeholder)) { // Überprüft, ob der aktuelle Text dem Platzhalter entspricht.
                    textField.setText(""); // Löscht den Text, wenn er dem Platzhalter entspricht.
                }
            }

            @Override
            public void focusLost(FocusEvent e) { // Reaktion, wenn das Textfeld den Fokus verliert.
                if (textField.getText().isEmpty()) { // Überprüft, ob das Feld leer ist.
                    textField.setText(placeholder); // Setzt den Platzhalter zurück, wenn das Feld leer ist.
                }
            }
        });
    }
}
