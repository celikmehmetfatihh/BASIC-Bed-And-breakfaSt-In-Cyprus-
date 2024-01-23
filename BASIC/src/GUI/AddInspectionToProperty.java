package GUI;

import AppExceptions.DuplicateIDException;
import AppExceptions.DuplicateInspectionException;
import AppExceptions.InvalidPropertyIdException;
import Classes.BASIC;
import Classes.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * Graphical user interface for adding an inspection to a property in the system.
 * Allows users to input details such as property ID and inspection text. Handles various
 * exceptions related to input validation.
 *
 * @author Your Name
 * @version 1.0
 */
public class AddInspectionToProperty extends JFrame{
    private JPanel mainPanel;
    private JButton addInspectionButton;
    private JTextField propertyIdTextField;
    private JTextArea inspectionText;


    public AddInspectionToProperty(BASIC b) {
        this.setContentPane(mainPanel);
        this.setTitle("Add Inspection to Property");
        this.setVisible(true);
        this.pack();

        addInspectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int propertyId;
                try{
                    propertyId = Integer.parseInt(propertyIdTextField.getText());

                    if (b.isPropertyIdExists(propertyId) == -1) {
                        JOptionPane.showMessageDialog(new Frame(), "This property id does not exist in the system!");
                        throw new InvalidPropertyIdException();
                    }

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid property ID format: Please enter a valid integer for the property ID.");
                    throw new InvalidPropertyIdException();
                }

                String inspText = inspectionText.getText();
                Property property = b.getPropertyAccordingToId(propertyId);
                if (property.getInspection().containsKey(LocalDate.now())) {
                    JOptionPane.showMessageDialog(new Frame(),"Two inspections cannot be scheduled for the same day. ");
                    throw new DuplicateInspectionException();
                } else {
                    if (inspText != null && !inspText.isEmpty()) {
                        StringBuilder message = new StringBuilder();
                        message.append("Inspection has been added successfully!");
                        property.getInspection().put(LocalDate.now(), inspText);

                        message.append("\n\nInspections for this property:\n");
                        message.append(property.getInspection());
                        message.append("\n");

                        JOptionPane.showMessageDialog(null, message.toString());
                    } else {
                        String errorMessage = "Inspection text cannot be empty!";
                        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                        throw new IllegalArgumentException(errorMessage);
                    }


                }



            }
        });
    }
}
