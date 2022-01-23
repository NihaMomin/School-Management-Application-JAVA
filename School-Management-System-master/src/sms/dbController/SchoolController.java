package sms.dbController;

import sms.db.DBConnection;
import sms.model.School;
import sms.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchoolController {

    public static School schoolInfo() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM school_info";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            School s=new School(rst.getString("school_name"),rst.getString("school_address"),rst.getString("class_available"),
                    rst.getString("school_type"), rst.getString("postal_code"),rst.getString("principal_id"), rst.getString("school_contact"));
            return s;
        }
        return null;
    }

    public static int updateInfo(School school) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE school_info SET school_name= ? ,school_address= ? ,class_available= ? ,school_type= ?, postal_code= ? " +
                ",principal_id=? ,school_contact=?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, school.getSchoolName());
        stm.setObject(2, school.getSchoolAddress());
        stm.setObject(3, Integer.parseInt(school.getClassAvailable()));
        stm.setObject(4, school.getSchoolType());
        stm.setObject(5, school.getPostalCode());
        stm.setObject(6, Integer.parseInt(school.getNameOfPrincipal()));
        stm.setObject(7, school.getContactNo());
//        System.out.println(stm);
        return  stm.executeUpdate();
    }
}
