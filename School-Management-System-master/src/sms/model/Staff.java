package sms.model;

public class Staff extends User{

    private int teacher_grade;
    public Staff(int empNo, String type,String teacherName, String email, String phone, String gender, String nic, String dob, String doa , String address, Integer teacher_grade) {
        super(empNo, type, teacherName, email, phone, gender, nic, dob, doa, address);
        this.teacher_grade = teacher_grade;
    }
    public Integer get_teacher_grade(){return teacher_grade;};
    public void set_teacher_grade(Integer student_grade){this.teacher_grade = student_grade;}

    @Override
    public void show() {
        System.out.println("Teacher Class");
    }
}