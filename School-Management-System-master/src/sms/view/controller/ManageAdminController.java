package sms.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import sms.dbController.AdminController;
import sms.model.Admin;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ManageAdminController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {loadTable();}

    private final ObservableList<Admin> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Admin> usersTable;
    @FXML
    private TableColumn<Admin, String> UsernameColumn;
    @FXML
    private TableColumn<Admin, Integer> UserIDColumn;
    @FXML
    private TableColumn<Admin, String> UserstatusColumn;
    @FXML
    private TableColumn<Admin, String> passwordColumn;

    @FXML
    private JFXButton addUser;
    @FXML
    private JFXTextField userid;
    @FXML
    private JFXTextField userNameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXTextField userstatus;
    @FXML
    private JFXButton searchUser;
    @FXML
    private JFXButton updateUser;
    @FXML
    private JFXButton deleteUser;
    @FXML
    private JFXButton Back;
    @FXML
    private AnchorPane root;

    @FXML
    void Back() {
        try {
            AnchorPane user = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/UserAccount.fxml")));
            root.getChildren().setAll(user);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    void loadTable(){

        UserIDColumn.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("admin_id"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<Admin, String>("admin_name"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Admin, String>("password"));
        UserstatusColumn.setCellValueFactory(new PropertyValueFactory<Admin, String>("status"));

        try {
            usersTable.setItems(data);
            ArrayList<Admin> admins = AdminController.getAllAdmin();
            for (Admin admin : admins) {
                data.add(admin);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addUser() {

        try {
            if(ValidationController.validateEmpty(userid) && ValidationController.validateEmpty(userNameField) && ValidationController.validateEmpty(passwordField) && ValidationController.validateEmpty(userstatus)) {

                Integer admin_id = Integer.parseInt(userid.getText());
                String admin_name = userNameField.getText();
                String admin_password = passwordField.getText();
                String admin_status = userstatus.getText();

                Admin admin = new Admin(admin_id, admin_name, admin_password, admin_status);
                int i = AdminController.addAdmin(admin);
                if(i > 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Management");
                    alert.setHeaderText(null);
                    alert.setContentText("User Registered Successfully..!");
                    alert.showAndWait();

                    userid.setText(null);
                    userNameField.setText(null);
                    passwordField.setText(null);
                    userstatus.setText(null);
                }
                data.clear();               //Refresh Table
                loadTable();                //Refresh Table

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void deleteUser(ActionEvent event) {

        try {
            if(ValidationController.validateEmpty(userid)){

                Integer user_id = Integer.parseInt(userid.getText());

                int deleteUser = AdminController.deleteAdmin(user_id);

                if (deleteUser > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Management");
                    alert.setHeaderText(null);
                    alert.setContentText("User Deleted Sucessfully..!");
                    alert.showAndWait();

                    userid.setText(null);
                    userNameField.setText(null);
                    passwordField.setText(null);
                    userstatus.setText(null);

                    data.clear();               //Refresh Table
                    loadTable();                //Refresh Table

                } else {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Management");
                    alert.setHeaderText(null);
                    alert.setContentText("There is an Error in Deleting User..!");
                    alert.showAndWait();

                    userid.setText(null);
                    userNameField.setText(null);
                    passwordField.setText(null);
                    userstatus.setText(null);
                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchUser(ActionEvent event) {

        try {
            if(ValidationController.validateEmpty(userid)){
                Integer admin_id = Integer.parseInt(userid.getText());
                Admin admin = AdminController.searchAdmin(admin_id);

                if(admin != null){

                    userid.setText(String.valueOf(admin.getAdmin_id()));
                    userNameField.setText(admin.getAdmin_name());
                    passwordField.setText(admin.getPassword());
                    userstatus.setText(admin.getStatus());

                }else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("User Management");
                    alert.setHeaderText(null);
                    alert.setContentText("User Not Found");
                    alert.showAndWait();

                    userid.setText(null);
                    userNameField.setText(null);
                    passwordField.setText(null);
                    userstatus.setText(null);

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateUser(ActionEvent event) {

        Integer admin_id = Integer.parseInt(userid.getText());
        String admin_name = userNameField.getText();
        String admin_password = passwordField.getText();
        String admin_status = userstatus.getText();

        try {
            if(ValidationController.validateEmpty(userid)){

                Admin admin = new Admin(admin_id,admin_name,admin_password,admin_status);

                int update = AdminController.updateAdmin(admin);

                if(update > 0){

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Management");
                    alert.setHeaderText(null);
                    alert.setContentText("User Updated Successfully..!");
                    alert.showAndWait();

                    userid.setText(null);
                    userNameField.setText(null);
                    passwordField.setText(null);
                    userstatus.setText(null);

                    data.clear();
                    loadTable();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Management");
                    alert.setHeaderText(null);
                    alert.setContentText("There is an error, user not found" + "..!");
                    alert.showAndWait();

                    userid.setText(null);
                    userNameField.setText(null);
                    passwordField.setText(null);
                    userstatus.setText(null);
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Management");
                alert.setHeaderText(null);
                alert.setContentText("There is an Error..!");
                alert.showAndWait();

                userid.setText(null);
                userNameField.setText(null);
                passwordField.setText(null);
                userstatus.setText(null);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
