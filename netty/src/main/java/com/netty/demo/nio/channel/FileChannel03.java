package com.netty.demo.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel03 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("netty/src/main/java/com/netty/demo/nio/channel/file01.txt");
        FileChannel fileChannelIn = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("netty/src/main/java/com/netty/demo/nio/channel/file02.txt");
        FileChannel fileChannelOut = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(2);

        while (true) {
            byteBuffer.clear();
            int read = fileChannelIn.read(byteBuffer);
            System.out.println("read = " + read);
            if(read == -1) break;
            byteBuffer.flip();
            fileChannelOut.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

}
