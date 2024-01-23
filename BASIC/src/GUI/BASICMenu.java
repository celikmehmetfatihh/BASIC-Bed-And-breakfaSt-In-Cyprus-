package GUI;
import AppExceptions.*;
import Classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;

/**
 * Graphical user interface for the main menu of the BASIC system.
 * Provides options to perform various operations such as adding/deleting users and properties,
 * displaying user/property details, exporting user/property data, and executing other functionalities.
 * Handles user interactions and exceptions related to input validation.
 *
 * @author Mehmet Fatih Çelik
 * @version 1.0
 **/
public class BASICMenu extends JFrame{
    private JPanel mainPanel;
    private JRadioButton addUserRadioButton;
    private JRadioButton deleteUserRadioButton;
    private JRadioButton detailUserRadioButton;
    private JRadioButton addPropertyRadioButton;
    private JRadioButton deletePropertyRadioButton;
    private JComboBox addUserComboBox;
    private JTextField deleteUserTextField;
    private JTextField detailUserTextField;
    private JComboBox addPropertyComboBox;
    private JTextField deletePropertyTextField;

    private JButton executeButton;
    private JRadioButton terminateRadioButton;
    private JRadioButton getPropertyDetailRadioButton;
    private JTextField detailPropertyTextField;
    private JRadioButton addBookingRadioButton;
    private JRadioButton getUserBookingRadioButton;
    private JRadioButton getBookingCostRadioButton;
    private JRadioButton listUsersRadioButton;
    private JRadioButton listPropertiesRadioButton;
    private JRadioButton addInspectionToPropertyRadioButton;
    private JRadioButton comparePropertyPricesRadioButton;


    JMenuBar menuBar = new JMenuBar();
    JMenu fileManager = new JMenu("Export");
    JMenu displayTabular = new JMenu("Display");
    JMenuItem exportUser = new JMenuItem("User");
    JMenuItem displayUser = new JMenuItem("User");
    JMenuItem exportProperty = new JMenuItem("Property");
    JMenuItem displayProperty = new JMenuItem("Property");


