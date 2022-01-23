package sms.model;

public class Admin {

    private Integer admin_id;
    private String admin_name;
    private String admin_password;
    private String status;

    public Admin(String name, String password) {
        this.admin_name = name;
        this.admin_password = password;
    }
    public Admin(Integer id, String name, String password, String status) {
        this.admin_id = id;
        this.admin_name = name;
        this.admin_password = password;
        this.status = status;
    }

    public Integer getAdmin_id() {return admin_id;}
    public String getAdmin_name() {return admin_name;}
    public String getPassword() {return admin_password;}
    public String getStatus() {return status;}

    public void setAdmin_id(Integer admin_id) {this.admin_id = admin_id;}
    public void setAdmin_name(String username) {
        this.admin_name = username;
    }
    public void setPassword(String password) {
        this.admin_password = password;
    }
    public void setStatus(String status) {this.status = status;}
}
