package databases;

import log.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseLink {
    public static Connection openDefualtDatabase(){
        return openDataBase("root","mysqlpw");
    }
    public static Connection openDataBase(String user,String pw){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Log.i("驱动加载成功");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniIM?useUnicode=true&amp;characterEncoding=UTF8 ",user,pw);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i("连接失败");
            return null;
        }
        Log.i("用户:",user,"密码:",pw,"已连接");
        return connection;
    }
    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Log.i("断开连接成功\n\n");

        }
    }
}
