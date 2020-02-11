/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author YVON
 */
public class CustomersController implements Initializable {

    String CustID, FirstName, LastName, Address, PostalCode, Mobile, email, Nationality, Dob, IDtype, IdNumb, Gender;
    String custRef, fName;

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
    private JFXTextField textFied;

    @FXML
    private JFXTextField custRef1;

    @FXML
    private JFXButton btnSearchCust;

    //@FXML
    //private ChoiceBox<String> choiceBox;
    @FXML
    private JFXComboBox<String> choiceBox;
    ////
    @FXML
    private TableView<CustomersTable> table;
    @FXML
    private TableColumn<CustomersTable, String> col_CustID;
    @FXML
    private TableColumn<CustomersTable, String> col_FirstName;
    @FXML
    private TableColumn<CustomersTable, String> col_LastName;
    @FXML
    private TableColumn<CustomersTable, String> col_Address;
    @FXML
    private TableColumn<CustomersTable, String> col_PostalCode;
    @FXML
    private TableColumn<CustomersTable, String> col_Mobile;
    @FXML
    private TableColumn<CustomersTable, String> col_email;
    @FXML
    private TableColumn<CustomersTable, String> col_Nationality;
    @FXML
    private TableColumn<CustomersTable, String> col_Dob;
    @FXML
    private TableColumn<CustomersTable, String> col_IDtype;
    @FXML
    private TableColumn<CustomersTable, String> col_IdNumb;
    @FXML
    private TableColumn<CustomersTable, String> col_Gender;
    @FXML
    private TableColumn<CustomersTable, String> col_State;
///
    ObservableList<CustomersTable> oblist = FXCollections.observableArrayList();

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement;
    Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        table.setEditable(true);

        FilteredList<CustomersTable> flCustomers = new FilteredList(oblist, p -> true);//Pass the data to a filtered list
        table.setItems(flCustomers);//Set the table's items using the filtered list
        
        choiceBox.getItems().addAll("CustID", "First Name", "Last Name", "Address",
                "PostalCode", "Mobile", "email", "Nationality", "Dob", "IDtype", "IdNumb", "Gender", "State");
        choiceBox.setValue("First Name");

        textFied.setOnKeyReleased(keyEvent -> {
            switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "CustID":
                    flCustomers.setPredicate(p -> p.getCustID().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "First Name":
                    flCustomers.setPredicate(p -> p.getFirstName().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Last Name":
                    flCustomers.setPredicate(p -> p.getLastName().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "email":
                    flCustomers.setPredicate(p -> p.getEmail().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Address":
                    flCustomers.setPredicate(p -> p.getAddress().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "PostalCode":
                    flCustomers.setPredicate(p -> p.getPostalCode().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Mobile":
                    flCustomers.setPredicate(p -> p.getMobile().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Nationality":
                    flCustomers.setPredicate(p -> p.getNationality().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Dob":
                    flCustomers.setPredicate(p -> p.getDob().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "IDtype":
                    flCustomers.setPredicate(p -> p.getIDtype().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "IdNumb":
                    flCustomers.setPredicate(p -> p.getIdNumb().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Gender":
                    flCustomers.setPredicate(p -> p.getGender().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "State":
                    flCustomers.setPredicate(p -> p.getState().toLowerCase().contains(textFied.getText().toLowerCase().trim()));//filter table by first name
                    break;
            }

        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                textFied.setText("");
                flCustomers.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });

        display();

        col_CustID.setCellValueFactory(new PropertyValueFactory<>("CustID"));
        col_FirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        col_LastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        col_Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        col_PostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        col_Mobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_Nationality.setCellValueFactory(new PropertyValueFactory<>("Nationality"));
        col_Dob.setCellValueFactory(new PropertyValueFactory<>("Dob"));
        col_IDtype.setCellValueFactory(new PropertyValueFactory<>("IDtype"));
        col_IdNumb.setCellValueFactory(new PropertyValueFactory<>("IdNumb"));
        col_Gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        col_State.setCellValueFactory(new PropertyValueFactory<>("State"));

        //EDITABLE
        table.setEditable(true);
        // col_FirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        col_FirstName.setCellFactory(TextFieldTableCell.forTableColumn());
      

        //table.setItems(oblist);
        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                textFied.setText("");
                flCustomers.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
    }

    String addSt = null;
    String searchSt = "select customers.*, checkroom.state from customers join checkroom on customers.custID= checkRoom.custid";

    @FXML
    private void RegisterAction(ActionEvent event) {
        /*if (event.getSource() == btnSearchCust) {
            addSt = " where FirstName=" + "'" + fName1.getText().toString() + "' and custId= " + custRef1.getText().toString();
            searchSt = searchSt + addSt;
        }*/
    }

    private void display() {
        try {
            con = ConnectDB.conDB();

            Class.forName("com.mysql.cj.jdbc.Driver");

            rs = con.createStatement().executeQuery(searchSt);

            while (rs.next()) {
                oblist.add(new CustomersTable(rs.getString("CustID"), rs.getString("FirstName"),
                        rs.getString("LastName"), rs.getString("Address"), rs.getString("PostalCode"),
                        rs.getString("Mobile"), rs.getString("email"), rs.getString("Nationality"), rs.getString("Dob"),
                        rs.getString("IDtype"), rs.getString("IdNumb"), rs.getString("Gender"), rs.getString("State")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //EDIT TABLE
    @FXML
    public void OnEditChanged(TableColumn.CellEditEvent<CustomersTable, String> CustomersStringCellEditEvent) {
        CustomersTable customer = table.getSelectionModel().getSelectedItem();
        customer.setFirstName(CustomersStringCellEditEvent.getNewValue().toString());
    }

    @FXML
    private void CustomersButtonAction(ActionEvent event) {
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
