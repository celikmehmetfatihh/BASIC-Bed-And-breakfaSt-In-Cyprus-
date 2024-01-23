package Classes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Classes.User class represents a user who can make bookings for properties.
 *
 * Each user has a unique user ID, a date of birth, a first name, a last name, and registration date.
 * The class is abstract and serves as a base class for specific user types.
 *
 * @author Mehmet Fatih Çelik
 * @version JDK 17.0.3
 */
public abstract class User {
    private int userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate registrationDate;

    /**
     * Constructs an undefined user with default values.
     */
    User(){
        userId = -1;
        firstName = "Undefined";
        lastName = "Undefined";
    }

    /**
     * Constructs a user with the specified user ID, date of birth, registration date, first name, and last name.
     *
     * @param userId The unique user ID.
     * @param dateOfBirth The date of birth of the user.
     * @param registrationDate The registration date of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     */
    User(int userId, LocalDate dateOfBirth, LocalDate registrationDate, String firstName, String lastName) {
        this.userId = userId;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructs a user with the specified user ID, date of birth, first name, last name, and a list of bookings.
     *
     * @param userId The unique user ID.
     * @param dateOfBirth The date of birth of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     */
     User(int userId, LocalDate dateOfBirth, String firstName, String lastName) {
        this.userId = userId;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns a string representation of the user, including user information and registration date.
     *
     * @return A string representation.
     */


    /**
     * Get the unique user ID.
     *
     * @return The unique user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set the unique user ID.
     *
     * @param userId The new unique user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Get the date of birth of the user.
     *
     * @return The date of birth.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Set the date of birth of the user.
     *
     * @param dateOfBirth The new date of birth to set.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Get the first name of the user.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of the user.
     *
     * @param firstName The new first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of the user.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of the user.
     *
     * @param lastName The new last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * Get the registration date of the user.
     *
     * @return The registration date of the user. May be null if not specified.
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Set the registration date of the user.
     *
     * @param registrationDate The new registration date to set.
     */
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
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
    public abstract void exportToFile(String path) throws IOException;

    /**
     * Exports the data of the object to the default file "sharedProperty.dat". The object's data is
     * converted to a string representation using its {@code toString} method, and the resulting
     * string is written to the file. If the file already exists, the data is appended to the end
     * of the file.
     *
     * @throws IOException If an I/O error occurs while writing to the default file.
     */
    public abstract void exportToFile() throws IOException;

}
