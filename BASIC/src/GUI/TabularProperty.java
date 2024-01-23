package GUI;

import Classes.BASIC;
import Classes.Property;
import Classes.User;

import javax.swing.*;

public class TabularProperty extends JFrame {

    private JTable table;
    private JPanel panel = new JPanel();


    /**
     * Displays a tabular view of property data using a JTable within a JFrame.
     * Columns include PropertyId, No Bedrooms, No Rooms, City, and Price per Day.
     * Data is populated from the provided BASIC instance.
     *
     * @author Mehmet Fatih Çelik
     * @version 1.0
     */
    public TabularProperty(BASIC b) {
        this.setContentPane(panel);
        this.setTitle("Tabular Property");
        this.setVisible(true);
        this.setSize(800, 400);

        String[] columns = {"PropertyId", "No Bedrooms", "No Rooms", "City", "Price per Day"};
        String[][] data = new String[b.users.size()][5];

        int i=0;
        for(Property property: b.properties) {
            data[i][0] = Integer.toString(property.getPropertyId());
            data[i][1] = Integer.toString(property.getNoBedRooms());
            data[i][2] = Integer.toString(property.getNoRooms());
            data[i][3] = property.getCity();
            data[i][4] = Float.toString(property.getPricePerDay());
            i++;
        }

        table = new JTable(data, columns);
        table.setSize(800, 400);
        table.setVisible(true);
        panel.add(table);
        panel.add(new JScrollPane(table));
    }
}
