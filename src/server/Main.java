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
            Log.i("服务器已开启");
            while (true) {
                Socket socket = serverSocket.accept();
                Log.i("连接建立中...");
                InetAddress inetAddress = serverSocket.getInetAddress();
                Log.i("客户端：" + inetAddress.getLocalHost() + " 已连接");
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
