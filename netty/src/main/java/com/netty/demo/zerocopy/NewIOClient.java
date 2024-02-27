package com.netty.demo.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String filename = "netty/src/main/java/com/netty/demo/zerocopy/test.png";

        // 得到一个文件的channel
        FileChannel fileChannel = new FileInputStream(filename).getChannel();
        // 准备发送
        long startTime = System.currentTimeMillis();
        // 在linux下一个transfer 函数就可以完成传输
        // 在windows下，一次调用tensferto只能发送8M文件，就需要分段传输文件，要记录好传输的位置
        // transferTo底层使用的是零拷贝
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送的总的字节数 = " + transferCount + " 耗时：" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }

}
