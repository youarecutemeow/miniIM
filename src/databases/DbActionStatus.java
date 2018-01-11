package databases;

public class DbActionStatus {
    public static final int UNKNOWWRONG = -1; //未知错误
    public static final int SUCCESS = 0;      //成功
    public static final int BEENUSED = 1;     //ID被占用
    public static final int TOOLONG = 2;      //字段过长
    public static final int DATABASEWRONG = 3;//数据库连接错误
    public static final int USEREXIST = 4;    //用户存在
    public static final int NOEXIST = 5;      //用户不存在
}
