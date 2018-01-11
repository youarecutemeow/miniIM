package client;

import databases.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static Thread thread;
    private static Runnable runnable;
    public static void main(String[] args){
        Socket socket;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        for(int i=0;i<1;i++){
            {
                try {
                    socket = new Socket("localhost",8001);
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    inputStream =  new ObjectInputStream(socket.getInputStream());
                    runnable = new CommunicateRunnable(inputStream);
                    thread = new Thread(runnable);
                    outputStream.writeObject(new Message(1));
                    thread.stop();
                    socket.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}

