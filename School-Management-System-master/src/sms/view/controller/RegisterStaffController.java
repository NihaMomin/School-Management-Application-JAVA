package sms.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sms.dbController.GradeController;
import sms.dbController.StaffController;
import sms.dbController.StudentController;
import sms.model.Staff;
import sms.model.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class RegisterStaffController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadComboBox();
    }
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
    private JFXRadioButton genderField;

    @FXML
    private ToggleGroup g;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private JFXButton AddStaff;

    @FXML
    private JFXButton reset;

    @FXML
    private ComboBox<String> loadCombo;

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
    void AddStaff(ActionEvent event) {

        try {

            ValidationController v = new ValidationController();

            if(v.validateEmpty(empNoField)&& v.validateEmpty(teacherNameField)&& v.validateEmpty(nicField)&& v.validateEmpty(dobField)
                    && v.validateEmpty(doaField)&& v.validateEmpty(phoneField)&& v.validateDate(dobField)&& v.validateDate(doaField)&& v.numbersOnly(empNoField)&& v.numbersOnly(phoneField)){

                int empNo = Integer.parseInt(empNoField.getText());
                String teacherName = teacherNameField.getText();
                String type = "teacher";
                String nic = nicField.getText();
                String dob = dobField.getText();
                String doa = doaField.getText();
                RadioButton selectedRadioButton = (RadioButton) g.getSelectedToggle();
                String gender = selectedRadioButton.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                String prsntGrade = loadCombo.getValue();

                Staff s = new Staff(empNo, type, teacherName, email, phone, gender, nic, dob, doa, address,Integer.parseInt(prsntGrade));
                int i = StaffController.AddStaff(s);

                if (i > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Staff Registration");
                    alert.setHeaderText(null);
                    alert.setContentText("Staff Registered Successfully..!");
                    alert.showAndWait();

                    empNoField.setText(null);
                    teacherNameField.setText(null);
                    nicField.setText(null);
                    dobField.setText(null);
                    doaField.setText(null);
                    emailField.setText(null);
                    nicField.setText(null);
                    phoneField.setText(null);
                    addressField.setText(null);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Staff Registration");
                    alert.setHeaderText(null);
                    alert.setContentText("OOPS there is an error adding Staff..!");
                    alert.showAndWait();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void reset(ActionEvent event) {
        empNoField.setText(null);
        teacherNameField.setText(null);
        nicField.setText(null);
        dobField.setText(null);
        doaField.setText(null);
        emailField.setText(null);
        nicField.setText(null);
        addressField.setText(null);
        phoneField.setText(null);
    }

    @FXML
    private void loadComboBox(){
        ArrayList arrayList = null;
        try {
            arrayList = GradeController.getGrades();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        ObservableList observableArray = FXCollections.observableArrayList();
        observableArray.addAll(arrayList);

        if (observableArray != null){
            loadCombo.setItems(observableArray);
        }

    }
}