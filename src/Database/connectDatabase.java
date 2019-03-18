package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class connectDatabase {

    public Connection connection;

    public Connection createConnection() {

        String dbName = "TrafficSystem";
        String userName = "root";
        String password = "";

        try {

            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            //JOptionPane.showMessageDialog(null, "Connected to database server");

        } catch (Exception e) {
            // e.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, e.getMessage());
        }

        return connection;
    }

}
