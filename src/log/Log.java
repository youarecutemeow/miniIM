package log;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class Log {
    public static void i(String... args){
        SimpleDateFormat df = new SimpleDateFormat("MM/dd HH:mm:ss");
        StringBuilder sb = new StringBuilder("["+df.format(new Date())+"]LogI : ");
        for(String str:args){
            sb.append(str+" ");
        }
        System.out.println(sb.toString());
        sb.append("\n");
        logToFile(sb.toString());
    }
    public static void d(String... args){
        SimpleDateFormat df = new SimpleDateFormat("MM/dd HH:mm:ss");
        StringBuilder sb = new StringBuilder("["+df.format(new Date())+"]LogD : ");
        for(String str:args){
            sb.append(str+" ");
        }
        System.out.println(sb.toString());
    }
    public static void e(String... args){
        SimpleDateFormat df = new SimpleDateFormat("MM/dd HH:mm:ss");
        StringBuilder sb = new StringBuilder("["+df.format(new Date())+"]LogE : ");
        for(String str:args){
            sb.append(str+" ");
        }
        System.out.println(sb.toString());
        sb.append("\n");
        logToFile(sb.toString());
    }
    public static void logToFile(String str){
        File file = new File("//var//log//miniIMserverLog.txt");
        if (!file.exists()){
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(file.length()/1024/1024>100){
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.append(str);
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
