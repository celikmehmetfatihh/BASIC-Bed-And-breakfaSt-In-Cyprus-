package GUI;

import AppExceptions.*;
import Classes.BASIC;
import Classes.Booking;
import Classes.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GetUserBooking is a Swing GUI class that allows users to retrieve and display booking details for a specific customer.
 * Users can enter the customer's user ID and click the "Get User Booking" button to see the booking details.
 * The class handles input validation, checks for user existence, user type, and provides appropriate error messages.
 * It uses the BASIC class to retrieve customer and booking information.
 *
 * @author Mehmet Fatih Çelik
 * @version 1.0
 */
public class GetUserBooking extends JFrame{
    private JPanel mainPanel;
    private JButton getUserBookingButton;
    private JTextField userIdTextField;

    /**
     * Constructs a new GetUserBooking instance.
     *
     * @param b The BASIC instance providing the core application logic.
     */
    public GetUserBooking(BASIC b) {
        this.setContentPane(mainPanel);
        this.setTitle("Get User Booking");
        this.setVisible(true);
        this.pack();

        getUserBookingButton.addActionListener(new ActionListener() {
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

                    Customer customer = (Customer) b.getUserAccordingToId(userId);
                    ArrayList<Booking> bookings = customer.getBookings();

                    if (bookings.isEmpty())
                        JOptionPane.showMessageDialog(new Frame(), "This user does not have any bookings");
                    else {
                        StringBuilder message = new StringBuilder("Booking Details:\n");
                        for (int i = 0; i < bookings.size(); i++) {
                            Booking booking = bookings.get(i);
                            message.append("\nBooking ").append(i + 1).append("\n")
                                    .append(((Customer) b.getUserAccordingToId(userId)).getBookings().get(i).toString());
                        }
                        JOptionPane.showMessageDialog(null, message.toString(), "Booking Details", JOptionPane.INFORMATION_MESSAGE);
                    }



                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid user ID format: Please enter a valid integer for the user ID.");
                    throw new InvalidUserIdException();
                }
            }
        });
    }
}
