package client;

import databases.Message;

import java.io.EOFException;
import java.io.ObjectInputStream;

public class CommunicateRunnable implements Runnable{
    private ObjectInputStream inputStream;
    public CommunicateRunnable(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        Message message = null;

        try {
            while ((message = (Message)inputStream.readObject())!=null){
                System.out.println(message.toString());
                System.out.println(Thread.currentThread().getId());
                message = null;
            }
            System.out.println("over");

        } catch (Exception e){
            if(e instanceof EOFException){
                System.out.println("over");
                return;
            }
            e.printStackTrace();
        }


    }

}
