package GUI;

import AppExceptions.DuplicateIDException;
import AppExceptions.InvalidDateException;
import AppExceptions.InvalidDateFormatException;
import AppExceptions.InvalidUserIdException;
import Classes.Gold;
import Classes.BASIC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Graphical user interface for adding a Gold Customer to the system.
 * Allows users to input details such as user ID, first name, last name, date of birth,
 * registration date, preferred payment method, and gold level. Handles various exceptions
 * related to input validation.
 *
 * @author Mehmet Fatih Çelik
 * @version 1.0
 */
public class AddGoldCustomer extends JFrame{
    private JPanel mainPanel;
    private JTextField userIdTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField dobTextField;
    private JTextField registrationTextField;
    private JComboBox preferredPaymentComboBox;
    private JComboBox goldLevelComboBox;
    private JButton addButton;

    public AddGoldCustomer(BASIC b) {
        this.setContentPane(mainPanel);
        this.setTitle("Add Gold Customer");
        this.setVisible(true);
        this.pack();

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = -1;
                LocalDate dateOfBirth;
                String firstName;
                String lastName;
                LocalDate registrationDate;
                String preferredPaymentMethod;
                int goldLevel;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");



                try {
                    userId = Integer.parseInt(userIdTextField.getText());

                    if (b.isUserIdExists(userId)) {
                        JOptionPane.showMessageDialog(new Frame(), "This user id already exists in the system!");
                        throw new DuplicateIDException();
                    }
                    String temp = dobTextField.getText();
                    dateOfBirth = LocalDate.parse(temp, formatter);

                    temp = registrationTextField.getText();
                    registrationDate = LocalDate.parse(temp, formatter);

                    if (dateOfBirth.isAfter(LocalDate.now()) || registrationDate.isAfter(LocalDate.now())) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid date: Birth date or registration date cannot be in the future!");
                        throw new InvalidDateException();
                    }
                } catch (NumberFormatException e2) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid user ID format: Please enter a valid integer for the user ID.");
                    throw new InvalidUserIdException();
                } catch (DateTimeParseException e1) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid date format: Please enter dates in the correct format (dd/MM/yyyy).");
                    throw new InvalidDateFormatException();
                }

                firstName = firstNameTextField.getText();
                lastName = lastNameTextField.getText();

                int paymentIndex = preferredPaymentComboBox.getSelectedIndex();
                if(paymentIndex == 0)
                    preferredPaymentMethod = "Credit Card";
                else if (paymentIndex == 1)
                    preferredPaymentMethod = "Debit Card";
                else
                    preferredPaymentMethod = "Paypal";

                int goldLevelIndex = goldLevelComboBox.getSelectedIndex();
                if(goldLevelIndex == 0)
                    goldLevel = 1;
                else if(goldLevelIndex == 1)
                    goldLevel = 2;
                else
                    goldLevel = 3;

                Gold gold = new Gold(userId, dateOfBirth, registrationDate, firstName, lastName, preferredPaymentMethod, goldLevel);
                b.users.add(gold);
                JOptionPane.showMessageDialog(new Frame(), "Gold Customer has been added successfully!");


            }
        });
    }
}

