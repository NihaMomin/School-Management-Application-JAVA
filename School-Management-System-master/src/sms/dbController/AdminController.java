package sms.dbController;

import sms.db.DBConnection;
import sms.model.Admin;

import java.sql.*;
import java.util.ArrayList;

public class AdminController {

    public static int addAdmin(Admin admin)throws ClassNotFoundException,SQLException {
        String SQL="INSERT INTO admin (admin_id, admin_name, admin_password, status) VALUES(?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        stm.setObject(1, admin.getAdmin_id());
        stm.setObject(2, admin.getAdmin_name());
        stm.setObject(3, admin.getPassword());
        stm.setObject(4, admin.getStatus());
//        System.out.println(stm);
        return  stm.executeUpdate();
    }

    public static int updateAdmin(Admin admin) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE admin SET admin_id = ?, admin_name= ? ,admin_password= ?, status = ?  WHERE admin_id= '" +admin.getAdmin_id()+ "'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, admin.getAdmin_id());
        stm.setObject(2, admin.getAdmin_name());
        stm.setObject(3, admin.getPassword());
        stm.setObject(4, admin.getStatus());
        return  stm.executeUpdate();
    }

    public static int deleteAdmin(Integer admin_id) throws ClassNotFoundException, SQLException {

        String sql = "DELETE FROM admin WHERE admin_id ='"+admin_id+"'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        return  stm.executeUpdate();
    }

    public static ArrayList <Admin> getAllAdmin() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery("Select * From admin");
        ArrayList <Admin> adminList = new ArrayList<>();
        while(rst.next()){
            Admin admin;
            admin = new Admin(rst.getInt(1),rst.getString(2),rst.getString(3), rst.getString(4));
            adminList.add(admin);
        }
        return adminList;
    }

    public static Admin searchAdmin(Integer admin_id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM admin WHERE admin_id = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, admin_id);
//        System.out.println(stm);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            Admin admin = new Admin(rst.getInt("admin_id"),rst.getString("admin_name"),rst.getString("admin_password"), rst.getString("status"));
            return admin;
        }
        return null;
    }
}
