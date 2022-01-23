package sms.view.controller;
import javafx.fxml.FXMLLoader;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import sms.db.DBConnection;
import sms.dbController.SchoolController;
import sms.model.School;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import sms.dbController.StudentController;

public class SchoolInfoController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            School s = SchoolController.schoolInfo();
            if (s != null) {
                SchoolNameField.setText(s.getSchoolName());
                SchoolAddressField.setText(s.getSchoolAddress());
                classAvailableField.setText(s.getClassAvailable());
                schoolTypeField.setText(s.getSchoolType());
                postalCodeField.setText(s.getPostalCode());
                nameOfPrincipalField.setText(s.getNameOfPrincipal());
                ContactNoField.setText(s.getContactNo());

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("School Information");
                alert.setHeaderText(null);
                alert.setContentText("No Information Found..!");
                alert.showAndWait();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SchoolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private AnchorPane root;

    @FXML
    private TextField SchoolNameField;

    @FXML
    private TextField SchoolAddressField;

    @FXML
    private TextField classAvailableField;

    @FXML
    private TextField schoolTypeField;

    @FXML
    private TextField postalCodeField;

    @FXML
    private TextField nameOfPrincipalField;

    @FXML
    private TextField ContactNoField;

    @FXML
    private JFXButton Back;

    @FXML
    void Back(ActionEvent event) {

        try {
            AnchorPane user = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/MainDashboard.fxml")));
            root.getChildren().setAll(user);
        }catch(IOException e){
            System.out.println(e);
        }

    }

    @FXML
    void updateDetails(ActionEvent event) {
        try {
            String SchoolName = SchoolNameField.getText();
            String SchoolAddress = SchoolAddressField.getText();
            String classAvailable = classAvailableField.getText();
            String schoolType = schoolTypeField.getText();
            String postalCode = postalCodeField.getText();
            String nameOfPrincipal = nameOfPrincipalField.getText();
            String ContactNo = ContactNoField.getText();

            School sch = new School(SchoolName,SchoolAddress,classAvailable,schoolType,postalCode,nameOfPrincipal,ContactNo);
            int i = SchoolController.updateInfo(sch);

            if (i > 0) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("School Information");
                alert.setHeaderText(null);
                alert.setContentText("Information Updated Successfully...!");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("School Information");
                alert.setHeaderText(null);
                alert.setContentText("OOPs there is an error Updating Details");
                alert.showAndWait();
            }

        }catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(SchoolController.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
}
