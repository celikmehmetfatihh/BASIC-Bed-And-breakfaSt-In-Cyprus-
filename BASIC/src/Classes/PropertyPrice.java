package Classes;

/**
 * The propertyPrice interface represents the ability to calculate the price per day for a property.
 *
 * Implementing classes are expected to provide a concrete implementation for the calculatePricePerDay method.
 * This interface is typically used by classes representing properties available for booking.
 *
 * @author Mehmet Fatih Celik
 * @version JDK 17.0.3
 */
public interface PropertyPrice {
    /**
     * Calculates the price per day for the property.
     *
     * @return The calculated price per day.
     */
    public double calculatePricePerDay();
}
