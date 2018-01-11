package databases;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable{
    public static final int TEXT = 1;
    public static final int REGISTER = 2;
    public static final int LOGIN = 3;
    public static final int ANSWER = 4;
    public static final int HEARTBEAT = 5;
    public static final int CANCLE = 6;
    private int what;
    private String content;
    private String time;
    private String who;
    private String from;
    private int id;
    public Message(int type){
        switch (type){
            case 1:
                SimpleDateFormat df = new SimpleDateFormat("MM/dd HH:mm:ss");
                time = df.format(new Date());
                break;
            case 2:
                break;
            case 3:
                break;

        }
    }

    public static Message instance(int type){
        return new Message(type);
    }
    public Message setTime(String time){
        this.time = time;
        return this;
    }
    public Message setWho(String who){
        this.who = who;
        return this;
    }

    public Message setFrom(String from){
        this.from = from;
        return this;
    }

    public Message setContent(String content){
        this.content = content;
        return this;
    }

    public Message setWhat(int what){
        this.what = what;
        return this;
    }

    public Message setId(int id){
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getWhat() {
        return what;
    }

    public String getFrom() {
        return from;
    }

    public String getTime() {
        return time;
    }

    public String getWho() {
        return who;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("from :"+from).append(" who :"+who).append(" what:"+what).append(" content:"+content);
        return sb.toString();
    }
}
