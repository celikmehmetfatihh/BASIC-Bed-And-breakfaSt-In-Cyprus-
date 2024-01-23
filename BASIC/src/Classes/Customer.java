package Classes;

import Classes.User;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * An abstract class representing a customer, extending the Classes.User class.
 * It contains information about the customer, including preferred payment method and bookings.
 *
 *  @author Mehmet Fatih Çelik
 *  @version JDK 17.0.3
 */
public abstract class Customer extends User {
    private String preferredPaymentMethod;
    private ArrayList<Booking> bookings = new ArrayList<Booking>();

    /**
     * Default constructor for a Classes.Customer.
     * Sets the preferred payment method to "None".
     */
    Customer() {
        this.preferredPaymentMethod = "None";
    }

    /**
     * Parameterized constructor for a Classes.Customer.
     *
     * @param userId             The ID of the user.
     * @param dateOfBirth        The date of birth of the user.
     * @param registrationDate   The registration date of the user.
     * @param firstName          The first name of the user.
     * @param lastName           The last name of the user.
     * @param preferredPaymentMethod The preferred payment method of the customer.
     */
    Customer(int userId, LocalDate dateOfBirth, LocalDate registrationDate, String firstName, String lastName, String preferredPaymentMethod) {
        super(userId, dateOfBirth, registrationDate, firstName, lastName);
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    /**
     * Constructs a new Classes.Customer object with the specified parameters.
     *
     * @param userId The unique identifier of the customer.
     * @param dateOfBirth The date of birth of the customer.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param bookings The list of bookings made by the customer.
     */
    public Customer(int userId, LocalDate dateOfBirth, String firstName, String lastName, ArrayList<Booking> bookings) {
        super(userId, dateOfBirth, firstName, lastName);
        this.bookings = bookings;
    }

    /**
     * Returns a string representation of the Classes.Customer.
     *
     * @return A string representation including user information and preferred payment method.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nPreferred payment method: " + preferredPaymentMethod;
    }

    /**
     * Gets the preferred payment method of the customer.
     *
     * @return The preferred payment method.
     */
    public String getPreferredPaymentMethod() {
        return preferredPaymentMethod;
    }

    /**
     * Sets the preferred payment method of the customer.
     *
     * @param preferredPaymentMethod The new preferred payment method to set.
     */
    public void setPreferredPaymentMethod(String preferredPaymentMethod) {
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    /**
     * Get the list of bookings made by the user.
     *
     * @return The list of bookings.
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Set the list of bookings made by the user.
     *
     * @param bookings The new list of bookings to set.
     */
    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Provides the discount percentage applicable to the customer.
     *
     * @return The discount percentage.
     */
    public abstract double discountPercentage();

}
