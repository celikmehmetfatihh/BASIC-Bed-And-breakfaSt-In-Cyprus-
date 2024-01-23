package Classes;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * The Classes.SharedProperty class represents a property available for booking where the price per day is calculated
 * based on the number of bedrooms, allowing multiple individuals or groups to share the cost.
 *
 * This class extends the Classes.Property class and overrides the calculatePricePerDay method to provide
 * a specific calculation for shared properties.
 *
 * @author Mehmet Fatih Celik
 * @version JDK 17.0.3
 */
public class SharedProperty extends Property {
    /**
     * Constructs a shared property with the specified parameters.
     *
     * @param propertyId The unique property ID.
     * @param noBedRooms The number of bedrooms in the property.
     * @param noRooms    The total number of rooms in the property.
     * @param city       The city where the property is located.
     * @param pricePerDay The base price per day for booking the property.
     */
    public SharedProperty(int propertyId, int noBedRooms, int noRooms, String city, float pricePerDay) {
        super(propertyId, noBedRooms, noRooms, city, pricePerDay);
    }

    /**
     * Creates a new instance of the Classes.SharedProperty class with the specified parameters.
     *
     * @param propertyId    The unique identifier for the shared property.
     * @param noBedRooms    The number of bedrooms in the shared property.
     * @param noRooms       The total number of rooms in the shared property.
     * @param city          The city where the shared property is located.
     * @param pricePerDay   The price per day to rent a portion of the shared property.
     * @param host          The host of the shared property.
     */
    public SharedProperty(int propertyId, int noBedRooms, int noRooms, String city, float pricePerDay, Host host) {
        super(propertyId, noBedRooms, noRooms, city, pricePerDay, host);
    }

    public SharedProperty() {

    }

    /**
     * Returns a string representation of the shared property.
     *
     * @return A string representation including property information.
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Property type: ").append("Shared")
                .append("\nProperty id: ").append(this.getPropertyId())
                .append("\nNumber of bedrooms: ").append(this.getNoBedRooms())
                .append("\nNumber of rooms: ").append(this.getNoRooms())
                .append("\nCity: ").append(this.getCity())
                .append("\nPrice per day: ").append(this.getPricePerDay())
                .append("\nHost Id: ").append(this.getHost().getUserId());

            for (HashMap.Entry<LocalDate, String> entry : this.getInspection().entrySet()) {
                sb.append("\nDate: ").append(entry.getKey()).append(", Comment: ").append(entry.getValue());
               }

        return sb.toString();
    }

    /**
     * Calculates the price per day for the shared property based on the number of bedrooms.
     * The cost is divided equally among the bedrooms, allowing multiple individuals or groups to share the cost.
     *
     * @return The calculated price per day for shared booking.
     */
    @Override
    public double calculatePricePerDay() {
        return getPricePerDay() / getNoBedRooms();
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
     * Exports the data of the object to the default file "sharedProperty.dat". The object's data is
     * converted to a string representation using its {@code toString} method, and the resulting
     * string is written to the file. If the file already exists, the data is appended to the end
     * of the file.
     *
     * @throws IOException If an I/O error occurs while writing to the default file.
     */
    @Override
    public void exportToFile() throws IOException {
        String data = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("sharedProperty.dat", true)));
        out.writeUTF(data);
        out.close();

    }

}
