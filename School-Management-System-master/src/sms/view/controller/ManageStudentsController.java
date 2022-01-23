package sms.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sms.db.DBConnection;
import sms.dbController.StudentController;
import sms.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageStudentsController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    private AnchorPane manageStudents;

    @FXML
    private TextField AdNo;

    @FXML
    private TextField adNoField;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField gradeField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField dobField;

    @FXML
    private TextField doaField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField nicField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField AdNo1;

    @FXML
    private JFXButton searchPastStudent;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton searchStudent;

    @FXML
    private JFXButton btnPrint;

    @FXML
    private JFXButton Back;

    @FXML
    void Back(ActionEvent event) {
        try {
            AnchorPane studentMgmt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/StudentManagement.fxml")));
            manageStudents.getChildren().setAll(studentMgmt);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    //Delete Method
    @FXML
    void btnDelete(ActionEvent event) {
        try {
            String adNo = adNoField.getText();
            String type = "student";
            Student s = new Student(Integer.parseInt(adNoField.getText()), type, fullNameField.getText(), emailField.getText(), phoneField.getText(),genderField.getText(), nicField.getText(), dobField.getText(), doaField.getText(), addressField.getText(), Integer.parseInt(gradeField.getText()));

            ValidationController v = new ValidationController();

            if(v.numbersOnly(adNoField)) {
                int forceDelete = StudentController.deleteStudent(adNo);
                if (forceDelete > 0) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Student");
                    alert.setHeaderText(null);
                    alert.setContentText("Student " + adNo + " has been deleted sucessfully..!");
                    alert.showAndWait();

                    AdNo.setText(null);
                    adNoField.setText(null);
                    fullNameField.setText(null);
                    emailField.setText(null);
                    dobField.setText(null);
                    doaField.setText(null);
                    gradeField.setText(null);
                    genderField.setText(null);
                    nicField.setText(null);
                    phoneField.setText(null);
                    fullNameField.setText(null);
                    addressField.setText(null);


                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Delete Student");
                    alert.setHeaderText(null);
                    alert.setContentText("There is an error deleting Student");
                    alert.showAndWait();
                }
            }

            } catch(ClassNotFoundException | SQLException ex){
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    //Update Method
    @FXML
    void btnUpdate(ActionEvent event) throws SQLException {
        try {

            ValidationController v = new ValidationController();

            if (v.numbersOnly(adNoField)) {

                int adNo = Integer.parseInt(adNoField.getText());
                String fullName = fullNameField.getText();
                String dob = dobField.getText();
                String doa = doaField.getText();
                String gender = genderField.getText();
                String type = "student";
                String email = emailField.getText();
                String grade = gradeField.getText();
                String nic = nicField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
//                System.out.println(adNo + type + fullName + email + phone + gender + nic + dob + doa + address + Integer.parseInt(grade));
                Student s = new Student(adNo, type, fullName, email, phone,gender, nic, dob, doa, address, Integer.parseInt(grade));
                int i = StudentController.updateStudent(s);
                if (i > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Student Management");
                    alert.setHeaderText(null);
                    alert.setContentText("Student " + adNo + " Updated Successfully..!");
                    alert.showAndWait();

                    AdNo.setText(null);
                    adNoField.setText(null);
                    fullNameField.setText(null);
                    emailField.setText(null);
                    dobField.setText(null);
                    doaField.setText(null);
                    gradeField.setText(null);
                    genderField.setText(null);
                    nicField.setText(null);
                    phoneField.setText(null);
                    fullNameField.setText(null);
                    addressField.setText(null);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Student Management");
                    alert.setHeaderText(null);
                    alert.setContentText("OOPs there is an error updating Student..!");
                    alert.showAndWait();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Search Method
    @FXML
    void searchStudent(ActionEvent event) {
        try {
            int adNo = Integer.parseInt(AdNo.getText());
//            System.out.println(adNo);
            Student s = StudentController.searchStudent(adNo);
            ValidationController v = new ValidationController();
            if (v.numbersOnly(AdNo)){
                if (s != null) {
                    adNoField.setText(String.valueOf(s.get_user_id()));
                    fullNameField.setText(s.get_user_name());
                    dobField.setText(s.get_user_dob());
                    doaField.setText(s.get_user_doa());
                    genderField.setText(s.get_user_gender());
                    emailField.setText(s.get_user_email());
                    nicField.setText(s.get_user_nic());
                    phoneField.setText(s.get_user_contact());
                    addressField.setText(s.get_user_address());
                    gradeField.setText(String.valueOf(s.get_student_grade()));

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Student Search");
                    alert.setHeaderText(null);
                    alert.setContentText("Student Not Found");
                    alert.showAndWait();

                    AdNo.setText(null);
                    adNoField.setText(null);
                    fullNameField.setText(null);
                    emailField.setText(null);
                    typeField.setText(null);
                    dobField.setText(null);
                    doaField.setText(null);
                    genderField.setText(null);
                    adNoField.setText(null);
                    nicField.setText(null);
                    phoneField.setText(null);
                    fullNameField.setText(null);
                    addressField.setText(null);
                    gradeField.setText(null);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Student Search");
                alert.setHeaderText(null);
                alert.setContentText("Student Not Found");
                alert.showAndWait();

                AdNo.setText(null);
                adNoField.setText(null);
                fullNameField.setText(null);
                emailField.setText(null);
                typeField.setText(null);
                dobField.setText(null);
                doaField.setText(null);
                genderField.setText(null);
                adNoField.setText(null);
                nicField.setText(null);
                phoneField.setText(null);
                fullNameField.setText(null);
                addressField.setText(null);
                gradeField.setText(null);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}




