package Classes;

import Classes.User;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

/**
 * The `Classes.Host` class represents a host who provides properties for booking.
 *
 * A host has a unique host ID, first name, last name, and optional registration date.
 * Hosts can offer one or more properties for booking.
 *
 * @author Mehmet Fatih Çelik
 * @version JDK 17.0.3
 */
public class Host extends User {
    private double taxNumber;

    /**
     * Constructs an undefined host with default values.
     */
    Host() {
        taxNumber = 0.0;
    }

    /**
     * Constructs a host with the specified tax number.
     *
     * @param taxNumber The tax number of the host.
     */
    public Host(double taxNumber) {
        this.taxNumber = taxNumber;
    }

    /**
     * Constructs a host with the specified host ID, first name, last name, and registration date.
     *
     * @param userId The unique user ID of the host.
     * @param dateOfBirth The date of birth of the host.
     * @param registrationDate The registration date of the host.
     * @param firstName The first name of the host.
     * @param lastName The last name of the host.
     * @param taxNumber The tax number of the host.
     */
    public Host(int userId, LocalDate dateOfBirth, LocalDate registrationDate, String firstName, String lastName, double taxNumber) {
        super(userId, dateOfBirth, registrationDate, firstName, lastName);
        this.taxNumber = taxNumber;
    }

    /**
     * Returns a string representation of the host.
     *
     * @return A string representation including user information and tax number.
     */
    public String toString() {
        return "User Type: Host\n" +
                "User id: " + this.getUserId() +"\n"+
                "First name: " + this.getFirstName() +"\n"+
                "Last name: " + this.getLastName() +"\n" +
                "Date of birth: " + this.getDateOfBirth()+"\n"+
                "Registration date: " + this.getRegistrationDate() +"\n"+
                "Tax Number: " + this.getTaxNumber() + "\n";
    }

    @Override
    public void exportToFile(String path) throws IOException {
        String data = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path, true)));
        out.writeUTF(data);
        out.close();
    }

    @Override
    public void exportToFile() throws IOException {
        String data = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("host.dat", true)));
        out.writeUTF(data);
        out.close();

    }

    /**
     * Gets the tax number of the host.
     *
     * @return The tax number.
     */
    public double getTaxNumber() {
        return taxNumber;
    }

    /**
     * Sets the tax number of the host.
     *
     * @param taxNumber The new tax number to set.
     */
    public void setTaxNumber(double taxNumber) {
        this.taxNumber = taxNumber;
    }


}
