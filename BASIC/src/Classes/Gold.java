package Classes;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a Classes.Gold level customer, extending the Classes.Customer class.
 * It includes information about the gold level and implements a discount percentage based on the level.
 *
 *  @author Mehmet Fatih Çelik
 *  @version JDK 17.0.3
 */
public class Gold extends Customer {
    private  int goldLevel;

    /**
     * Default constructor for a Classes.Gold customer.
     * Sets the gold level to 0.
     */
    Gold() {
        this.goldLevel = 0;
    }

    /**
     * Parameterized constructor for a Classes.Gold customer.
     *
     * @param userId               The ID of the user.
     * @param dateOfBirth          The date of birth of the user.
     * @param registrationDate     The registration date of the user.
     * @param firstName            The first name of the user.
     * @param lastName             The last name of the user.
     * @param preferredPaymentMethod The preferred payment method of the customer.
     * @param goldLevel            The gold level of the customer.
     */
    public Gold(int userId, LocalDate dateOfBirth, LocalDate registrationDate, String firstName, String lastName, String preferredPaymentMethod, int goldLevel) {
        super(userId, dateOfBirth, registrationDate, firstName, lastName, preferredPaymentMethod);
        this.goldLevel = goldLevel;
    }

    /**
     * Creates a new instance of the Classes.Gold class with the specified parameters.
     *
     * @param userId        The unique identifier for the user.
     * @param dateOfBirth   The date of birth of the user.
     * @param firstName     The first name of the user.
     * @param lastName      The last name of the user.
     * @param bookings      The list of bookings associated with the user.
     */
    public Gold(int userId, LocalDate dateOfBirth, String firstName, String lastName, ArrayList<Booking> bookings) {
        super(userId, dateOfBirth, firstName, lastName, bookings);
    }

    /**
     * Returns a string representation of the Classes.Gold customer.
     *
     * @return A string representation including user information and gold level.
     */
    @Override
    public String toString() {
        return "User Type: Gold Customer\n" +
                "User id: " + this.getUserId() +"\n"+
                "First name: " + this.getFirstName() +"\n"+
                "Last name: " + this.getLastName() +"\n" +
                "Date of birth: " + this.getDateOfBirth()+"\n"+
                "Registration date: " + this.getRegistrationDate() +"\n"+
                "Payment Method: " + this.getPreferredPaymentMethod() +"\n"+
                "Level: " + this.getGoldLevel() + "\n";
    }

    /**
     * Gets the gold level of the customer.
     *
     * @return The gold level.
     */
    public int getGoldLevel() {
        return goldLevel;
    }

    /**
     * Sets the gold level of the customer.
     *
     * @param goldLevel The new gold level to set.
     */
    public void setGoldLevel(int goldLevel) {
        this.goldLevel = goldLevel;
    }

    /**
     * Calculates the discount percentage based on the gold level.
     *
     * @return The discount percentage.
     */
    @Override
    public double discountPercentage() {
        if (goldLevel == 1) // if it is a level 1, then the discount is 1%
            return 0.01;
        else if (goldLevel == 2) //  if it is level 2 then the discount is 2%
            return 0.02;
        else
            return 0.03; // if it is level 3 then the discount is 3%
    }

    /**
     * Exports the data of the object to the specified file path. The object's data is converted
     * to a string representation using its {@code toString} method, and the resulting string
     * is written to the file. If the file already exists, the data is appended to the end of
     * the file.
     *
     * @param path The file path where the data should be exported.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Override
    public void exportToFile(String path) throws IOException {
        String data = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path, true)));
        out.writeUTF(data);
        out.close();
    }

    /**
     * Exports the data of the object to the default file "gold.dat". The object's data is
     * converted to a string representation using its {@code toString} method, and the resulting
     * string is written to the file. If the file already exists, the data is appended to the end
     * of the file.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Override
    public void exportToFile() throws IOException {
        String data = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("gold.dat", true)));
        out.writeUTF(data);
        out.close();

    }
}
