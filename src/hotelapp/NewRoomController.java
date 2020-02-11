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
import javafx.beans.value.ObservableValue;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author YVON
 */
public class NewRoomController implements Initializable {
    
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
    private JFXTextField txID;

    @FXML
    private JFXTextField txtNum;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TableView tblData;

    @FXML
    private Label lblStatus;

    @FXML
    private JFXComboBox<String> txtType;

    @FXML
    private JFXComboBox<String> txtStatus;


   PreparedStatement preparedStatement;
    Connection connection;

    public NewRoomController() throws SQLException {
        connection = (Connection) ConnectDB.conDB();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
// TODO
        txtStatus.getItems().addAll("Available", "Unavailable", "Other");
        txtStatus.getSelectionModel().select("Available");
        
        txtType.getItems().addAll("Single", "Double", "Suit", "Family");
        txtType.getSelectionModel().select("Single");
        
        fetColumnList();
        fetRowList();
    }

    @FXML
    private void HandleEvents(ActionEvent event) {
//check if not empty
       
        if (event.getSource() == btnSave) {
            saveData();
        }
        if (event.getSource() == btnCancel) {
            clearFields();
        }
        if (event.getSource() == btnClose) {
            System.exit(0);
        }
    }

    private void clearFields() {
        txID.clear();
        txtNum.clear();
    }

    
      String typeNID;
    private String saveData() {
        
        switch(txtType.getValue().toString()){
                case "Single": typeNID ="1"; break;
                case "Double": typeNID ="2"; break;
                case "Suit": typeNID ="3"; break;
                case "Family": typeNID ="4"; break;
        }
        try {
            String st = "INSERT INTO rooms ( id, numb, typeId, status) VALUES (?,?,?,?)";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, txID.getText());
            preparedStatement.setString(2, txtNum.getText());
            preparedStatement.setString(3, typeNID);
            preparedStatement.setString(4, txtStatus.getValue().toString());
     
            
            preparedStatement.executeUpdate();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Added Successfully");
            fetRowList();
//clear fields
            clearFields();
            return "Success";
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(ex.getMessage());
            return "Exception";
        }
    }
    
    
    private ObservableList<ObservableList> data;
    String SQL = "select id, numb, typename, status from rooms join roomtype on rooms.typeid=roomtype.typeid";
//only fetch columns

    private void fetColumnList() {
        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL);
//SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tblData.getColumns().removeAll(col);
                tblData.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
//fetches rows and data from the list

    public void fetRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL);
            while (rs.next()) {
//Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            tblData.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
     @FXML
    private void NewRoomAction(ActionEvent event) {
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
