/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author YVON
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXButton btnSign;

    @FXML
    private JFXButton btnClose;

    @FXML
    private Label lblErrors;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnSign) {
            logIn();
            if (status == "success") {
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
            }
        } else if (event.getSource() == btnClose) {
            System.exit(0);

        }
        if (event.getSource() == btnClose) {
            System.exit(0);
        }
    }

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public LoginController() throws SQLException {
        con = ConnectDB.conDB();
    }
    String status = "fail";

    private void logIn() {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        String sql = "SELECT * FROM LOGIN WHERE username=? and password =?";

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                lblErrors.setTextFill(Color.WHITE);
                lblErrors.setText("Enter correct username/password!");
                status = "fail";
            } else {
                lblErrors.setTextFill(Color.WHITE);
                lblErrors.setText("Login successful!!");
                status = "success";
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
