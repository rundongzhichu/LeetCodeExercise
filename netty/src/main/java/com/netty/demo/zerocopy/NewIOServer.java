package com.netty.demo.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(address);

        // 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCnt = 0;
            while (-1 != readCnt) {
                try {
                    readCnt = socketChannel.read(byteBuffer);
                } catch (Exception e) {
//                e.printStackTrace();
                    break;
                }
                byteBuffer.rewind(); // 倒带position = 0， mark作废
            }
        }
    }

}
