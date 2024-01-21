package com.netty.demo.nio.channel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel01 {

    // fileChannel 本地文件写数据
    public static void main(String[] args) throws IOException {
        String str = "Hello, World!";

        // 创建一个输出流-》channel
        FileOutputStream fileOutputStream = new FileOutputStream(new File("netty/src/main/java/com/netty/demo/nio/channel/file01.txt"));

        // 通过fileOutputStream 获取一个fileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();
        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将str放到byteBuffer
        byteBuffer.put(str.getBytes());
        //将byteBuffer进行flip
        byteBuffer.flip();
        // 将byteBuffer数据写入到fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

}
