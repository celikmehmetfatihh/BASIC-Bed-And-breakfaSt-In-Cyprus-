package GUI;

import AppExceptions.*;
import Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddBooking extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField userIdTextField;
    private JTextField propertyIdTextField;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private JComboBox paidComboBox;

    /**
     * Graphical user interface for adding a booking. Allows users to input details
     * such as user ID, property ID, start date, end date, and payment status.
     * Handles various exceptions related to input validation.
     *
     * @author Mehmet Fatih Çelik
     * @version 1.0
     */
    public AddBooking(BASIC b) {
        this.setContentPane(mainPanel);
        this.setTitle("Add Booking");
        this.setVisible(true);
        this.pack();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userId;
                    try{
                        userId = Integer.parseInt(userIdTextField.getText());

                        if (b.isUserIdExists(userId)) {
                            JOptionPane.showMessageDialog(new Frame(), "This user id already exists in the system!");
                            throw new DuplicateIDException();
                        }
                        if (b.isUseraCustomer(userId) == -1) {  // Not customer
                            JOptionPane.showMessageDialog(new Frame(), "The booking cannot be bound to host");
                                throw new BindToCustomerException();
                        }

                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid user ID format: Please enter a valid integer for the user ID.");
                        throw new InvalidUserIdException();
                    }

                    int propertyId;
                    try{
                        propertyId = Integer.parseInt(propertyIdTextField.getText());

                        if (b.isPropertyIdExists(propertyId) == -1) {
                            JOptionPane.showMessageDialog(new Frame(), "This property id does not exist in the system!");
                            throw new PropertyNotFoundException();
                        }

                    } catch (NumberFormatException e2) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid property ID format: Please enter a valid integer for the property ID.");
                        throw new InvalidPropertyIdException();
                    }

                    LocalDate startDate = LocalDate.parse(startDateTextField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalDate endDate = LocalDate.parse(endDateTextField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    boolean isPaid ;
                    int paidIndex = paidComboBox.getSelectedIndex();
                    if(paidIndex == 0)
                        isPaid = true;
                    else
                        isPaid = false;

                    if (endDate.isBefore(startDate)) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid date: End date must be after the start date.");
                        throw new InvalidDateException();
                    }

                    Property p =  b.getPropertyAccordingToId(propertyId);

                    Booking booking = new Booking(startDate, endDate, isPaid, p);

                    Customer c = (Customer) b.getUserAccordingToId(userId);
                    c.getBookings().add(booking);

                    JOptionPane.showMessageDialog(new Frame(), "Booking has been added successfully!");


                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid date format: Please enter dates in the correct format (dd/MM/yyyy).");
                    throw new InvalidDateFormatException();
                }
            }
        });
    }
}
