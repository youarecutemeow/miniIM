package databases;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;

public class test {
    public static void main(String[] args)  {
       // UsersDB.serachUser("w1x",DataBaseLink.openDataBase("root","mysqlpw"));
        //UsersDB.createUser("wangxin","w",DataBaseLink.openDataBase("root","mysqlpw"));
            //InputStream inputStream = new FileInputStream("//home//wangxin//miniIMserverLog.txt");
            //String photo = "123123";
         //   InputStream inputStream = new ByteArrayInputStream(photo.getBytes());
        //Connection connection = DataBaseLink.openDefualtDatabase();
            //InformationDB.addInformation("你有可爱了","哈哈",inputStream,"woshishauige",1,22,DataBaseLink.openDataBase("root","mysqlpw"));
           //InformationDB.getInformationByID("你有可爱了喵",DataBaseLink.openDataBase("root","mysqlpw"));
       // InformationDB.changeAlias("你有可爱了","asdasf",connection);
       // InformationDB.changeAge("你有可爱了",57,connection);
       //  InformationDB.changeProfile("你有可爱了","帅得一批",connection);
       // InformationDB.changeSex("你有可爱了",0,connection);
       // DataBaseLink.closeConnection(connection);
       // Message message = Message.instance(1).setContent("你是谁a").setFrom("王鑫").setWhat(2).setWho("wangxin");
       // MessageDB.insertMessage(message,DataBaseLink.openDefualtDatabase());
        MessageDB.getMessagesByID("wangxin",DataBaseLink.openDefualtDatabase());
    }
}
