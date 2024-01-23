package GUI;

import AppExceptions.*;
import Classes.BASIC;
import Classes.Booking;
import Classes.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * GetBookingCost is a Swing GUI class that allows users to calculate the total cost of bookings for a specific user on a given property.
 * Users can enter the user ID and property ID in text fields and click the "Get Booking Cost" button to see the calculated cost.
 * The class handles input validation, checks for user existence, user type, property existence, and provides appropriate error messages.
 * It uses the BASIC class to retrieve user and booking information.
 *
 * @author Mehmet Fatih Çelik
 * @version 1.0
 */
public class GetBookingCost extends JFrame{
    private JPanel mainPanel;
    private JButton getBookingCostButton;
    private JTextField userIdTextField;
    private JTextField propertyIdTextField;

    /**
     * Constructs a new GetBookingCost instance.
     *
     * @param b The BASIC instance providing the core application logic.
     */
    public GetBookingCost(BASIC b) {
        this.setContentPane(mainPanel);
        this.setTitle("Get Booking Cost");
        this.setVisible(true);
        this.pack();

        getBookingCostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId;
                try {
                    userId = Integer.parseInt(userIdTextField.getText());

                    if (b.isUserIdExists(userId)) {
                        JOptionPane.showMessageDialog(new Frame(), "This user id already exists in the system!");
                        throw new DuplicateIDException();
                    }

                    if (b.isUseraCustomer(userId) == -1) {  // Host
                        JOptionPane.showMessageDialog(new Frame(), "Host type user cannot have any bookings!");
                        throw new InvalidUserTypeException();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid user ID format: Please enter a valid integer for the user ID.");
                    throw new InvalidUserIdException();
                }

                int propertyId;
                try {
                    propertyId = Integer.parseInt(propertyIdTextField.getText());

                    if (b.isPropertyIdExists(propertyId) == -1) {
                        JOptionPane.showMessageDialog(new Frame(), "This property id does not exist in the system!");
                        throw new PropertyNotFoundException();
                    }

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid property ID format: Please enter a valid integer for the property ID.");
                    throw new InvalidPropertyIdException();
                }

                Customer customer = (Customer) b.getUserAccordingToId(userId);
                double totalCost = 0;

                for (Booking booking : customer.getBookings()) {
                    if (booking.getProperty().getPropertyId() == propertyId) {
                        totalCost += booking.totalCost();
                    }
                }

                double discount = b.getDiscountForUser(userId);

                totalCost = totalCost - (totalCost * discount);

                if (totalCost == 0) {
                    JOptionPane.showMessageDialog(null, "There is no booking for user id " + userId + " on property id " + propertyId + "!");
                } else {
                    StringBuilder message = new StringBuilder();

                    if (discount == 0) {
                        message.append("This user cannot get any discounts!");
                    } else {
                        message.append("This user gets ").append(discount * 100).append("% discount!");
                    }

                    message.append("\n\nThe total cost for user id ").append(userId).append(" on property id ").append(propertyId).append(" is: ").append(totalCost);

                    JOptionPane.showMessageDialog(null, message.toString());
                }
            }

        });
    }
}
