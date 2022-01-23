package sms.view.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sms.db.DBConnection;
import sms.dbController.GradeController;
import sms.dbController.StaffController;
import sms.dbController.StudentController;
import sms.model.Staff;
import sms.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ManageStaffsController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    private AnchorPane root;

    @FXML
    private TextField empNoField;

    @FXML
    private TextField teacherNameField;

    @FXML
    private TextField nicField;

    @FXML
    private TextField dobField;

    @FXML
    private TextField doaField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField prsntGradeField;

    @FXML
    private TextField empNoOld;

    @FXML
    private JFXButton deleteStaff;

    @FXML
    private JFXButton update;

    @FXML
    private TextField genderField;

    @FXML
    private TextField EmpNo;

    @FXML
    private JFXButton searchStaff;

    @FXML
    private TextField empName;

    @FXML
    private JFXButton searchByName;

    @FXML
    private JFXButton searchOldStaff;

    @FXML
    private JFXButton printStaff;

    @FXML
    private JFXButton Back;

    @FXML
    void Back(ActionEvent event) {
        try {
            AnchorPane studentMgmt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/StaffManagement.fxml")));
            root.getChildren().setAll(studentMgmt);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    void searchStaff(ActionEvent event) {
        try {
            int empNo = Integer.parseInt(EmpNo.getText());
//            System.out.println(empNo);
            Staff s = StaffController.searchStaff(empNo);
            if (s != null) {
                empNoField.setText(String.valueOf(s.get_user_id()));
                teacherNameField.setText(s.get_user_name());
                nicField.setText(s.get_user_nic());
                dobField.setText(s.get_user_dob());
                doaField.setText(s.get_user_doa());
                genderField.setText(s.get_user_gender());
                emailField.setText(s.get_user_email());
                phoneField.setText(s.get_user_contact());
                addressField.setText(s.get_user_address());
                prsntGradeField.setText(String.valueOf(s.get_teacher_grade()));


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Staff Search");
                alert.setHeaderText(null);
                alert.setContentText("Staff Not Found");
                alert.showAndWait();

                empNoField.setText(null);
                teacherNameField.setText(null);
                nicField.setText(null);
                dobField.setText(null);
                doaField.setText(null);
                emailField.setText(null);
                nicField.setText(null);
                genderField.setText(null);
                phoneField.setText(null);
                addressField.setText(null);
                prsntGradeField.setText(null);
                empName.setText(null);
                EmpNo.setText(null);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void deleteStaff(ActionEvent event) {
        try {
            int empNo = Integer.parseInt(empNoField.getText());
            String type = "teacher";

            ValidationController v = new ValidationController();
            if(v.numbersOnly(empNoField)){
                Staff s = new Staff(Integer.parseInt(empNoField.getText()), type, teacherNameField.getText(),  emailField.getText(), phoneField.getText(),
                        genderField.getText(), nicField.getText(), dobField.getText(), doaField.getText(), addressField.getText(),Integer.parseInt(prsntGradeField.getText()));

                int deleteStaff = StaffController.deleteStaff(empNo);

                if (deleteStaff > 0) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Staff Management");
                    alert.setHeaderText(null);
                    alert.setContentText("Staff " + empNo + " Has Been Deleted Sucessfully..!");
                    alert.showAndWait();

                    empNoField.setText(null);
                    teacherNameField.setText(null);
                    nicField.setText(null);
                    dobField.setText(null);
                    doaField.setText(null);
                    emailField.setText(null);
                    genderField.setText(null);
                    nicField.setText(null);
                    phoneField.setText(null);
                    addressField.setText(null);
                    prsntGradeField.setText(null);


                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Staff Management");
                    alert.setHeaderText(null);
                    alert.setContentText("There is an error deleting Staff..!");
                    alert.showAndWait();
                }

            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Staff Management");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Input");
                alert.showAndWait();
            }


        } catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void update(ActionEvent event) {
        try {

            ValidationController v = new ValidationController();

            if(v.validateEmpty(empNoField)&& v.validateEmpty(teacherNameField)&& v.validateEmpty(nicField)&& v.validateEmpty(dobField)
                    && v.validateEmpty(doaField)&& v.validateEmpty(phoneField)&& v.validateDate(dobField)&& v.validateDate(doaField)&& v.numbersOnly(empNoField)&& v.numbersOnly(phoneField)){

                int empNo = Integer.parseInt(empNoField.getText());
                String type = "teacher";
                String teacherName = teacherNameField.getText();
                String nic = nicField.getText();
                String dob = dobField.getText();
                String doa = doaField.getText();
                String gender = genderField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                String prsntGrade = prsntGradeField.getText();

                Staff s = new Staff(empNo, type, teacherName, email, phone, gender, nic, dob, doa, address,Integer.parseInt(prsntGrade));

                int i = StaffController.updateStaff(s);

                if (i > 0){

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Staff Management");
                    alert.setHeaderText(null);
                    alert.setContentText("Staff Informations Updated Successfully..!");
                    alert.showAndWait();

                    empNoField.setText(null);
                    teacherNameField.setText(null);
                    nicField.setText(null);
                    dobField.setText(null);
                    doaField.setText(null);
                    emailField.setText(null);
                    genderField.setText(null);
                    nicField.setText(null);
                    phoneField.setText(null);
                    addressField.setText(null);
                    prsntGradeField.setText(null);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
