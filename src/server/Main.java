package server;

import log.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] agrs) {
        HashMap<String,CommunicateThread> communications = new HashMap<>();
        try {
            ServerSocket serverSocket = new ServerSocket(8001);
            while (true) {
                Socket socket = serverSocket.accept();
                InetAddress inetAddress = serverSocket.getInetAddress();
                Log.i("客户端：" + inetAddress.getHostAddress() + " 已连接");
                CommunicateThread communicateThread = new CommunicateThread(socket);
                Thread thread = new Thread(communicateThread);
                thread.start();
                communications.put(inetAddress.getHostAddress(),communicateThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
