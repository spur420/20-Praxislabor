import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuswahlFenster {
    public static void main(String[] args) {
        // Hauptfenster erstellen
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new FlowLayout());

        // Label hinzufügen
        JLabel label = new JLabel("Wählen Sie eine Option:");
        frame.add(label);

        // Checkboxen für Auswahlmöglichkeiten
        JCheckBox option1 = new JCheckBox("Option 1");
        JCheckBox option2 = new JCheckBox("Option 2");
        JCheckBox option3 = new JCheckBox("Option 3");
        frame.add(option1);
        frame.add(option2);
        frame.add(option3);

        // Radiobuttons für exklusive Auswahl
        JRadioButton radio1 = new JRadioButton("VS PC");
        JRadioButton radio2 = new JRadioButton("VS Player");
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radio1);
        radioGroup.add(radio2);
        frame.add(radio1);
        frame.add(radio2);

        // Dropdown-Menü (ComboBox)
        String[] dropdownOptions = {"Auswahl 1", "Auswahl 2", "Auswahl 3"};
        JComboBox<String> comboBox = new JComboBox<>(dropdownOptions);
        frame.add(comboBox);

        // Button hinzufügen
        JButton submitButton = new JButton("Bestätigen");
        frame.add(submitButton);

        // Ergebnis-Label
        JLabel resultLabel = new JLabel("Ihre Auswahl wird hier angezeigt.");
        frame.add(resultLabel);

        // Aktion für den Button definieren
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder result = new StringBuilder("Ausgewählt: ");
                
                // Checkboxen auslesen
                if (option1.isSelected()) result.append("Option 1 ");
                if (option2.isSelected()) result.append("Option 2 ");
                if (option3.isSelected()) result.append("Option 3 ");

                // Radiobuttons auslesen
                if (radio1.isSelected()) result.append("| Radio 1 ");
                if (radio2.isSelected()) result.append("| Radio 2 ");

                // Dropdown-Auswahl auslesen
                result.append("| Dropdown: ").append(comboBox.getSelectedItem());

                // Ergebnis anzeigen
                resultLabel.setText(result.toString());
            }
        });

        // Fenster sichtbar machen
        frame.setVisible(true);
    }
}
