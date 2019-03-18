package trafficsystem.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class loginController implements Initializable {

    @FXML
    private JFXButton btnadminlogin;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void openSystemPage(ActionEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/MainController.fxml"));
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
    private void openUserInfo(ActionEvent event) {

        validateAdmin();

    }

    private void openAdminLogin() {
        System.out.println("Users information");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/UserDetails.fxml"));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error occurred when loading fxml resourse");
        }
    }

    private void validateAdmin() {

        final String ADMIN_USERNAME = "Tadmin";
        final String ADMIN_PASSWORD = "Soen20337";

        String usernameValue = username.getText();
        String passwordValue = password.getText();
        if (!usernameValue.equals(ADMIN_USERNAME) && !passwordValue.equals(ADMIN_PASSWORD)) {
            String errorMessage;
            errorMessage = "SORRY USERNAME OR PASSWORD MAYBE WRONG !!";
            JOptionPane.showMessageDialog(null, errorMessage);
        } else {
            openAdminLogin();
        }

    }

}
