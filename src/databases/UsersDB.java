package databases;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import log.Log;

import java.sql.*;

public class UsersDB {

    public static int createUser(String id,String pw,Connection connection){
        Log.i("动作：注册用户 "+id);
        Statement statement = null;
        int ret = DbActionStatus.UNKNOWWRONG;
        if(connection == null){
            return DbActionStatus.DATABASEWRONG;
        }
        try {
            statement = connection.createStatement();
            statement.executeQuery("use miniIM");
            Log.i("使用数据库 miniIM");
            statement.execute("insert into Users(ID,PW) value('" + id + "','" + pw + "')");
            Log.i(id+" 注册成功");
            ret = DbActionStatus.SUCCESS;
        } catch (SQLException e) {
            if(e instanceof MySQLIntegrityConstraintViolationException){
                Log.i("ID已经被使用");
                ret = DbActionStatus.BEENUSED;
            }
            else{
                e.printStackTrace();
                ret = DbActionStatus.UNKNOWWRONG;
            }
        } finally {
            DataBaseLink.closeConnection(connection);
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ret;
        }


    }
    public static int serachUser(String id,Connection connection){
        Log.i("动作：搜索用户 "+id);
        Statement statement = null;
        ResultSet resultSet = null;
        int ret = DbActionStatus.UNKNOWWRONG;
        if(connection == null){
            return DbActionStatus.DATABASEWRONG;
        }
        try {
            statement = connection.createStatement();
            statement.execute("use miniIM");
            Log.i("使用数据库 miniIM");
            resultSet = statement.executeQuery("select * from Users where ID ='"+id+"';");
            if(resultSet.next()){
                if(resultSet.getString(1)!=null){
                    Log.i("搜索用户: "+id+" 存在");
                    ret = DbActionStatus.USEREXIST;
                }
            } else {
                Log.i("搜索用户: "+id+" 不存在");
                ret = DbActionStatus.NOEXIST;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            ret = DbActionStatus.UNKNOWWRONG;
        } finally {
            try {
                statement.close();
                DataBaseLink.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ret;
        }
    }
}
