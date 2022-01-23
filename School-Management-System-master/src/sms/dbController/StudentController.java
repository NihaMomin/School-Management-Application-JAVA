package sms.dbController;

import javafx.scene.control.Alert;
import sms.model.Student;
import sms.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class StudentController {

    public static int AddStudent(Student student)throws ClassNotFoundException,SQLException {
        String SQL="INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
        String SQL1="INSERT INTO student VALUES(?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        PreparedStatement stm1 = conn.prepareStatement(SQL1);
        stm.setObject(1,student.get_user_id());
        stm.setObject(2, student.get_user_type());
        stm.setObject(3, student.get_user_name());
        stm.setObject(4, student.get_user_email());
        stm.setObject(5, student.get_user_contact());
        stm.setObject(6, student.get_user_gender());
        stm.setObject(7, student.get_user_nic());
        stm.setObject(8, student.get_user_dob());
        stm.setObject(9, student.get_user_doa());
        stm.setObject(10, student.get_user_address());
//        System.out.println(stm);
        stm1.setObject(1,student.get_user_id());
        stm1.setObject(2, student.get_student_grade());
        stm1.executeUpdate();
        return  stm.executeUpdate();
    }

    public static Student searchStudent(Integer adNo) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, adNo);
        ResultSet rst=stm.executeQuery();
//        System.out.println(stm);
        String sql1 = "SELECT grade_gradeId FROM student WHERE student_id = ?";
        PreparedStatement stm1 = conn.prepareStatement(sql1);
        stm1.setObject(1, adNo);
        ResultSet rst1 = stm1.executeQuery();
//        System.out.println(stm1);
        if(rst.next() && rst1.next()){
//            System.out.println(rst1.getString(1));
            Student s = new Student(rst.getInt(1),rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9),rst.getString(10), Integer.parseInt(rst1.getString(1)));
            return s;
        }
        return null;
    }

    public static int deleteStudent(String user_id) throws ClassNotFoundException, SQLException {

        String sql = "DELETE FROM user WHERE user_id ='"+user_id+"'";
        String sql1 = "DELETE FROM student WHERE student_id ='"+user_id+"'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        PreparedStatement stm1 = conn.prepareStatement(sql1);
        stm1.executeUpdate();
        return  stm.executeUpdate();
    }

    public static int updateStudent(Student student) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE user SET user_id = ?, user_name = ?, user_type = ?, user_email = ?, user_contact = ?, user_gender = ?, user_nic = ?, user_doa = ?, user_dbo = ?, user_address = ? WHERE user_id = '" +student.get_user_id()+ "'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1,student.get_user_id());
        stm.setObject(3, student.get_user_type());
        stm.setObject(2, student.get_user_name());
        stm.setObject(4, student.get_user_email());
        stm.setObject(5, student.get_user_contact());
        stm.setObject(6, student.get_user_gender());
        stm.setObject(7, student.get_user_nic());
        stm.setObject(8, student.get_user_doa());
        stm.setObject(9, student.get_user_dob());
        stm.setObject(10, student.get_user_address());
//        System.out.println(stm);
        String s = "UPDATE student SET grade_gradeId =? WHERE student_id ='"+student.get_user_id()+"'";
        PreparedStatement s1 = conn.prepareStatement(s);
        s1.setObject(1, student.get_student_grade());
        s1.executeUpdate();
//        System.out.println("here");
        return  stm.executeUpdate();
    }

}
