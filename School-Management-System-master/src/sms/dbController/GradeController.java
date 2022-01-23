package sms.dbController;

import sms.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class GradeController {

    public static ArrayList<String> getGrades() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery("Select gradeId From grade");

        ArrayList<String>gradeList=new ArrayList<>();
        while(rst.next()){
            gradeList.add(rst.getString("gradeId"));
        }
        return gradeList;
    }

}
