package Classes;

import Classes.Property;

import java.time.LocalDate;
import java.time.Period;

/**
 * The `Classes.Booking` class represents a booking for a property.
 *
 * Each booking has a start date, an end date, a payment status, and is associated with a specific property.
 * Bookings are used to track reservations made by users.
 *
 * @author Mehmet Fatih Çelik
 * @version JDK 17.0.3
 */
public class Booking {
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isPaid;
    private Property property;

    /**
     * Constructs an unpaid booking.
     * By default, a booking is initialized as unpaid.
     */
    Booking() {
        isPaid = false;
    }

    /**
     * Constructs a booking with the specified start and end dates.
     *
     * @param startDate The start date of the booking.
     * @param endDate The end date of the booking.
     * @param property The property associated with this booking.
     */
    Booking(LocalDate startDate, LocalDate endDate, Property property) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.property = property;
    }

    /**
     * Constructs a booking with the specified details.
     *
     * @param startDate The start date of the booking.
     * @param endDate The end date of the booking.
     * @param isPaid The payment status of the booking (true if paid, false if unpaid).
     * @param property The property associated with this booking.
     */
    public Booking(LocalDate startDate, LocalDate endDate, boolean isPaid, Property property) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPaid = isPaid;
        this.property = property;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nStart Date: ").append(this.startDate)
                .append("\nEnd Date: ").append(this.endDate)
                .append("\nPaid Information: ");
        if (isPaid)
            sb.append("Paid\n");
        else
            sb.append("Not paid\n");

        return sb.toString();
    }

    /**
     * Get the start date of the booking.
     *
     * @return The start date of the booking.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Set the start date of the booking.
     *
     * @param startDate The new start date to set.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the end date of the booking.
     *
     * @return The end date of the booking.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Set the end date of the booking.
     *
     * @param endDate The new end date to set.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Check if the booking is paid.
     *
     * @return `true` if the booking is paid, `false` if it's unpaid.
     */
    public boolean getIsPaid() {
        return isPaid;
    }

    /**
     * Set the payment status of the booking.
     *
     * @param isPaid `true` if the booking is paid, `false` if it's unpaid.
     */
    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    /**
     * Calculate the total cost of the booking.
     *
     * The total cost is calculated based on the number of days between the start and end dates and the property's price per day.
     *
     * @return The total cost of the booking.
     */
    public double totalCost() {
        Period period = Period.between(startDate, endDate);
        int days = period.getDays();
        int months = period.getMonths();
        int years = period.getYears();

        double totalCost = (years * 365 + months * 30 + days) * property.calculatePricePerDay();

        return totalCost;
    }

    /**
     * Get the property associated with this booking.
     *
     * @return The property associated with this booking.
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Set the property associated with this booking.
     *
     * @param property The property to associate with this booking.
     */
    public void setProperty(Property property) {
        this.property = property;
    }
}
