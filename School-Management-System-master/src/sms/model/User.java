package sms.model;

abstract class User{

    protected int user_id;
    protected String user_type, user_name, user_email, user_contact, user_gender, user_nic, user_dob, user_doa, user_address;

    public User(int user_id, String user_type, String user_name, String user_email, String user_contact, String user_gender, String user_nic, String user_dob, String user_doa, String user_address){
        this.user_id = user_id;
        this.user_type = user_type;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_contact = user_contact;
        this.user_gender = user_gender;
        this.user_nic = user_nic;
        this.user_dob = user_dob;
        this.user_doa = user_doa;
        this.user_address = user_address;
    }
    //getters
    public int get_user_id(){
        return this.user_id;
    }
    public String get_user_type(){
        return this.user_type;
    }
    public String get_user_name(){return this.user_name;}
    public String get_user_email(){
        return this.user_email;
    }
    public String get_user_contact(){
        return this.user_contact;
    }
    public String get_user_gender(){
        return this.user_gender;
    }
    public String get_user_nic(){
        return this.user_nic;
    }
    public String get_user_dob(){
        return this.user_dob;
    }
    public String get_user_doa(){
        return this.user_doa;
    }
    public String get_user_address(){
        return this.user_address;
    }

    //setters

    public void set_user_id(int user_id){
        this.user_id = user_id;
    }
    public void set_user_type(String user_type){
        this.user_type = user_type;
    }
    public void set_user_name(String user_name){
        this.user_name = user_name;
    }
    public void set_user_email(String user_email){
        this.user_email = user_email;
    }
    public void set_user_contact(String user_contact){
        this.user_contact = user_contact;
    }
    public void set_user_gender(String user_gender){
        this.user_gender = user_gender;
    }
    public void set_user_nic(String user_nic){
        this.user_nic = user_nic;
    }
    public void set_user_dob(String user_dob){
        this.user_dob = user_dob;
    }
    public void set_user_doa(String user_doa){
        this.user_doa = user_doa;
    }
    public void set_user_address(String user_address){
        this.user_address = user_address;
    }

    public abstract void show();

}
