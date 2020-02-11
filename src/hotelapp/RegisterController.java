package hotelapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class RegisterController implements Initializable {

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnCustomers;

    @FXML
    private JFXButton btnRooms;

    @FXML
    private JFXButton btnSetting;

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXTextField txtCustID;

    @FXML
    private JFXTextField txtfName;

    @FXML
    private JFXTextField txtlName;

    @FXML
    private JFXTextField txtmobile;

    @FXML
    private JFXTextField txtaddress;

    @FXML
    private JFXTextField txtpostCode;

    @FXML
    private JFXTextField txtemail;

    @FXML
    private JFXDatePicker txtdob;

    @FXML
    private JFXDatePicker txtinDate;

    @FXML
    private JFXComboBox<String> txtidType;

    @FXML
    private JFXDatePicker txtoutDate;

    @FXML
    private JFXComboBox<String> txtroomNum;
 

    @FXML
    private JFXComboBox<String> txtroomType;

    @FXML
    private JFXComboBox<String> txtgender;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXComboBox<String> txtnational;

    @FXML
    private Label lblStatus;

    @FXML
    private JFXTextField txtIDnum;

    PreparedStatement preparedStatement;
    PreparedStatement preparedstatement;
    Connection connection;
    String typeNID;

    private ObservableList<String> RoomsList = FXCollections.observableArrayList();

    @FXML
    private void getRoom() {

        txtroomNum.setItems(RoomsList);
    } 
    @Override
    public void initialize(URL url, ResourceBundle resources) {
    getRoom();
// TODO
        txtgender.getItems().addAll("Male", "Female", "Other");
        txtgender.getSelectionModel().select("Male");

        txtidType.getItems().addAll("Passport", "National ID", "Voter's ID", "Student ID", "Driver License");
        txtidType.getSelectionModel().select("Passport");

        txtnational.getItems().addAll("Asia", "Europe", "America", "Africa", "Oceania", "Antartica");
        txtnational.getSelectionModel().select("Asia");

        txtroomType.getItems().addAll("Single", "Double", "Suit", "Family");
        txtroomType.getSelectionModel().select("Single");

        //GET THE ROOM NUMBER
         String roomSql = " select * from rooms where status ='Available' order by id";

        try {
            Connection conn = ConnectDB.conDB();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement RoomStn = conn.prepareStatement(roomSql);
            ResultSet RoomRS = RoomStn.executeQuery(roomSql);

            while (RoomRS.next()) {

                RoomsList.add(RoomRS.getString("numb"));

            }

            RoomRS.close();
            RoomStn.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        } 
    }

    private void clearFields() {
        txtCustID.clear();
        txtfName.clear();
        txtlName.clear();
        txtmobile.clear();
        txtaddress.clear();
        txtpostCode.clear();
        txtemail.clear();
        txtIDnum.clear();

    }

    @FXML
    private void HandleEvents(ActionEvent event) {
        if (txtCustID.getText().isEmpty() || txtfName.getText().isEmpty() || txtlName.getText().isEmpty()
                || txtmobile.getText().isEmpty() || txtaddress.getText().isEmpty()
                || txtemail.getText().isEmpty() || txtIDnum.getText().isEmpty() || txtpostCode.getText().isEmpty()
                || txtdob.getValue().equals(null)) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter all details");
        }

        if (event.getSource() == btnCancel) {
            clearFields();
        }
    }

    //SAVE DATA 
    public RegisterController() throws SQLException {
        connection = (Connection) ConnectDB.conDB();
    }

    //Save into Customer table
    private void saveData() {
        try {
            String st = "INSERT INTO customers VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = (PreparedStatement) connection.prepareStatement(st);

            preparedStatement.setString(1, txtCustID.getText());
            preparedStatement.setString(2, txtfName.getText());
            preparedStatement.setString(3, txtlName.getText());
            preparedStatement.setString(4, txtaddress.getText());
            preparedStatement.setString(5, txtpostCode.getText());
            preparedStatement.setString(6, txtmobile.getText());
            preparedStatement.setString(7, txtemail.getText());
            preparedStatement.setString(8, txtnational.getValue().toString());
            preparedStatement.setString(9, txtdob.getValue().toString());
            preparedStatement.setString(10, txtidType.getValue().toString());
            preparedStatement.setString(11, txtIDnum.getText());
            preparedStatement.setString(12, txtgender.getValue().toString());

            preparedStatement.executeUpdate();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Added Successfully");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(ex.getMessage());

        }
    }

    //save into Checkroom
    private void saveRoom() {
        try {
            switch (txtroomType.getValue().toString()) {
                case "Single":
                    typeNID = "1";
                    break;
                case "Double":
                    typeNID = "2";
                    break;
                case "Suit":
                    typeNID = "3";
                    break;
                case "Family":
                    typeNID = "4";
                    break;
            }
            String st1 = "INSERT INTO checkroom (CheckIn,inDate,outDate,CustId,TypeId,TotalCost,roomNumb,state) "
                    + " VALUES (null,?,?,?,?,'0',?,'IN')";

            preparedstatement = (PreparedStatement) connection.prepareStatement(st1);

            preparedstatement.setString(1, txtinDate.getValue().toString());
            preparedstatement.setString(2, txtoutDate.getValue().toString());
            preparedstatement.setString(3, txtCustID.getText());
            preparedstatement.setString(4, typeNID);
            preparedstatement.setString(5, txtroomNum.getValue().toString());

            preparedstatement.executeUpdate();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Added Successfully");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(ex.getMessage());

        }
    }

    //update
    private void UpdateRoomStatus() {
        try {
            String st2 = "update  rooms set status='Unavailable' where numb= " + txtroomNum.getValue().toString();
            PreparedStatement preparedStatement1 = (PreparedStatement) connection.prepareStatement(st2);

            preparedStatement1.executeUpdate();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Added Successfully");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(ex.getMessage());

        }
    }

    @FXML
    private void RegisterAction(ActionEvent event) {
        if (event.getSource() == btnSave) {
            saveData();
            saveRoom();
            UpdateRoomStatus();
        }

        if (event.getSource() == btnHome) {

            try {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (event.getSource() == btnClose) {
            System.exit(0);
        }
        if (event.getSource() == btnClose) {
            System.exit(0);
        }
        if (event.getSource() == btnRegister) {

            try {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Register.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        if (event.getSource() == btnCustomers) {

            try {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("customers.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        if (event.getSource() == btnRooms) {

            try {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Rooms.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        if (event.getSource() == btnSetting) {

            try {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("newOne.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
