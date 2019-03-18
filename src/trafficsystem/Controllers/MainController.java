/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsystem.Controllers;

import Model.catchDetails;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author trojan101
 */
public class MainController implements Initializable {

    @FXML
    private MenuItem closeapp;
    @FXML
    private Button refresh;
    @FXML
    private TableView<catchDetails> tableUser;
    @FXML
    private TableColumn<catchDetails, String> columnfisrtName;
    @FXML
    private TableColumn<catchDetails, String> columnlastName;
    @FXML
    private TableColumn<catchDetails, String> columnarea;
    @FXML
    private TableColumn<catchDetails, String> columncontacts;

    //Obeservable List
    private ObservableList<catchDetails> data;

    @FXML
    private TableColumn<catchDetails, String> columntitle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // cDatabase.createConnection();

    }

    @FXML
    public void closeApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
        System.out.println("Closing the system");
    }

    @FXML
    private void openAdminLoginForm(ActionEvent event) {
        Parent root = null;
        try {
            //root = FXMLLoader.load(getClass().getResource("/Views/Internet.fxml"));
            root = FXMLLoader.load(getClass().getResource("/Views/login.fxml"));
        } catch (IOException ex) {
            System.out.println("Error occurred when loading fxml resourse");
        }
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void openStaffLoginForm(ActionEvent event) {
        System.out.println("Staff log");
    }

    private void openFstaffLoginForm(ActionEvent event) {
        System.out.println("FFFFFF staff");
    }

    @FXML
    private void reportView(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/Report.fxml"));
        } catch (IOException ex) {
            System.out.println("Error occurred when loading fxml resourse");
        }
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void loadDataToTableView(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "loading data from database ,......");
        try {
            String dbName = "TrafficSystem";
            String userName = "root";
            String password = "";

            Connection connection;
            data = FXCollections.observableArrayList();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            //JOptionPane.showMessageDialog(null, "Connected to database server" + dbName + " " + "database");

            //Execute Query and store data in a resulset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM reportedusers");

            while (rs.next()) {

                data.add(new catchDetails(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

            }
        } catch (SQLException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        columntitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnfisrtName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnlastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnarea.setCellValueFactory(new PropertyValueFactory<>("area"));
        columncontacts.setCellValueFactory(new PropertyValueFactory<>("contacts"));

        //tableUser.setItems(null);
        tableUser.setItems(data);

    }

    @FXML
    private void testingBrowser(ActionEvent event) {
        Parent root = null;
        try {
            //root = FXMLLoader.load(getClass().getResource("/Views/Internet.fxml"));
            root = FXMLLoader.load(getClass().getResource("/Views/Browser.fxml"));
        } catch (IOException ex) {
            System.out.println("Error occurred when loading fxml resourse");
        }
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void UpdateUserDetails(MouseEvent event) {

        System.out.println("Something");
    }
}
