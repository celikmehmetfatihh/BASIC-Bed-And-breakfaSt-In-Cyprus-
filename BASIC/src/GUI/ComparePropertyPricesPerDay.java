package GUI;

import AppExceptions.InvalidPropertyIdException;
import Classes.BASIC;
import Classes.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ComparePropertyPricesPerDay is a Swing GUI class that allows users to compare the daily prices of two properties.
 * Users can enter the property IDs in text fields and click the "Compare Property Prices" button to see the comparison result.
 * The class handles input validation and displays appropriate error messages if invalid input or non-existent property IDs are provided.
 * It uses the BASIC class to retrieve property information and perform the comparison.
 *
 * @author Mehmet Fatih Çelik
 * @version 1.0
 */

public class ComparePropertyPricesPerDay extends JFrame{
    private JPanel mainPanel;
    private JButton comparePropertyPricesButton;
    private JTextField propertyId1TextField;
    private JTextField propertyId2TextField;

    /**
     * Constructs a new ComparePropertyPricesPerDay instance.
     *
     * @param b The BASIC instance providing the core application logic.
     */
    public ComparePropertyPricesPerDay(BASIC b) {
        this.setContentPane(mainPanel);
        this.setTitle("Compare Property Prices per Day");
        this.setVisible(true);
        this.pack();

        comparePropertyPricesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int propertyId1;
                try{
                    propertyId1 = Integer.parseInt(propertyId1TextField.getText());

                    if (b.isPropertyIdExists(propertyId1) == -1) {
                        JOptionPane.showMessageDialog(new Frame(), "This property id does not exist in the system!");
                        throw new InvalidPropertyIdException();
                    }

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid property ID format: Please enter a valid integer for the property ID.");
                    throw new InvalidPropertyIdException();
                }

                int propertyId2;
                try{
                    propertyId2 = Integer.parseInt(propertyId2TextField.getText());

                    if (b.isPropertyIdExists(propertyId2) == -1) {
                        JOptionPane.showMessageDialog(new Frame(), "This property id does not exist in the system!");
                        throw new InvalidPropertyIdException();
                    }

                    if (propertyId1 == propertyId2) {
                        JOptionPane.showMessageDialog(new Frame(), "You cannot compare the same properties!");
                        throw new InvalidPropertyIdException();
                    }

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid property ID format: Please enter a valid integer for the property ID.");
                    throw new InvalidPropertyIdException();
                }

                Property property1 = b.getPropertyAccordingToId(propertyId1);
                Property property2 = b.getPropertyAccordingToId(propertyId2);

                int result = property1.compareTo(property2);

                if (result < 0) {
                    String message = "Property id: " + propertyId1 + " is cheaper than Property id: " + propertyId2 + " per day.";
                    JOptionPane.showMessageDialog(null, message);
                } else if (result > 0) {
                    String message = "Property id: " + propertyId2 + " is cheaper than Property id: " + propertyId1 + " per day.";
                    JOptionPane.showMessageDialog(null, message);
                } else {
                    String message = "Property id: " + propertyId1 + " and Property id: " + propertyId2 + " have the same price per day.";
                    JOptionPane.showMessageDialog(null, message);
                }

            }
        });
    }
}
