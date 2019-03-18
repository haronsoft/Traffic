/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsystem.Controllers;

import Database.connectDatabase;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author trojan101
 */
public class RegisterUsers implements Initializable {

    @FXML
    private TextField gender;
    @FXML
    private Button submit;
    @FXML
    public TextField title;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField contacts;
    @FXML
    private TextField station;
    private RadioButton cbmale;
    private RadioButton cbfemale;
    
    
    @FXML
    public  ComboBox<String> comboBoxDays;
    ObservableList<String> days = FXCollections.observableArrayList("Monday", "Teusday", "Wednesday", "Thursday","Friday","Saturday","Sunday");
    @FXML
    private Label day;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxDays.setItems(days);
    }

    @FXML
    private void registerUsers(ActionEvent event) {

        connectDatabase connectdb = new connectDatabase();
        connectdb.createConnection();

        catchUserDetails();

        sayHi();
    }

    public void sayHi() {
        System.out.println("Testing");
    }

    public void catchUserDetails() {

        String dbName = "TrafficSystem";
        String userName = "root";
        String password = "";

        try {

            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            JOptionPane.showMessageDialog(null, "Connected to database server");

            PreparedStatement preparedStatement;
            String query = "INSERT INTO `newusers` (title,firstName,lastName,contacts,gender,station,day) values (?,?,?,?,?,?,?)";

            preparedStatement = (PreparedStatement) connection.prepareStatement(query);

            preparedStatement.setString(1, title.getText());
            preparedStatement.setString(2, firstName.getText());
            preparedStatement.setString(3, lastName.getText());
            preparedStatement.setString(4, contacts.getText());
            preparedStatement.setString(5, gender.getText());
            preparedStatement.setString(6, station.getText());
             preparedStatement.setString(7, comboBoxDays.getValue());
            
            //comboBoxDays.setItems(days);

            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, " Data Saved Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(RegisterUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void getComboBoxValue(ActionEvent event) {
       
        System.out.println(""+ comboBoxDays.getValue());
    }

  
}
