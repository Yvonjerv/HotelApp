/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YVON
 */
public class NewOneController implements Initializable {

    @FXML
    private JFXTextField txtNum;

    @FXML
    private JFXDatePicker txtoutDate;

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
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCost;
    @FXML
    private JFXButton btnInfo;

    @FXML
    private Label txt_name;

    @FXML
    private Label txt_inDate;

    @FXML
    private Label txtCost;
    Connection connection;

    ResultSet resultset = null;
    ResultSet resultset1 = null;

    PreparedStatement preparedStatement, preparedCheck;
    ResultSet rs = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public NewOneController() throws SQLException {
        connection = (Connection) ConnectDB.conDB();
    }

    private void ShowDetails() {
        try {

            String query = "select CONCAT(customers.FirstName,' ',customers.LastName) as name,"
                    + "checkroom.inDate, checkroom.state from customers "
                    + "join checkroom on customers.custID= checkRoom.custid "
                    + "where checkroom.state in ('IN','CUR') and checkroom.roomNumb=" + txtNum.getText();

            resultset = connection.createStatement().executeQuery(query);
            //resultset = connection.dbExecuteQuery(query);

            while (resultset.next()) {
                String FN = resultset.getString("name");
                txt_name.setText(FN);
                String inDate = resultset.getString("inDate");
                txt_inDate.setText(inDate);
            }

        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }

    }

    //not working
    private void ShowCost() {
        try {

            String query1 = "select cost from cost_view  \n"
                    + "where state='CUR' and  roomNumb=" + txtNum.getText();

            resultset1 = connection.createStatement().executeQuery(query1);
            //resultset = connection.dbExecuteQuery(query);

            while (resultset1.next()) {
                String Fcost = resultset1.getString("Cost");
                txtCost.setText(Fcost);
            }

        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }

    }

    //changing Room status to Available
    private void UpdateRoomStatus() {
        try {
            String st = "update  rooms set status='Available' where numb=" + txtNum.getText();
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //changing Room status to Available

    private void UpdateCheckState1() {
        try {
            String st = "update checkroom set state='CUR' where roomNumb=" + txtNum.getText();
            preparedCheck = (PreparedStatement) connection.prepareStatement(st);
            preparedCheck.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   

    private void UpdateCheckState() {
        try {
            String st = "update checkroom set state='out' where roomNumb=" + txtNum.getText();
            preparedCheck = (PreparedStatement) connection.prepareStatement(st);
            preparedCheck.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //changing checkroom outDate
    private void UpdateOutDate() {
        try {
            String st = "update checkroom set outdate='" + txtoutDate.getValue().toString() + "' where roomNumb=" + txtNum.getText();
            preparedCheck = (PreparedStatement) connection.prepareStatement(st);
            preparedCheck.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    ///
    @FXML
    private void CheckAction(ActionEvent event) {
        if (event.getSource() == btnInfo) {
            ShowDetails();
            UpdateCheckState1();
                        UpdateOutDate();
        }

        if (event.getSource() == btnSave) {

            UpdateRoomStatus();
            UpdateCheckState();
        }
        if (event.getSource() == btnCost) {

            ShowCost();

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
