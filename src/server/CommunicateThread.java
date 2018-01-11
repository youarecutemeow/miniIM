package server;

import databases.Message;

import java.io.*;
import java.net.Socket;

public class CommunicateThread implements Runnable {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    public CommunicateThread(Socket socket) {
        this.socket = socket;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

}
