package com.netty.demo.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7001);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            try{
                byte[] bytes = new byte[4096];
                while (true) {
                    int cnt = dis.read(bytes, 0, bytes.length);
                    if(-1 == cnt) break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
