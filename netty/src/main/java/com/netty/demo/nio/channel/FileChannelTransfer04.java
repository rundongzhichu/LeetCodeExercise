package com.netty.demo.nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class FileChannelTransfer04 {

    public static void main(String[] args) throws IOException {
        // 创建相关流
        FileInputStream fileInputStream = new FileInputStream("netty/src/main/java/com/netty/demo/nio/channel/test.png");
        FileOutputStream fileOutputStream = new FileOutputStream("netty/src/main/java/com/netty/demo/nio/channel/test-trans.png");

        // 获取各个流对应的channel
        FileChannel channelIn = fileInputStream.getChannel();
        FileChannel channelOut = fileOutputStream.getChannel();

        // 使用transferFrom 完成拷贝
        channelOut.transferFrom(channelIn, 0, channelIn.size());

        // 关闭相关通道和流
        channelIn.close();
        channelOut.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

}
