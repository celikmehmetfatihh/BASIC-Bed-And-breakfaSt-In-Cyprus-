package Classes;

import java.beans.FeatureDescriptor;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.BASICMenu;

import javax.xml.crypto.Data;
import java.awt.*;

/**
 * The ThreadMenu class extends the Thread class and is designed to run concurrently
 * with other threads. It initializes a new instance of BASICMenu by passing a BASIC object
 * and starts the thread.
 */
class ThreadMenu extends Thread {
    public BASIC b;
    public ThreadMenu(BASIC b) {
        this.b = b;
        this.start();
    }
    /**
     * Overrides the run method of the Thread class. Initializes and starts a new instance
     * of BASICMenu with the associated BASIC object.
     */
    @Override
    public void run() {
        new BASICMenu(b);
    }
}

/**
 * The ThreadSecurity class extends the Thread class and is designed to run concurrently
 * with other threads. It checks the integrity of an MD5 file by comparing it with the MD5 hash generated
 * from the BASICMenu.createMD5() method.
 */
class ThreadSecurity extends Thread {
    public ThreadSecurity() {
        this.start();
    }

    /**
     * Overrides the run method of the Thread class. Checks the integrity of an MD5 file
     * by comparing it with the MD5 hash generated from the BASICMenu.createMD5() method.
     */
    @Override
    public void run() {
        File md5File = new File("md5.txt");

        if (md5File.exists()) {
            try (Scanner scanner = new Scanner(md5File)) {
                String md5 = scanner.nextLine();

                if (md5.equals(BASICMenu.createMD5())) {
                    System.out.println("This file is safe!");
                } else {
                    System.out.println("This file has been changed by third parties!");
                }

            } catch (IOException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/**
 * The `BASIC` class represents a program with user, property, and booking management functionality.
 * It includes methods for checking user and property existence, retrieving user and property details,
 * and calculating discounts for customers.
 *
 * @author Mehmet Fatih Çelik
 * @version JDK 17.0.3
 */
public class BASIC {

    public static ArrayList<User> users;

    public static ArrayList<Property> properties;

    public static Scanner input = new Scanner(System.in);
    public DataStorage dataStorage;

    /**
     * Default constructor initializes empty ArrayLists for users and properties.
     */
    public BASIC() {
        this.users = new ArrayList<>();
        this.properties = new ArrayList<>();
    }

    /**
     * Parameterized constructor that allows initializing the `BASIC` object with existing user and property lists.
     *
     * @param users List of users.
     * @param properties List of properties.
     */
    public BASIC(ArrayList<User> users, ArrayList<Property> properties) {
        this.users = users;
        this.properties = properties;
    }

    /**
     * Constructs a new instance of the `BASIC` class with an existing list of users.
     * This constructor allows initializing the `BASIC` object with a pre-existing list of users.
     *
     * @param users The ArrayList of users to be associated with the `BASIC` object.
     */
    public BASIC(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * The main method of the program.
     *
     * @param args Command-line arguments as a String array.
     */
    public static void main(String[] args) {
        // Initialize event queue and manager
        EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        queue.push(new EventManager());

        BASIC b = new BASIC();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3307/basic";
            String username = "fatihcelik";
            String password = "123456";

            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            DataStorage dataStorage1 = new DataStorage(b, conn);

            dataStorage1.fetchUserFromDb();
            dataStorage1.fetchPropertyFromDb();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        new ThreadSecurity();
        new ThreadMenu(b);
    }

    /**
     * Checks if a user with the specified user ID exists.
     *
     * @param userId The ID of the user to check.
     * @return True if the user exists, false otherwise.
     */
    public boolean isUserIdExists(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the index of a user in the user list based on their user ID.
     *
     * @param userId The ID of the user to find.
     * @return The index of the user in the list, or -1 if the user is not found.
     */
    public int getUserIndexAccordingToUserId(int userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == userId)
                return i;
        }
        return -1;
    }

    /**
     * Checks if a property with the specified property ID exists.
     *
     * @param propId The ID of the property to check.
     * @return The index of the property in the list, or -1 if the property is not found.
     */
    public int isPropertyIdExists(int propId) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getPropertyId() == propId)
                return i;
        }
        return -1;
    }

    /**
     * Checks if a user with the specified user ID is a customer.
     *
     * @param userId The ID of the user to check.
     * @return The index of the user in the list if it is a customer, -1 otherwise.
     */
    public int isUseraCustomer(int userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == userId) {
                if (users.get(i) instanceof Customer)
                    return i;
            }
        }
        return -1;
    }

    /**
     * Checks if a host with the specified host ID exists.
     *
     * @param hostId The ID of the host to check.
     * @return The index of the host in the list if found and is a host, -2 if found but not a host, -1 otherwise.
     */
    public int isHostExists(int hostId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == hostId) {
                if (users.get(i) instanceof Host) {
                    return i;
                } else
                    return -2; // it is found but not a host
            }
        }
        return -1; // not found user id
    }

    /**
     * Retrieves a user object based on the specified user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user object if found, null otherwise.
     */
    public User getUserAccordingToId(int userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == userId)
                return users.get(i);
        }
        return null;
    }

    /**
     * Retrieves a property object based on the specified property ID.
     *
     * @param propertyId The ID of the property to retrieve.
     * @return The property object if found, null otherwise.
     */
    public Property getPropertyAccordingToId(int propertyId) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getPropertyId() == propertyId)
                return properties.get(i);
        }
        return null;
    }

    /**
     * Retrieves the discount percentage for a customer based on their user ID.
     *
     * If the specified user ID belongs to a host, no discount is applicable,
     * and a message is displayed. If the user ID is not found, an error message
     * is displayed. Otherwise, the discount percentage for the customer is returned.
     *
     * @param discountUserId The user ID for which to retrieve the discount.
     * @return The discount percentage for the customer. If the user ID is invalid or belongs to a host, returns 1 (no discount).
     */
    public static double getDiscountForUser(int discountUserId) {
        // Check if the input user ID is associated with a host
        for (User u : users) {
            if (u.getUserId() == discountUserId && u instanceof Host) {
                return 0; // No discount for hosts
            } else if (u.getUserId() == discountUserId) {
                Customer customer = (Customer) u; // Cast to customer
                return customer.discountPercentage(); // Return the customer's discount percentage
            }
        }
        return 0;
    }
}
