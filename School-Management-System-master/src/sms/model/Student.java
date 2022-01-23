package sms.model;

public class Student extends User{

    private int student_grade;

    public Student(int adNo, String type, String fullName, String email, String contact, String gender, String nic, String dob, String doa, String address, Integer student_grade){
        super(adNo, type, fullName, email, contact, gender, nic, dob, doa, address);
        this.student_grade = student_grade;
    }

    public Integer get_student_grade(){return student_grade;};
    public void set_student_grade(Integer student_grade){this.student_grade = student_grade;};

    @Override
    public void show() {
        System.out.println("Student Class");
    }
}
