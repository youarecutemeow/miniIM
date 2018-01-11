package databases;

import log.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDB {
    public static int insertMessage(Message message, Connection connection){
        Log.i("动作：添加信息 "+message.toString());
        int ret = DbActionStatus.UNKNOWWRONG;
        PreparedStatement ps = null;
        if(connection == null){
            return DbActionStatus.DATABASEWRONG;
        }
        try {
            String sql = "use miniIM";
            ps = connection.prepareStatement(sql);
            ps.execute();
            Log.i("使用数据库 miniIM");
            sql = "insert into Message(what,content,time,who,`from`) value(?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(3,message.getTime());
            ps.setString(4,message.getWho());
            ps.setString(5,message.getFrom());
            ps.setString(2,message.getContent());
            ps.setInt(1,message.getWhat());
            ps.execute();
            Log.i("成功执行" + sql);
            ret = DbActionStatus.SUCCESS;
        } catch (SQLException e) {
            if(e instanceof DataTruncation){
                Log.e("添加信息 Message: "+message.toString()+" 失败,字段过长");
                ret = DbActionStatus.TOOLONG;
            } else{
                e.printStackTrace();
                ret = DbActionStatus.UNKNOWWRONG;
            }
        } finally {
            try {
                ps.close();
                DataBaseLink.closeConnection(connection);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ret;
        }
    }

    public static List<Message> getMessagesByID(String id, Connection connection){
        Log.i("动作：查询信息 "+"发送给 ："+id);
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "use miniIM";
            ps = connection.prepareStatement(sql);
            ps.execute();
            sql = "select * from Message where who = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,id);
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                Message message = Message.instance(2).setWhat(resultSet.getInt(1)).
                        setContent(resultSet.getString(2)).setTime(resultSet.getString(3))
                        .setWho(resultSet.getString(4)).setFrom(resultSet.getString(5));
                messages.add(message);
                Log.i("message :"+message.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                ps.close();
                resultSet.close();
                DataBaseLink.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return messages;
        }

    }

}

