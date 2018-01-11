package databases;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import log.Log;

import java.io.InputStream;
import java.sql.*;


public class InformationDB {


    public static int addInformation(String id, String alias, InputStream photo, String profile, int sex, int age, Connection connection) {
        Log.i("动作：添加用户 "+id);
        PreparedStatement ps = null;
        int ret = DbActionStatus.UNKNOWWRONG;
        if (connection == null) {
            return DbActionStatus.DATABASEWRONG;
        }
        try {
            String sql = "use miniIM";
            ps = connection.prepareStatement(sql);
            ps.execute();
            Log.i("使用数据库 miniIM");
            sql = "insert into Information(ID,Alias,Photo,Profile,Sex,Age) value" +
                    "(?,'" + alias + "'," + "?" + ",'" + profile + "'," + sex + "," + age + ");";
            ps = connection.prepareStatement(sql);
            ps.setBinaryStream(2, photo);
            ps.setString(1, id);
            ps.execute();
            Log.i("成功执行" + sql);
            ret = DbActionStatus.SUCCESS;

        } catch (SQLException e) {
            if(e instanceof MySQLIntegrityConstraintViolationException){
                Log.e("添加信息 id: "+id+" 失败,已经有了信息，请使用修改");
                ret = DbActionStatus.BEENUSED;
            } else if(e instanceof DataTruncation){
                Log.e("添加信息 id: "+id+" 失败,字段过长");
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


    public static Information getInformationByID(String id, Connection connection) {
        Log.i("动作：搜索用户信息 "+id);
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Information information = null;
        Boolean isSuccess = false;
        if (connection == null) {
            return null;
        }
        try {
            String sql = "use miniIM";
            ps = connection.prepareStatement(sql);
            ps.execute();
            sql = "select * from Information where ID = '" + id + "'";
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String ID = resultSet.getString(1);
                String alias = resultSet.getString(2);
                Blob photo = resultSet.getBlob(3);
                String profile = resultSet.getString(4);
                int sex = resultSet.getInt(5);
                int age = resultSet.getInt(6);
                long len = photo.length();
                byte[] bytes = photo.getBytes(1,(int)len);
                information = new Information(ID, alias, bytes ,profile, sex, age);
                System.out.println(information.toString());
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if(isSuccess){
                Log.i("查询成功 "+"id: "+id);
            } else {
                Log.e("查询失败 id: "+id);
            }
            try {
                ps.close();
                resultSet.close();
                DataBaseLink.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return information;
        }

    }

    //使用change需要手动断开数据库连接
    public static Boolean changeAlias(String id,String alias,Connection connection){
        Log.i("动作：更换昵称 "+id);
        Boolean isSuccess = false;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE `miniIM`.`Information` SET `Alias`=? WHERE `ID`=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,alias);
            preparedStatement.setString(2,id);
            preparedStatement.execute();
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(isSuccess){
                Log.i("更换成功 id:"+id+" alias:"+alias);
            } else {
                Log.i("更换失败 id:"+id+" alias:"+alias);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return isSuccess;
        }
    }

    public static Boolean changeAge(String id,int age,Connection connection){
        Log.i("动作：更换年龄 "+id);
        Boolean isSuccess = false;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE `miniIM`.`Information` SET `Age`=? WHERE `ID`=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,age);
            preparedStatement.setString(2,id);
            preparedStatement.execute();
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(isSuccess){
                Log.i("更换成功 id:"+id+" age:"+age);
            } else {
                Log.i("更换失败 id:"+id+" age:"+age);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return isSuccess;
        }
    }

    public static Boolean changePhoto(String id,InputStream photo,Connection connection){
        Log.i("动作：更换头像 "+id);
        Boolean isSuccess = false;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE `miniIM`.`Information` SET `Photo`=? WHERE `ID`=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1,photo);
            preparedStatement.setString(2,id);
            preparedStatement.execute();
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(isSuccess){
                Log.i("更换成功 id:");
            } else {
                Log.i("更换失败 id:");
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return isSuccess;
        }
    }

    public static Boolean changeProfile(String id,String profile,Connection connection){
        Log.i("动作：更换简介 "+id);
        Boolean isSuccess = false;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE `miniIM`.`Information` SET `Profile`=? WHERE `ID`=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,profile);
            preparedStatement.setString(2,id);
            preparedStatement.execute();
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(isSuccess){
                Log.i("更换成功 id:"+id+" profile:"+profile);
            } else {
                Log.i("更换失败 id:"+id+" profile:"+profile);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return isSuccess;
        }
    }

    public static Boolean changeSex(String id,int sex,Connection connection){
        Log.i("动作：更换性别 "+id);
        Boolean isSuccess = false;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE `miniIM`.`Information` SET `Sex`=? WHERE `ID`=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,sex);
            preparedStatement.setString(2,id);
            preparedStatement.execute();
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(isSuccess){
                Log.i("更换成功 id:"+id+" sex:"+sex);
            } else {
                Log.i("更换失败 id:"+id+" sex:"+sex);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return isSuccess;
        }
    }
}