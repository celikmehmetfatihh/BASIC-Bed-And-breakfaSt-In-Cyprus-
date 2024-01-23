package GUI;

import javax.swing.*;

import AppExceptions.*;
import Classes.FullProperty;
import Classes.BASIC;
import Classes.Host;
import Classes.SharedProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Graphical user interface for adding a Full Property to the system.
 * Allows users to input details such as property ID, number of bedrooms, number of rooms,
 * city, price per day, property size, and host ID. Handles various exceptions related to
 * input validation.
 *
 * @author Mehmet Fatih Çelik
 * @version 1.0
 */
public class AddFullProperty extends JFrame{
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField propertyIdTextField;
    private JTextField noBedroomsTextField;
    private JTextField noRoomsTextField;
    private JTextField cityTextField;
    private JTextField priceTextField;
    private JTextField propertySizeTextField;
    private JTextField hostIdTextField;

    public AddFullProperty(BASIC b) {
        this.setContentPane(mainPanel);
        this.setTitle("Add Full Property");
        this.setVisible(true);
        this.pack();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int propertyId = Integer.parseInt(propertyIdTextField.getText());

                    int noOfBedrooms;
                    try {
                        noOfBedrooms = Integer.parseInt(noBedroomsTextField.getText());
                    } catch (NumberFormatException e3) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid number of bedrooms format: Please enter a valid integer for the number of bedrooms.");
                        throw new InvalidFieldFormatException();
                    }

                    int noOfRooms;
                    try {
                        noOfRooms = Integer.parseInt(noRoomsTextField.getText());
                    } catch (NumberFormatException e4) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid number of rooms format: Please enter a valid integer for the number of rooms.");
                        throw new InvalidFieldFormatException();
                    }

                    String city = cityTextField.getText();

                    float pricePerDay;
                    try {
                        pricePerDay = Float.parseFloat(priceTextField.getText());
                    } catch (NumberFormatException e6) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid price per day format: Please enter a valid float for the price per day.");
                        throw new InvalidFieldFormatException();
                    }

                    int hostId;
                    try {
                        hostId = Integer.parseInt(hostIdTextField.getText());
                    } catch (NumberFormatException e6) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid host id format: Please enter a valid integer for the host id.");
                        throw new InvalidFieldFormatException();
                    }

                    double propertySize;
                    try{
                        propertySize = Double.parseDouble(propertySizeTextField.getText());
                    } catch (NumberFormatException e7) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid property size format: Please enter a valid double for the property size.");
                        throw new InvalidFieldFormatException();
                    }

                    if (b.isPropertyIdExists(propertyId) != -1) {
                        JOptionPane.showMessageDialog(new Frame(), "This property id already exists in the system!");
                        throw new DuplicateIDException();
                    }

                    if (b.isHostExists(hostId) == -1) {
                        JOptionPane.showMessageDialog(new Frame(), "This host id does not exist in the system!");
                        throw new HostNotFoundException();
                    } else if (b.isHostExists(hostId) == -2) {
                        JOptionPane.showMessageDialog(new Frame(), "The property cannot be bound to any customer.");
                        throw new BindToCustomerException();
                    }

                    Host h = (Host) b.getUserAccordingToId(hostId);

                    FullProperty fullProperty = new FullProperty(propertyId, noOfBedrooms, noOfRooms, city, pricePerDay, h, propertySize);
                    b.properties.add(fullProperty);
                    JOptionPane.showMessageDialog(new Frame(), "Full Property has been added successfully!");

                } catch (NumberFormatException e5) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid property ID format: Please enter a valid integer for the property ID.");
                    throw new InvalidPropertyIdException();
                }

            }
        });
    }
}
