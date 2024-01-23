package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DataStorage class provides methods to interact with a database and perform
 * operations related to user and property data for a BASIC system.
 *
 *  @author Mehmet Fatih Çelik
 *  @version JDK 17.0.3
 */
public class DataStorage{
    public BASIC b;
    public Connection conn;

    /**
     * Constructs a new DataStorage with the specified BASIC object
     * and database connection.
     *
     * @param b    The BASIC object to associate with the data storage.
     * @param conn The database connection to be used by the data storage.
     */
    public DataStorage(BASIC b, Connection conn) {
        this.b = b;
        this.conn = conn;
    }

    /**
     * Removes a user from the database based on the given user ID.
     *
     * @param userId The ID of the user to be removed.
     * @throws SQLException If a database access error occurs or the SQL execution fails.
     */
    public void removeUserFromDb(int userId) throws SQLException {
        PreparedStatement preparedStatement = this.conn.prepareStatement("DELETE FROM user WHERE userID=?");
        preparedStatement.setString(1,String.valueOf(userId));
        preparedStatement.executeUpdate();
    }

    /**
     * Removes a property from the database based on the given property ID.
     *
     * @param propertyId The ID of the property to be removed.
     * @throws SQLException If a database access error occurs or the SQL execution fails.
     */
    public void removePropertyFromDb(int propertyId) throws SQLException {
        PreparedStatement preparedStatement = this.conn.prepareStatement("DELETE FROM property WHERE propertyID=?");
        preparedStatement.setString(1,String.valueOf(propertyId));
        preparedStatement.executeUpdate();
    }

    /**
     * Fetches user data from the database and populates the BASIC system's user list.
     *
     * @throws SQLException If a database access error occurs or the SQL execution fails.
     */
    public void fetchUserFromDb() throws SQLException {
        PreparedStatement preparedStatement = this.conn.prepareStatement("SELECT * FROM user");
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()) {
            if(rs.getString(6).equals("h")) {
                Host h = new Host(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(3), rs.getString(4), rs.getDouble(8));
                this.b.users.add(h);
            }
            else if (rs.getString(6).equals("g")) {

                Gold g = new Gold(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(3), rs.getString(4), rs.getString(7), rs.getInt(8));
                this.b.users.add(g);
            }
            else {
                Standard sd = new Standard(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getDate(5) .toLocalDate(), rs.getString(3), rs.getString(4), rs.getString(7));
                this.b.users.add(sd);
            }
        }
    }

    /**
     * Fetches property data from the database and populates the BASIC system's property list.
     *
     * @throws SQLException If a database access error occurs or the SQL execution fails.
     */
    public void fetchPropertyFromDb() throws SQLException {
        PreparedStatement preparedStatement = this.conn.prepareStatement("SELECT * FROM property");
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            double pricePerDaydb = rs.getDouble(5);
            float pricePerDay = (float) pricePerDaydb;

            if(rs.getString(6).equals("f")) {
                FullProperty fp = new FullProperty(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), pricePerDay, rs.getDouble(7));
                this.b.properties.add(fp);
            }
            else {
                SharedProperty sp = new SharedProperty(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), pricePerDay);
                this.b.properties.add(sp);
            }
        }
    }

    /**
     * Adds a user to the database based on the given User object.
     *
     * @param user The User object to be added to the database.
     * @throws SQLException If a database access error occurs or the SQL execution fails.
     */
    public void addUserToDb(User user) throws SQLException{
        if (user instanceof Host) {
            PreparedStatement preparedStatement= this.conn.prepareStatement("INSERT INTO user(userID, dateOfBirth, firstName, lastName, registrationDate, type, taxNumber)" + "VALUES(?,?,?,?,?,?,?)");
            Host host = (Host) user;

            preparedStatement.setString(1,Integer.toString(host.getUserId()));
            preparedStatement.setString(2, host.getDateOfBirth().toString());
            preparedStatement.setString(3, host.getFirstName());
            preparedStatement.setString(4, host.getLastName());
            preparedStatement.setString(5, host.getRegistrationDate().toString());
            preparedStatement.setString(6,"h");
            preparedStatement.setString(7,Double.toString(host.getTaxNumber()));

            preparedStatement.executeUpdate();

        }
        else if (user instanceof Standard) {
            PreparedStatement preparedStatement = this.conn.prepareStatement("INSERT INTO user(userID, dateOfBirth, firstName, lastName, registrationDate, type, preferredPaymentMethod)" + "VALUES(?,?,?,?,?,?,?)");
            Standard sd = (Standard) user;

            preparedStatement.setString(1,Integer.toString(sd.getUserId()));
            preparedStatement.setString(2, sd.getDateOfBirth().toString());
            preparedStatement.setString(3, sd.getFirstName());
            preparedStatement.setString(4, sd.getLastName());
            preparedStatement.setString(5, sd.getRegistrationDate().toString());
            preparedStatement.setString(6,"s");
            preparedStatement.setString(7, sd.getPreferredPaymentMethod());

            preparedStatement.executeUpdate();

        }
        else {
            PreparedStatement preparedStatement = this.conn.prepareStatement("INSERT INTO user(userID, dateOfBirth, firstName, lastName, registrationDate, type, preferredPaymentMethod, goldLevel)" + "VALUES(?,?,?,?,?,?,?,?)");
            Gold g = (Gold) user;

            preparedStatement.setString(1,Integer.toString(g.getUserId()));
            preparedStatement.setString(2, g.getDateOfBirth().toString());
            preparedStatement.setString(3, g.getFirstName());
            preparedStatement.setString(4, g.getLastName());
            preparedStatement.setString(5, g.getRegistrationDate().toString());
            preparedStatement.setString(6,"g");
            preparedStatement.setString(7, g.getPreferredPaymentMethod());
            preparedStatement.setString(8,Integer.toString(g.getGoldLevel()));

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Adds a property to the database based on the given Property object.
     *
     * @param property The Property object to be added to the database.
     * @throws SQLException If a database access error occurs or the SQL execution fails.
     */
    public void addPropertyToDb(Property property) throws SQLException {
        PreparedStatement preparedStatement;
        if (property instanceof SharedProperty) {
            preparedStatement = this.conn.prepareStatement("INSERT INTO property(propertyID, noBedRooms, noRooms, city, pricePerDay, type)" + "VALUES(?,?,?,?,?,?)");
            SharedProperty sp = (SharedProperty) property;

            preparedStatement.setString(1, Integer.toString(sp.getPropertyId()));
            preparedStatement.setString(2,Integer.toString(sp.getNoBedRooms()));
            preparedStatement.setString(3,Integer.toString(sp.getNoRooms()));
            preparedStatement.setString(4, sp.getCity());
            preparedStatement.setString(5, Double.toString(sp.getPricePerDay()));
            preparedStatement.setString(6,"s");

        } else {
            preparedStatement = this.conn.prepareStatement("INSERT INTO property(propertyID, noBedRooms, noRooms, city, pricePerDay, type, propertySize)" + "VALUES(?,?,?,?,?,?,?)");
            FullProperty fp = (FullProperty) property;

            preparedStatement.setString(1, Integer.toString(fp.getPropertyId()));
            preparedStatement.setString(2,Integer.toString(fp.getNoBedRooms()));
            preparedStatement.setString(3,Integer.toString(fp.getNoRooms()));
            preparedStatement.setString(4, fp.getCity());
            preparedStatement.setString(5, Double.toString(fp.getPricePerDay()));
            preparedStatement.setString(6,"f");
            preparedStatement.setString(7, Double.toString(fp.getPropertySize()));

        }
        preparedStatement.executeUpdate();
    }
}
