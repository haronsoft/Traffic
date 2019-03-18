package trafficsystem.Controllers;

import Model.AdminCatchDetails;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.w3c.dom.Document;

public class ManageUsers implements Initializable {

    @FXML
    private Button adduser;
    @FXML
    private Button refresh;
//Obeservable List
    private ObservableList<AdminCatchDetails> data;
    @FXML
    private TableView<AdminCatchDetails> tableUsers;
    @FXML
    private TableColumn<AdminCatchDetails, String> columnId;
    @FXML
    private TableColumn<AdminCatchDetails, String> columntitle;
    @FXML
    private TableColumn<AdminCatchDetails, String> columnfirstname;
    @FXML
    private TableColumn<AdminCatchDetails, String> columnlastname;
    @FXML
    private TableColumn<AdminCatchDetails, String> columncontacts;
    @FXML
    private TableColumn<AdminCatchDetails, String> columngender;
    @FXML
    private TableColumn<AdminCatchDetails, String> columnstation;
    @FXML
    private TableColumn<AdminCatchDetails, String> columnday;
    @FXML
    private TextField userid;
    @FXML
    private TextField userTitle;
    @FXML
    private TextField userFirstName;
    @FXML
    private TextField UserLastName;
    @FXML
    private TextField userContacts;
    @FXML
    private TextField userGender;
    @FXML
    private TextField userStation;
    @FXML
    private TextField userDay;
    @FXML
    private Button deleteuserfromdb;
    @FXML
    private Button updateuserdetails;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchuserfromdb;
    @FXML
    private Button print;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void openRegisterForm(ActionEvent event) {
        System.out.println("Users information");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/Register.fxml"));
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
    private void loadDataToTAbleView(ActionEvent event) {
        // JOptionPane.showMessageDialog(null, "loading data from database ,......");
        try {
            String dbName = "TrafficSystem";
            String userName = "root";
            String password = "";

            Connection connection;
            data = FXCollections.observableArrayList();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            //JOptionPane.showMessageDialog(null, "Connected to database server" + dbName + " " + "database");

            //Execute Query and store data in a resulset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM newusers");

            while (rs.next()) {

                data.add(new AdminCatchDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));

            }
        } catch (SQLException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columntitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnfirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnlastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columncontacts.setCellValueFactory(new PropertyValueFactory<>("contacts"));
        columngender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnstation.setCellValueFactory(new PropertyValueFactory<>("station"));
        columnday.setCellValueFactory(new PropertyValueFactory<>("day"));

        //tableUser.setItems(null);
        tableUsers.setItems(data);
    }

    @FXML
    private void loadDataToFields(MouseEvent event) {

        // FETCH DATA FROM TABLE
        String newId = tableUsers.getSelectionModel().getSelectedItem().getId();
        String newTitle = tableUsers.getSelectionModel().getSelectedItem().gettitle();
        String newFirstName = tableUsers.getSelectionModel().getSelectedItem().getFistName();
        String newLastName = tableUsers.getSelectionModel().getSelectedItem().getLastName();
        String newContacts = tableUsers.getSelectionModel().getSelectedItem().getContacts();
        String newGender = tableUsers.getSelectionModel().getSelectedItem().getGender();
        String newStation = tableUsers.getSelectionModel().getSelectedItem().getStation();
        String newDay = tableUsers.getSelectionModel().getSelectedItem().getDay();

        //SET TO TEXT FIELDS
        userid.setText(newId);
        userTitle.setText(newTitle);
        userFirstName.setText(newFirstName);
        UserLastName.setText(newLastName);
        userContacts.setText(newContacts);
        userGender.setText(newGender);
        userStation.setText(newStation);
        userDay.setText(newDay);

    }

    @FXML
    private void deleteUserFromDb(ActionEvent event) {

        //  JOptionPane.showMessageDialog(null, "THis ACTION cannot be UnDone");
        try {
            String dbName = "TrafficSystem";
            String userName = "root";
            String password = "";

            Connection connection;
            PreparedStatement preparedStatement;
            ///data = FXCollections.observableArrayList();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            //JOptionPane.showMessageDialog(null, "Connected to database server" + dbName + " " + "database");

            String query = "DELETE FROM newusers WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userid.getText());
            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("User has been Deleted!.");
            alert.show();

        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateUserDetails(ActionEvent event) {

        String dbName = "TrafficSystem";
        String userName = "root";
        String password = "";

        try {

            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            // JOptionPane.showMessageDialog(null, "Connected to database server");

            com.mysql.jdbc.PreparedStatement preparedStatement;
            String query = "UPDATE `newusers` SET id =?,title = ?,firstname=?,lastname=?,contacts=?,"
                    + "gender=?,station=?,day=? WHERE id = '" + userid.getText() + "'";

            preparedStatement = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);

            preparedStatement.setString(1, userid.getText());
            preparedStatement.setString(2, userTitle.getText());
            preparedStatement.setString(3, userFirstName.getText());
            preparedStatement.setString(4, UserLastName.getText());
            preparedStatement.setString(5, userContacts.getText());
            preparedStatement.setString(6, userGender.getText().toUpperCase());
            preparedStatement.setString(7, userStation.getText());
            preparedStatement.setString(8, userDay.getText());

            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User Details Updated successfully");
            alert.setTitle("UPDATE USERS");
            alert.show();

        } catch (SQLException ex) {
            Logger.getLogger(RegisterUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void searchFromDb(ActionEvent event) {
        String dbName = "TrafficSystem";
        String userName = "root";
        String password = "";

        try {

            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            //JOptionPane.showMessageDialog(null, "Connected to database server");

            com.mysql.jdbc.PreparedStatement preparedStatement;
            String query = "SELECT * FROM `newusers` WHERE id Like '" + userid.getText() + "'";

            preparedStatement = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM newusers");

            if (rs.next()) {
                ClearFields();
                String uid = rs.getString(1);
                String utitle = rs.getString(2);
                String ufirstname = rs.getString(3);
                String ulastname = rs.getString(4);
                String ucontacts = rs.getString(5);
                String ugender = rs.getString(6);
                String ustation = rs.getString(7);
                String uday = rs.getString(8);

                  ;
                //setValues to Fields
                userid.setText(uid);
                userTitle.setText(utitle);
                userFirstName.setText(ufirstname);
                UserLastName.setText(ulastname);
                userContacts.setText(ucontacts);
                userGender.setText(ugender);
                userStation.setText(ustation);
                userDay.setText(uday);
                

            }else{
                    ClearFields();
                    }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("User Details Updated successfully");
            alert.setTitle("UPDATE USERS");
            //alert.show();

        } catch (SQLException ex) {
            Logger.getLogger(RegisterUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ClearFields(){
    
                userid.setText(null);
                userTitle.setText(null);
                userFirstName.setText(null);
                UserLastName.setText(null);
                userContacts.setText(null);
                userGender.setText(null);
                userStation.setText(null);
                userDay.setText(null);
    }


    @FXML
    private void PrintDocument(ActionEvent event) {
        //Creating Pdf font colors, font-family, font-size
            Font redFont
                    = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.UNDERLINE, new CMYKColor(15, 255, 20, 40));
            Font paraFontOne
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(2, 255, 20, 155));
            Font fontCustomerValue
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(2, 255, 20, 15));
            Font dateFont
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(8, 222, 20, 15));
            Font paidcolor
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(85, 222, 2, 15));

            Font discountColor
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(86, 244, 66, 22));
            Font thanksColor
                    = FontFactory.getFont(FontFactory.COURIER, 9, Font.NORMAL, new CMYKColor(244, 66, 66, 66));
            Font contactsColor
                    = FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL, new CMYKColor(2, 155, 66, 225));
            try {

                //define page size and document(pdf)
                //Rectangle pRectangle = new Rectangle(220, 500);
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter writer = null;
                try {
                    writer = PdfWriter.getInstance(document, new FileOutputStream("allusers.pdf"));
                } catch (DocumentException ex) {
                    Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
                document.open();

                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setLockedWidth(true);
                PdfContentByte contentByte = writer.getDirectContent();

                //Create fields int the table 
                //First Row
                PdfPCell cell = new PdfPCell(new Phrase("Kamwana"));
                cell.setFixedHeight(30);
                cell.setBorder(2);
                cell.setColspan(3);
                cell.setBackgroundColor(BaseColor.GRAY);

                //DOCUMENT LINKING
              /*  String paraOne = "Welcome To newBus Travellers";
                String stageOne = ("Original Zone : " + originField.getText());
                String stageTwo = ("Destination Zone : " + destinationField.getText());
                String stageDays = ("Number Of Days : " + daysField.getText());
                String customerName = ("Customers Name : " + filledName.getText());
                String newDate = ("Date : " + datepickerlabel.getText());
                String totalAmountToBePaid = ("Amount Paid : " + totalAmountPaid.getText());
                String discountReceivedByCustomer = ("Discount Received : " + discountReceived.getText());
                String thankYouNote = "Thanks for Travelling With us";
                String contacts = "0707058437 or EMAIL US at haronnjugunaw@gmail.com";

                document.add(new Paragraph(paraOne, redFont));
                document.add(new Paragraph(newDate, dateFont));
                document.add(new Paragraph(customerName, fontCustomerValue));
                document.add(new Paragraph(stageOne, paraFontOne));
                document.add(new Paragraph(stageTwo, paraFontOne));
                document.add(new Paragraph(stageDays, paraFontOne));
                document.add(new Paragraph(totalAmountToBePaid, paidcolor));
                document.add(new Paragraph(discountReceivedByCustomer, discountColor));
                document.add(new Paragraph(thankYouNote, thanksColor));
                document.add(new Paragraph(contacts, contactsColor));
                table.addCell(cell);
                document.add(table);//Adding table component
*/
                //Set Printing Permission
                int ALLOW_PRINTING = PdfWriter.ALLOW_PRINTING;
                int CenterWindow = PdfWriter.CenterWindow;

                //Dynamic content
                Image img = Image.getInstance("/home/trojan101/Pictures/WhatsApp Images/one.jpg");
                //setImage position
                img.setAbsolutePosition(200f, 450f);
                //rotate image
                img.setRotation(250f);
                //scale image
                img.scaleToFit(30f, 800f);
                img.setBorderColor(BaseColor.RED);

                //Show Alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Receipt Dialogue");
                alert.setHeaderText("Receipt has been generated successifully");
                //Alert buttons
                ButtonType buttonTypeOne = new ButtonType("CONFIRM");
                ButtonType buttonTypeTWO = new ButtonType("EXIT");
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTWO);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    try {
                        //some File Viewer logic
                        FileChooser viewReceipts = new FileChooser();
                        viewReceipts.setInitialDirectory(new File("/home/trojan101/NetBeansProjects/StageArea"));
                        viewReceipts.getExtensionFilters().addAll(new ExtensionFilter("PDF FILES", "*.pdf", "*.PDF"));
                        viewReceipts.showOpenDialog(null);

                    } catch (Exception e) {
                        //String dc = "Receipts Directory is not configured";
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } else if (result.get() == buttonTypeTWO) {
                    //some Printing Logic
                    System.out.println("Some Printing Action Button");
                }
                document.add(img);
                document.close();
                writer.close();
            } catch (FileNotFoundException ex) {
               //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}
    
