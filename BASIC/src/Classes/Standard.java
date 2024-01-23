package Classes;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Classes.Standard class represents a standard level customer, extending the Classes.Customer class.
 * It includes information about the registration date and implements a discount percentage based on the number of years
 * the customer has been registered.
 *
 * Classes.Standard customers receive a discount if they have been registered for 10 years or more.
 *
 * @author Mehmet Fatih Celik
 * @version JDK 17.0.3
 */
public class Standard extends Customer {

    /**
     * Constructs a standard level customer with the specified parameters.
     *
     * @param userId               The ID of the user.
     * @param dateOfBirth          The date of birth of the user.
     * @param registrationDate     The registration date of the user.
     * @param firstName            The first name of the user.
     * @param lastName             The last name of the user.
     * @param preferredPaymentMethod The preferred payment method of the customer.
     */
    public Standard(int userId, LocalDate dateOfBirth, LocalDate registrationDate, String firstName, String lastName, String preferredPaymentMethod) {
        super(userId, dateOfBirth, registrationDate, firstName, lastName, preferredPaymentMethod);
    }

    /**
     * Creates a new instance of the Classes.Standard class with the specified parameters.
     *
     * @param userId        The unique identifier for the standard user.
     * @param dateOfBirth   The date of birth of the standard user.
     * @param firstName     The first name of the standard user.
     * @param lastName      The last name of the standard user.
     * @param bookings      The list of bookings associated with the standard user.
     */
    public Standard(int userId, LocalDate dateOfBirth, String firstName, String lastName, ArrayList<Booking> bookings) {
        super(userId, dateOfBirth, firstName, lastName, bookings);
    }

    public Standard() {

    }

    /**
     * Returns a string representation of the standard level customer.
     *
     * @return A string representation including user information.
     */


    @Override
    public String toString() {
        return "User Type: Standard Customer\n" +
                "User id: " + this.getUserId() +"\n"+
                "First name: " + this.getFirstName() +"\n"+
                "Last name: " + this.getLastName() +"\n" +
                "Date of birth: " + this.getDateOfBirth()+"\n"+
                "Registration date: " + this.getRegistrationDate() +"\n"+
                "Payment Method: " + this.getPreferredPaymentMethod() +"\n";
    }

    /**
     * Calculates the discount percentage for the standard level customer based on the number of years registered.
     * If the customer has been registered for 10 years or more, a 2% discount is provided; otherwise, no discount is applied.
     *
     * @return The discount percentage.
     */
    @Override
    public double discountPercentage() {
        int registrationYear = getRegistrationDate().getYear();
        int difference = LocalDate.now().getYear() - registrationYear; // Calculating how many years standard customer is registered

        if (difference >= 10) // if Classes.Standard customer registered more than 10 years (including), then get 2% discount
            return 0.02;
        else
            return 0; // no discount
    }

    /**
     * Exports the data of the object to the specified file path. The object's data is converted
     * to a string representation using its {@code toString} method, and the resulting string
     * is written to the file. If the file already exists, the data is appended to the end of
     * the file.
     *
     * @param path The file path where the data should be exported.
     * @throws IOException If an I/O error occurs while writing to the specified file.
     */
    @Override
    public void exportToFile(String path) throws IOException {
        String data = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path, true)));
        out.writeUTF(data);
        out.close();
    }

    /**
     * Exports the data of the object to the default file "standard.dat". The object's data is
     * converted to a string representation using its {@code toString} method, and the resulting
     * string is written to the file. If the file already exists, the data is appended to the end
     * of the file.
     *
     * @throws IOException If an I/O error occurs while writing to the default file.
     */
    @Override
    public void exportToFile() throws IOException {
        String data = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("standard.dat", true)));
        out.writeUTF(data);
        out.close();

    }
}
