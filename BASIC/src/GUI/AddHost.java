package GUI;

import AppExceptions.*;
import Classes.Host;
import Classes.User;
import Classes.BASIC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Graphical user interface for adding a Host to the system.
 * Allows users to input details such as user ID, first name, last name, date of birth,
 * registration date, and tax number. Handles various exceptions related to input validation.
 *
 * @author Mehmet Fatih Çelik
 * @version 1.0
 */

public class AddHost extends JFrame{
    private JPanel mainPanel;
    private JTextField userIdTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField dobTextField;
    private JTextField registrationTextField;
    private JTextField taxNumberTextField;
    private JButton addButton;

    public AddHost(BASIC b) {
        this.setContentPane(mainPanel);
        this.setTitle("Add Host");
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
                double taxNumber;
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

                try {
                    taxNumber = Double.parseDouble(taxNumberTextField.getText());
                } catch (NumberFormatException e3) {
                    JOptionPane.showMessageDialog(new Frame(), "Invalid tax number format: Please enter a valid double for the tax number.");
                    throw new InvalidFieldFormatException();
                }

                Host host = new Host(userId, dateOfBirth, registrationDate, firstName, lastName, taxNumber);
                b.users.add(host);
                JOptionPane.showMessageDialog(new Frame(), "Host has been added successfully!");


            }
        });
    }
}

