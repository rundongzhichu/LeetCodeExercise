package com.netty.demo.bio.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {

    public static void main(String[] args) throws IOException {
        // 线程池机制

        /*
         思路
            1. 创建线程池
            2.如果有客户端连接，就创建一个线程，与之通讯

            创建好server以后，可以打开cmd窗口，执行 telnet 127.0.0.1 8888
            连接到服务器，然后快捷键ctrl + ], 执行send "message" 就可以发送消息了

         */
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server started!");

        while (true) {
            final Socket socket = serverSocket.accept();
            System.out.println("A client connected to the server!");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // 与客户端的通信逻辑
                    try {
                        handler(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void handler(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        try {
            System.out.println("线程信息 id = " + Thread.currentThread().getId() + " name = " + Thread.currentThread().getName());
            InputStream inputStream = socket.getInputStream();
            // 循环读取客户端发送的数据
            while (true){
                int read = inputStream.read(bytes);
                if(read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

}
