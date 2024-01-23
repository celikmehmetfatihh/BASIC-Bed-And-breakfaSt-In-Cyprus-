package GUI;

import javax.swing.*;
import Classes.User;
import Classes.BASIC;

public class TabularHost extends  JFrame{
    private JTable table;
    private JPanel panel = new JPanel();

    /**
     * Displays a tabular view of host data using a JTable within a JFrame.
     * Columns include UserId, First Name, Last Name, Date of Birth, and Registration Date.
     * Data is populated from the provided BASIC instance.
     *
     * @author Mehmet Fatih Çelik
     * @version 1.0
     */
    public TabularHost(BASIC b) {
        this.setContentPane(panel);
        this.setTitle("Tabular Host");
        this.setVisible(true);
        this.setSize(800, 400);

        String[] columns = {"UserId", "First Name", "Last Name", "Date of Birth", "Registration Date"};
        String[][] data = new String[b.users.size()][5];

        int i=0;
        for(User user: b.users) {
            data[i][0] = Integer.toString(user.getUserId());
            data[i][1] = user.getFirstName();
            data[i][2] = user.getLastName();
            data[i][3] = user.getDateOfBirth().toString();
            data[i][4] = user.getRegistrationDate().toString();
            i++;
        }

        table = new JTable(data, columns);
        table.setSize(800, 400);
        table.setVisible(true);
        panel.add(table);
        panel.add(new JScrollPane(table));

    }
}
