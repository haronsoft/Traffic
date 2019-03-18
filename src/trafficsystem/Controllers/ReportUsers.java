/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsystem.Controllers;

import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author trojan101
 */
public class ReportUsers implements Initializable {

    @FXML
    private Button reportusers;
    @FXML
    private TextField title;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField area;
    @FXML
    private TextField contacts;
    @FXML
    private ComboBox<String> comboBoxDays;
    ObservableList<String> days = FXCollections.observableArrayList("Monday", "Teusday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxDays.setItems(days);
    }

    @FXML
    private void reportUers(ActionEvent event) {
        //JOptionPane.showMessageDialog( null, "reportUsers");

        String dbName = "TrafficSystem";
        String userName = "root";
        String password = "";

        try {

            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            //JOptionPane.showMessageDialog(null, "Connected to database server");
            PreparedStatement preparedStatement;
            String query = "INSERT INTO `reportedusers` (title,firstName,lastName,area,contacts,day) values (?,?,?,?,?,?)";

            preparedStatement = (PreparedStatement) connection.prepareStatement(query);

            preparedStatement.setString(1, title.getText());
            preparedStatement.setString(2, firstName.getText());
            preparedStatement.setString(3, lastName.getText());
            preparedStatement.setString(4, area.getText());
            preparedStatement.setString(5, contacts.getText());
            preparedStatement.setString(6, comboBoxDays.getValue());

            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, " Data Saved Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(RegisterUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
