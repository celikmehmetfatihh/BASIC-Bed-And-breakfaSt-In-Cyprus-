package Classes;

import Classes.Property;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * Represents a full property, extending the Classes.Property class.
 * It includes information about the property size and implements additional pricing logic based on property size.
 *
 *  @author Mehmet Fatih Çelik
 *  @version JDK 17.0.3
 */
public class FullProperty extends Property {
    private  double propertySize;

    /**
     * Constructs a Classes.FullProperty with the specified parameters.
     *
     * @param propertyId    The unique ID of the property.
     * @param noBedRooms    The number of bedrooms in the property.
     * @param noRooms       The total number of rooms in the property.
     * @param city          The city where the property is located.
     * @param pricePerDay   The price per day for renting the property.
     * @param propertySize  The size of the property in square meters.
     */
    public FullProperty(int propertyId, int noBedRooms, int noRooms, String city, float pricePerDay, double propertySize) {
        super(propertyId, noBedRooms, noRooms, city, pricePerDay);
        this.propertySize = propertySize;
    }

    public FullProperty(int propertyId, int noBedRooms, int noRooms, String city, float pricePerDay, Host host, double propertySize) {
        super(propertyId, noBedRooms, noRooms, city, pricePerDay, host);
        this.propertySize = propertySize;
    }

    /**
     * Creates a new instance of the Classes.FullProperty class with the specified parameters.
     *
     * @param propertyId    The unique identifier for the property.
     * @param noBedRooms    The number of bedrooms in the property.
     * @param noRooms       The total number of rooms in the property.
     * @param city          The city where the property is located.
     * @param pricePerDay   The price per day to rent the property.
     */
    public FullProperty(int propertyId, int noBedRooms, int noRooms, String city, float pricePerDay) {
        super(propertyId, noBedRooms, noRooms, city, pricePerDay);
    }

    public FullProperty() {

    }

    /**
     * Returns a string representation of the Classes.FullProperty.
     *
     * @return A string representation including property information and size.
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Property type: ").append("Full")
                .append("\nProperty id: ").append(this.getPropertyId())
                .append("\nNumber of bedrooms: ").append(this.getNoBedRooms())
                .append("\nNumber of rooms: ").append(this.getNoRooms())
                .append("\nCity: ").append(this.getCity())
                .append("\nPrice per day: ").append(this.getPricePerDay())
                .append("\nProperty Size: ").append(propertySize)
                .append("\nHost Id: ").append(this.getHost().getUserId());

        // Check if there are no inspection details
        if (this.getInspection().isEmpty()) {
            sb.append("\nNo inspection details");
        } else {
            // Append inspection details
            sb.append("\nInspection details:");
            for (HashMap.Entry<LocalDate, String> entry : this.getInspection().entrySet()) {
                sb.append("\nDate: ").append(entry.getKey()).append(", Comment: ").append(entry.getValue());
            }
        }

        return sb.toString();
    }

    /**
     * Calculates the price per day for renting the property, considering additional taxes based on property size.
     * Applies different tax rates depending on the property size.
     *
     * @return The calculated price per day with applied taxes.
     */
    @Override
    public double calculatePricePerDay() {
        if (propertySize < 200) { // %1 tax will be applied if size is up to 200 square meters
            System.out.print("\n%1 tax applied!");
            return getPricePerDay() * 1.01;
        }
        else if (propertySize <= 300) {// %3 tax will be applied if size Between 200-300 (inclusive) square meters
            System.out.print("\n3% tax applied!");
            return getPricePerDay() * 1.03;
        }
        else {// %4 tax will be applied if size Above 300 square meters
            System.out.print("\n4% tax applied!");
            return getPricePerDay() * 1.04;
        }
    }

    /**
     * Gets the size of the property.
     *
     * @return The size of the property in square meters.
     */
    public double getPropertySize() {
        return propertySize;
    }

    /**
     * Sets the size of the property.
     *
     * @param propertySize The new size of the property to set.
     */
    public void setPropertySize(double propertySize) {
        this.propertySize = propertySize;
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
     * Exports the data of the object to the default file "fullProperty.dat". The object's data
     * is converted to a string representation using its {@code toString} method, and the resulting
     * string is written to the file. If the file already exists, the data is appended to the end
     * of the file.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Override
    public void exportToFile() throws IOException {
        String data = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("fullProperty.dat", true)));
        out.writeUTF(data);
        out.close();

    }
}
