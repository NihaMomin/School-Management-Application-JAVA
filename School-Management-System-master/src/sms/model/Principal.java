package sms.model;

public class Principal extends User{

    public Principal(int adNo, String type, String fullName, String email, String contact, String gender, String nic, String dob, String doa, String address){
        super(adNo, type, fullName, email, contact, gender, nic, dob, doa, address);
    }

    @Override
    public void show() {
        System.out.println("Principal Class");
    }
}

