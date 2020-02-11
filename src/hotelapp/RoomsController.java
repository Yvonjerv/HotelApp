/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelapp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.beans.value.ObservableValue;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author YVON
 */
public class RoomsController implements Initializable {

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
    private TableView<RoomsTable> tblData;

    @FXML
    private JFXTextField textFied;

    @FXML
    private JFXComboBox<String> choiceBox;

    @FXML
    private JFXButton btnSearchRoom;
    ////
    @FXML
    private TableColumn<RoomsTable, String> col_NUMB;

    @FXML
    private TableColumn<RoomsTable, String> col_FIRSTNAME;

    @FXML
    private TableColumn<RoomsTable, String> col_LASTNAME;

    @FXML
    private TableColumn<RoomsTable, String> col_TYPENAME;

    @FXML
    private TableColumn<RoomsTable, String> col_INDATE;
    
    
    @FXML
    private TableColumn<RoomsTable, String> col_STATE;

   Connection con = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement;
    Connection connection;

    public RoomsController() throws SQLException {
        connection = (Connection) ConnectDB.conDB();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

         tblData.setEditable(true);

        FilteredList<RoomsTable> flRooms= new FilteredList(data, p -> true);//Pass the data to a filtered list
        tblData.setItems(flRooms);//Set the table's items using the filtered list
       
        choiceBox.getItems().addAll("NUMB", "FIRSTNAME", "LASTNAME", "TYPENAME",
                "INDATE","STATE");
        choiceBox.setValue("FIRSTNAME");

        textFied.setOnKeyReleased(keyEvent -> {
            switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "NUMB":
                    flRooms.setPredicate(p -> p.getNUMB().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "FIRSTNAME":
                    flRooms.setPredicate(p -> p.getFIRSTNAME().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "LASTNAME":
                    flRooms.setPredicate(p -> p.getLASTNAME().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "TYPENAME":
                    flRooms.setPredicate(p -> p.getTYPENAME().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "INDATE":
                    flRooms.setPredicate(p -> p.getINDATE().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                     case "STATE":
                    flRooms.setPredicate(p -> p.getSTATE().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                           }

        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                textFied.setText("");
                flRooms.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });

        display();

       col_NUMB.setCellValueFactory(new PropertyValueFactory<>("NUMB"));
        col_FIRSTNAME.setCellValueFactory(new PropertyValueFactory<>("FIRSTNAME"));
        col_LASTNAME.setCellValueFactory(new PropertyValueFactory<>("LASTNAME"));
        col_TYPENAME.setCellValueFactory(new PropertyValueFactory<>("TYPENAME"));
        col_INDATE.setCellValueFactory(new PropertyValueFactory<>("INDATE"));
         col_STATE.setCellValueFactory(new PropertyValueFactory<>("STATE"));
       
        //table.setItems(data);
          choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {//reset table and textfield when new choice is selected
            if (newVal != null)
            {
                textFied.setText("");
                flRooms.setPredicate(null);
            }
        });
    }

   ObservableList<RoomsTable> data = FXCollections.observableArrayList(); ;

    String SQL = "select ROOMS.numb, customers.firstname, customers.lastname, roomtype.typename, checkroom.inDate,checkroom.STATE\n"
            + "from ROOMS \n"
            + "INNER JOIN checkroom ON  rooms.numb = checkroom.roomNumb\n"
            + "INNER JOIN roomtype ON  roomtype.typeID  = checkroom.typeID \n"
            + "INNER JOIN customers ON checkroom.CustId = customers.CustId";

  private void display() {
        try {
            con = ConnectDB.conDB();

            Class.forName("com.mysql.cj.jdbc.Driver");

            rs = con.createStatement().executeQuery(SQL);

               while (rs.next()) {
                data.add(new RoomsTable(rs.getString("NUMB"), rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"), rs.getString("TYPENAME"), rs.getString("INDATE"),rs.getString("STATE")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
 

    @FXML
    private void RoomAction(ActionEvent event) {
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
