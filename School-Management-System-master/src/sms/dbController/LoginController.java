package sms.dbController;

import sms.db.DBConnection;
import java.sql.*;

public class LoginController {

    public static boolean Login(String username, String password) throws SQLException, ClassNotFoundException {

        String SQL = "SELECT * FROM admin WHERE admin_name=? AND admin_password=?";

        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        stm.setObject(1, username);
        stm.setObject(2, password);
//        System.out.println(stm);
        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
//            System.out.println(rst.getString(1));
//            System.out.println(rst.getString(2));
//            System.out.println(rst.getString(3));
            if(!rst.getString(2).equals(username)){return false;}
            String pwd = rst.getString(3);
            if (pwd.equals(password)) {return true;}
        }
        return false;

}
}
