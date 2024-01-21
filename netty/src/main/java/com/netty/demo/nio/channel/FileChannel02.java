package com.netty.demo.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel02 {

    public static void main(String[] args) throws IOException {
        // 创建一个输入流-》channel
        File file = new File("netty/src/main/java/com/netty/demo/nio/channel/file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        // fileInputStream 获取一个fileChannel
        FileChannel fileChannel = fileInputStream.getChannel();
        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // 读取数据byteBuffer
        fileChannel.read(byteBuffer);
        //将byteBuffer进行flip
        byteBuffer.flip();
        // 将byteBuffer数据写入到fileChannel
        System.out.println("byteBuffer = " + new String(byteBuffer.array()));
        fileInputStream.close();
    }

}
