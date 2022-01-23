package sms.dbController;

import sms.db.DBConnection;
import sms.model.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffController {

    public static int AddStaff(Staff staff)throws ClassNotFoundException,SQLException {
        String SQL="INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
        String SQL1="INSERT INTO teacher VALUES(?)";
        String SQL2="INSERT INTO grade_has_teacher VALUES(?,?)";

        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        PreparedStatement stm1 = conn.prepareStatement(SQL1);
        PreparedStatement stm2 = conn.prepareStatement(SQL2);

        stm.setObject(1,staff.get_user_id());
        stm.setObject(2, staff.get_user_type());
        stm.setObject(3, staff.get_user_name());
        stm.setObject(4, staff.get_user_email());
        stm.setObject(5, staff.get_user_contact());
        stm.setObject(6, staff.get_user_gender());
        stm.setObject(7, staff.get_user_nic());
        stm.setObject(8, staff.get_user_dob());
        stm.setObject(9, staff.get_user_doa());
        stm.setObject(10, staff.get_user_address());

        stm1.setObject(1,staff.get_user_id());

        stm2.setObject(1,staff.get_user_id());
        stm2.setObject(2,staff.get_teacher_grade());

        stm1.executeUpdate();
        stm2.executeUpdate();

        return  stm.executeUpdate();
    }

    public static Staff searchStaff(Integer empNo) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM user WHERE user_id = ? and user_type='teacher'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, empNo);
        ResultSet rst=stm.executeQuery();
        System.out.println(stm);
        String sql1 = "SELECT grade_gradeId FROM grade_has_teacher WHERE teacher_id = ?";
        PreparedStatement stm1 = conn.prepareStatement(sql1);
        stm1.setObject(1, empNo);
        ResultSet rst1 = stm1.executeQuery();
//        System.out.println(stm1);
        if(rst.next() && rst1.next()){
            Staff s = new Staff(rst.getInt(1),rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9),rst.getString(10), Integer.parseInt(rst1.getString(1)));
            return s;
        }
        return null;
    }

    public static int deleteStaff(Integer empNo) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM user WHERE user_id ='"+empNo+"'";
        String sql1 = "DELETE FROM teacher WHERE teacher_id ='"+empNo+"'";
        String sql2 = "DELETE FROM grade_has_teacher WHERE teacher_id ='"+empNo+"'";

        Connection conn = DBConnection.getDBConnection().getConnection();

        PreparedStatement stm = conn.prepareStatement(sql);
        PreparedStatement stm1 = conn.prepareStatement(sql1);
        PreparedStatement stm2 = conn.prepareStatement(sql2);

        stm1.executeUpdate();
        stm2.executeUpdate();

        return  stm.executeUpdate();
    }

    public static int updateStaff(Staff staff) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE user SET user_id = ?, user_type = ?, user_name = ?, user_email = ?, user_contact = ?, user_gender = ?, user_nic = ?, user_doa = ?, user_dbo = ?, user_address = ? WHERE user_id = '" +staff.get_user_id()+ "'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setObject(1,staff.get_user_id());
        stm.setObject(2, staff.get_user_type());
        stm.setObject(3, staff.get_user_name());
        stm.setObject(4, staff.get_user_email());
        stm.setObject(5, staff.get_user_contact());
        stm.setObject(6, staff.get_user_gender());
        stm.setObject(7, staff.get_user_nic());
        stm.setObject(8, staff.get_user_dob());
        stm.setObject(9, staff.get_user_doa());
        stm.setObject(10, staff.get_user_address());

        String s = "UPDATE grade_has_teacher SET grade_gradeId =? WHERE teacher_id ='"+staff.get_user_id()+"'";
        PreparedStatement s1 = conn.prepareStatement(s);
        s1.setObject(1,staff.get_user_id());
        s1.executeUpdate();

        return  stm.executeUpdate();
    }


}