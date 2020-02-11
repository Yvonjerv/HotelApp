/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController implements Initializable {
 @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnSetting;

    @FXML
    private JFXButton btnRooms;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnCustomers;

    @FXML
    private JFXButton btnLog;

    @FXML
    private JFXButton btnHome1;

    @FXML
    private JFXButton btnSetting1;

    @FXML
    private JFXButton btnRooms1;

    @FXML
    private JFXButton btnRegister1;

    @FXML
    private JFXButton btnCustomers1;

    @FXML
    private JFXButton btnSetting11;

    @FXML
    private JFXButton btnClose;
      @FXML
    private JFXButton btnCheckOut;

    @FXML
    private Label showTime;

    @FXML
    private ImageView lnk_Wechat;

    @FXML
    private ImageView lk_Insta;

    @FXML
    private ImageView lnk_FB;

    @FXML
    private ImageView lnk_IN;

    @FXML
    private ImageView lnk_QQ;

    @FXML
    private ImageView lnk_Skype;

    @FXML
    private ImageView lnk_Message;

    @FXML
    private ImageView lnk_Twitter;

    @FXML
    private ImageView lnk_Whatsapp;
    @FXML
    void handleButtonAction(ActionEvent event) {

    }

    @FXML
    public void TimeDisplay() {
        Date date = new Date();
        do {
            showTime.setText(date.toString());
        } while (0 == 0);

    }

    @FXML
    private void HomeButtonAction(ActionEvent event) {
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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("NewRoom.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
           if (event.getSource() == btnCheckOut) {

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
           
           if (event.getSource() == btnLog) {

            try {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    PreparedStatement preparedStatement;
    Connection connection;

    public HomeController() throws SQLException {
        connection = (Connection) ConnectDB.conDB();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
      int  second = LocalDateTime.now().getSecond();
       int minute = LocalDateTime.now().getMinute();
        int hour = LocalDateTime.now().getHour();
         showTime.setText(hour + ":" + (minute) + ":" + second);
    }),
         new KeyFrame(Duration.seconds(1))
    );
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();
}

}
