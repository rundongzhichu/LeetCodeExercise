package com.netty.demo.nio.buffer;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 *
 * scattering: 将数据写入到buffer时，可以采用buffer数组，依次写入
 * gathering： 从buffer读取数据时，可以采用buffer数组，依次读
 *
 */
public class NIOBufferScatteringAndGathering {

    /*
 思路
    1. 创建线程池
    2.如果有客户端连接，就创建一个线程，与之通讯

    创建好server以后，可以打开cmd窗口，执行 telnet 127.0.0.1 8888
    连接到服务器，然后快捷键ctrl + ], 执行send "message" 就可以发送消息了

 */
    public static void main(String[] args) throws Exception {
        // 使用ServerSocketChannel 和SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);


        //绑定端口到socket， 并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        // 创建Buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        // 等待客户端连接（telnet）
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLenght = 8;

        // 循环读取数据
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLenght) {
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead = " + byteRead);

                // 使用流打印，查看当前buffer的position 和limit
                Arrays.asList(byteBuffers).stream().map(buffer -> "position= " + buffer.position()
                                                            + ", limit=" + buffer.limit()).forEach(System.out::println);
            }

            //将所有的ByteBuffer进行flip
            Arrays.asList(byteBuffers).stream().forEach(byteBuffer -> byteBuffer.flip());

            //将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLenght) {
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }

            // 将所有的buffer进行clear
            Arrays.asList(byteBuffers).stream().forEach(byteBuffer -> byteBuffer.clear());
            System.out.println("byteRead = " + byteRead + " byteWrite = " + byteWrite + " messagelength = " + messageLenght);
        }
    }

}
