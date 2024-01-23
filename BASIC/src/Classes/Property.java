package Classes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * The `Classes.Property` class represents a property available for booking, such as a rental accommodation.
 *
 * Each property has a unique property ID, information about the number of bedrooms, rooms, city location, price per day,
 * and an optional host who manages the property.
 *
 * This class is designed to be extended by subclasses like Classes.SharedProperty and Classes.FullProperty.
 *
 * @author Mehmet Fatih Çelik
 * @version JDK 17.0.3
 */
public abstract class Property implements PropertyPrice, Comparable{
    private int propertyId;
    private int noBedRooms;
    private int noRooms;
    private String city;
    private float pricePerDay;
    private Host host;

    private HashMap<LocalDate, String> Inspection = new HashMap<>();

    /**
     * Constructs an undefined property with default values.
     */
    Property() {
        propertyId = -1;
        noRooms = -1;
        noBedRooms = -1;
        city = "Undefined";
        pricePerDay = -1;
    }

    /**
     * Constructs a property with the specified property ID, number of bedrooms, number of rooms, city, and price per day.
     *
     * @param propertyId The unique property ID.
     * @param noBedRooms The number of bedrooms in the property.
     * @param noRooms The total number of rooms in the property.
     * @param city The city where the property is located.
     * @param pricePerDay The price per day for booking the property.
     */
     Property(int propertyId, int noBedRooms, int noRooms, String city, float pricePerDay) {
        this.propertyId = propertyId;
        this.noBedRooms = noBedRooms;
        this.noRooms = noRooms;
        this.city = city;
        this.pricePerDay = pricePerDay;
    }

    /**
     * Constructs a property with the specified property ID, number of bedrooms, number of rooms, city, price per day, and host.
     *
     * @param propertyId The unique property ID.
     * @param noBedRooms The number of bedrooms in the property.
     * @param noRooms The total number of rooms in the property.
     * @param city The city where the property is located.
     * @param pricePerDay The price per day for booking the property.
     * @param host The host managing the property.
     */
     Property(int propertyId, int noBedRooms, int noRooms, String city, float pricePerDay, Host host) {
        this.propertyId = propertyId;
        this.noBedRooms = noBedRooms;
        this.noRooms = noRooms;
        this.city = city;
        this.pricePerDay = pricePerDay;
        this.host = host;
    }

    /**
     * Compares this property to another property based on their calculated price per day.
     *
     * @param other The property to compare to.
     * @return 1 if this property has a higher price per day, -1 if lower, 0 if equal.
     */
    @Override
    public int compareTo(Object other) {
         Property otherr = (Property) other; // Cast to property
        if (this.calculatePricePerDay() > otherr.calculatePricePerDay())
            return 1;
        else if (this.calculatePricePerDay() < otherr.calculatePricePerDay())
            return -1;
        else
            return 0;
    }

    /**
     * Gets the price per day for booking the property.
     *
     * @return The price per day.
     */
    @Override
    public double calculatePricePerDay() { // This method will be overwritten in Shared property and full property
        return  pricePerDay;
    }

    /**
     * Returns a string representation of the property.
     *
     * @return A string representation including property information.
     */
    @Override
    public String toString() {
         return "\nProperty id: " + propertyId +
                 "\nNumber of bedrooms: " + noBedRooms +
                 "\nNumber of rooms: " + noRooms +
                 "\nCity: " + city +
                 "\nPrice per day: " + pricePerDay;
    }

    /**
     * Get the unique property ID.
     *
     * @return The unique property ID.
     */
    public int getPropertyId() {
        return propertyId;
    }

    /**
     * Set the unique property ID.
     *
     * @param propertyId The new unique property ID to set.
     */
    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * Get the number of bedrooms in the property.
     *
     * @return The number of bedrooms.
     */
    public int getNoBedRooms() {
        return noBedRooms;
    }

    /**
     * Set the number of bedrooms in the property.
     *
     * @param noBedRooms The new number of bedrooms to set.
     */
    public void setNoBedRooms(int noBedRooms) {
        this.noBedRooms = noBedRooms;
    }

    /**
     * Get the total number of rooms in the property.
     *
     * @return The total number of rooms.
     */
    public int getNoRooms() {
        return noRooms;
    }

    /**
     * Set the total number of rooms in the property.
     *
     * @param noRooms The new total number of rooms to set.
     */
    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    /**
     * Get the city where the property is located.
     *
     * @return The city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the city where the property is located.
     *
     * @param city The new city name to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the price per day for booking the property.
     *
     * @return The price per day.
     */
    public float getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Set the price per day for booking the property.
     *
     * @param pricePerDay The new price per day to set.
     */
    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    /**
     * Get the host managing the property.
     *
     * @return The host, may be null if no host is specified.
     */
    public Host getHost() {
        return host;
    }

    /**
     * Set the host managing the property.
     *
     * @param host The new host to set.
     */
    public void setHost(Host host) {
        this.host = host;
    }

    /**
     * Get the inspection dates and comments associated with the property.
     *
     * @return The map of inspection dates and comments.
     */
    public HashMap<LocalDate, String> getInspection() {
        return Inspection;
    }

    /**
     * Set the inspection dates and comments associated with the property.
     *
     * @param inspection The new map of inspection dates and comments to set.
     */
    public void setInspection(HashMap<LocalDate, String> inspection) {
        Inspection = inspection;
    }

    /**
     * Exports the data represented by the implementing class to a default file. The data is
     * converted to a string representation using the specific implementation's logic, and
     * the resulting string is written to the default file. If the file already exists, the
     * data is appended to the end of the file.
     *
     * @throws IOException If an I/O error occurs while writing to the default file.
     */
    public abstract void exportToFile() throws IOException;

    /**
     * Exports the data represented by the implementing class to the specified file path.
     * The data is converted to a string representation using the specific implementation's
     * logic, and the resulting string is written to the specified file. If the file already
     * exists, the data is appended to the end of the file.
     *
     * @param path The file path where the data should be exported.
     * @throws IOException If an I/O error occurs while writing to the specified file.
     */
    public abstract void exportToFile(String path) throws IOException;
}