    /**
     * Initializes the BASICMenu GUI with the specified BASIC instance.
     * Configures the menu bar, buttons, and event listeners for user interactions.
     *
     * @param b The BASIC instance representing the main system.
     **/
    public BASICMenu(BASIC b) {
        displayTabular.add(displayUser);
        displayTabular.add(displayProperty);
        fileManager.add(exportUser);
        fileManager.add(exportProperty);

        menuBar.add(displayTabular);
        menuBar.add(fileManager);
        this.setJMenuBar(menuBar);

        /**
         * ActionListener for exporting user data. Allows the user to select a file
         * and exports user data to the chosen file path.
         *
         * @param e The ActionEvent triggered by the user selecting the export user option.
         */
        exportUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int r = j.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path = (j.getSelectedFile().getAbsolutePath());
                    for(User user: b.users) {
                        try {
                            user.exportToFile(path);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        /**
         * ActionListener for exporting property data. Allows the user to select a file
         * and exports property data to the chosen file path.
         *
         * @param e The ActionEvent triggered by the user selecting the export property option.
         */
        exportProperty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int r = j.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path = (j.getSelectedFile().getAbsolutePath());
                    for(Property property: b.properties) {
                        try {
                            property.exportToFile(path);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        /**
         * ActionListener for displaying user data in a tabular format.
         * Opens a new TabularHost window to show user details.
         *
         * @param e The ActionEvent triggered by the user selecting the display user option.
         */
        displayUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TabularHost(b);
            }
        });

        /**
         * ActionListener for displaying property data in a tabular format.
         * Opens a new TabularProperty window to show property details.
         *
         * @param e The ActionEvent triggered by the user selecting the display property option.
         */
        displayProperty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TabularProperty(b);
            }
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(addUserRadioButton);
        bg.add(deleteUserRadioButton);
        bg.add(detailUserRadioButton);
        bg.add(addPropertyRadioButton);
        bg.add(executeButton);
        bg.add(terminateRadioButton);
        bg.add(deletePropertyRadioButton);
        bg.add(getPropertyDetailRadioButton);
        bg.add(addBookingRadioButton);
        bg.add(getUserBookingRadioButton);
        bg.add(getBookingCostRadioButton);
        bg.add(listUsersRadioButton);
        bg.add(listPropertiesRadioButton);
        bg.add(addInspectionToPropertyRadioButton);
        bg.add(comparePropertyPricesRadioButton);

        this.setContentPane(mainPanel);
        this.setTitle("BASIC");
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                terminate(b);
            }
        });

        /**
         * An ActionListener implementation that handles the actions triggered by the executeButton.
         * Depending on the selected radio buttons and user input, it performs various operations related to
         * adding users, deleting users, retrieving user details, adding properties, deleting properties,
         * retrieving property details, adding bookings, retrieving user bookings, calculating booking costs,
         * listing users, listing properties, adding inspections to properties, comparing property prices, or terminating the system.
         */
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addUserRadioButton.isSelected()) {
                    if(addUserComboBox.getSelectedIndex() == 0)
                        new AddHost(b);
                    else if(addUserComboBox.getSelectedIndex() == 1)
                        new AddStandardCustomer(b);
                    else
                       new AddGoldCustomer(b);
                }
                else if(deleteUserRadioButton.isSelected()) {
                    try {
                        int deleteUserId = Integer.parseInt(deleteUserTextField.getText());

                        if (!b.isUserIdExists(deleteUserId)) {
                            JOptionPane.showMessageDialog(new Frame(), "This user id does not exist in the system!");
                            throw new DuplicateIDException();
                        }

                        b.users.remove(b.getUserIndexAccordingToUserId(deleteUserId));
                        JOptionPane.showMessageDialog(new Frame(), "User has been deleted successfully!");

                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid user ID format: Please enter a valid integer for the user ID.");
                        throw new InvalidUserIdException();
                    }
                }

                else if (detailUserRadioButton.isSelected()) {
                    try {
                        int detailUserId = Integer.parseInt(detailUserTextField.getText());

                        if (b.isUserIdExists(detailUserId)) {
                            JOptionPane.showMessageDialog(new Frame(), "This user id already exists in the system!");
                            throw new DuplicateIDException();
                        }

                        JOptionPane.showMessageDialog(new Frame(), b.users.get(b.getUserIndexAccordingToUserId(detailUserId)));

                    } catch (NumberFormatException e2) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid user ID format: Please enter a valid integer for the user ID.");
                        throw new InvalidUserIdException();
                    }
                }

                else if(addPropertyRadioButton.isSelected()) {

                    if(addPropertyComboBox.getSelectedIndex() == 0)
                        new AddSharedProperty(b);
                    else
                        new AddFullProperty(b);
                }

                else if (deletePropertyRadioButton.isSelected()) {
                    try {
                        int deletePropertyId = Integer.parseInt(deletePropertyTextField.getText());

                        int checkProperty = b.isPropertyIdExists(deletePropertyId);
                        if (checkProperty == -1) {
                            JOptionPane.showMessageDialog(new Frame(), "Property Id does not exist!");
                            throw new PropertyNotFoundException();
                        }

                        b.properties.remove(checkProperty);
                        JOptionPane.showMessageDialog(new Frame(), "Property has been deleted successfully!");

                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid property ID format: Please enter a valid integer for the property ID.");
                        throw new InvalidPropertyIdException();
                    }
                }

                else if (getPropertyDetailRadioButton.isSelected()) {
                    try{
                        int detailPropertyId = Integer.parseInt(detailPropertyTextField.getText());

                        int checkProperty = b.isPropertyIdExists(detailPropertyId);
                        if (checkProperty == -1) {
                            JOptionPane.showMessageDialog(new Frame(), "Property Id does not exist!");
                            throw new PropertyNotFoundException();
                        }

                        JOptionPane.showMessageDialog(new Frame(), b.properties.get(checkProperty));

                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(new Frame(), "Invalid property ID format: Please enter a valid integer for the property ID.");
                        throw new InvalidPropertyIdException();
                    }
                }
                else if(addBookingRadioButton.isSelected()) {
                    new AddBooking(b);
                }
                else if (getUserBookingRadioButton.isSelected()) {
                    new GetUserBooking(b);
                }
                else if (getBookingCostRadioButton.isSelected()) {
                    new GetBookingCost(b);
                }
                else if (listUsersRadioButton.isSelected()) {
                    StringBuilder userListMessage = new StringBuilder("User List\n");

                    for (User user : b.users) {
                        userListMessage.append("\n").append(user);
                        userListMessage.append("\n");

                        if (user instanceof Customer) {
                            Customer customer = (Customer) user;
                            ArrayList<Booking> bookings = customer.getBookings();

                            if (bookings.isEmpty()) {
                                userListMessage.append("There are no bookings for this user!\n");
                            } else {
                                for (int i = 0; i < bookings.size(); i++) {
                                    Booking booking = bookings.get(i);
                                    userListMessage.append("\nUser id: ").append(user.getUserId()).append(" Booking-").append(i + 1).append("\n");
                                    userListMessage.append("\nStart Date: ").append(booking.getStartDate());
                                    userListMessage.append("\nEnd Date: ").append(booking.getEndDate());
                                    userListMessage.append("\nIsPaid: ").append(booking.getIsPaid()).append("\n");
                                }
                            }
                        } else {
                            userListMessage.append("This user is a host, cannot have any bookings\n");
                        }
                    }

                    JOptionPane.showMessageDialog(null, userListMessage.toString());
                }
                else if (listPropertiesRadioButton.isSelected()) {
                    StringBuilder propertyListMessage = new StringBuilder("Property List\n");

                    for(Property property: b.properties) {
                        propertyListMessage.append("\n").append(property);
                        propertyListMessage.append("\n");

                        Host host = property.getHost();
                        if (host != null) {
                            propertyListMessage.append("\nHost Detail of the property\n\n");
                            propertyListMessage.append(host);
                        }
                        else
                            propertyListMessage.append("\nNo host details have been found for property id: ").append(property.getPropertyId()).append("\n");
                    }

                    JOptionPane.showMessageDialog(null, propertyListMessage.toString());
                }
                else if (addInspectionToPropertyRadioButton.isSelected()) {
                    new AddInspectionToProperty(b);
                }
                else if (comparePropertyPricesRadioButton.isSelected()) {
                    new ComparePropertyPricesPerDay(b);
                }

                else if (terminateRadioButton.isSelected())
                    terminate(b);
                else
                    JOptionPane.showMessageDialog(new Frame(), "Please select an option");
             }
        });

    }

    /**
     * This method terminates the BASIC system by performing the following actions:
     * 1. Deletes the files "user.txt," "property.txt," and "md5.txt" if they exist.
     * 2. Writes the content of the user list and property list from the provided BASIC object to corresponding files.
     * 3. Calculates the MD5 hash of the combined content of "user.txt" and "property.txt" and writes it to "md5.txt".
     * 4. Prints a termination message and exits the system with status code -1.
     *
     * @param b The BASIC object containing user and property information to be saved.
     */
    public static void terminate(BASIC b) {
        try {
            Files.deleteIfExists(Paths.get("user.txt"));
            Files.deleteIfExists(Paths.get("property.txt"));
            Files.deleteIfExists(Paths.get("md5.txt"));

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("user.txt"));
                 ObjectOutputStream outputStream2 = new ObjectOutputStream(new FileOutputStream("property.txt"));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("md5.txt"))) {

                for (User u : b.users) {
                    outputStream.writeObject(u);
                }
                for (Property p : b.properties) {
                    outputStream2.writeObject(p);
                }

                bufferedWriter.write(createMD5());
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Thank you for using BASIC!");
        System.exit(-1);
    }

    /**
     * This utility method generates an MD5 hash for the combined content of two files: "user.txt" and "property.txt".
     * It reads the content of both files concurrently, calculates the MD5 hash, and returns the hash as a hexadecimal string.
     *
     * @return The MD5 hash of the combined content of "user.txt" and "property.txt" as a hexadecimal string.
     * @throws IOException              If an I/O error occurs while reading the files.
     * @throws NoSuchAlgorithmException If the MD5 algorithm is not available.
     */
    public static String createMD5() throws IOException, NoSuchAlgorithmException {
        try (FileInputStream fileInputStream1 = new FileInputStream("user.txt");
             FileInputStream fileInputStream2 = new FileInputStream("property.txt");
             BufferedInputStream bufferedInputStream1 = new BufferedInputStream(fileInputStream1);
             BufferedInputStream bufferedInputStream2 = new BufferedInputStream(fileInputStream2)) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];   // 8 KB buffer

            int bytesRead;
            while ((bytesRead = bufferedInputStream1.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            while ((bytesRead = bufferedInputStream2.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            byte[] totalBuffer = byteArrayOutputStream.toByteArray();

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(totalBuffer);

            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(Integer.toHexString(0xFF & b));
            }

            return result.toString();
        }
    }
}
