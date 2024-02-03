package com.netty.demo.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws IOException {
        // 创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个selector对象
        Selector selector = Selector.open();
        // 绑定一个端口6666，在服务端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 把serverSocketChannel 注册到selector 关心事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 循环等待客户端连接
        while (true) {
            if(selector.select(3000) == 0) { // 等待1s没有事件发生
                System.out.println("服务器等待了3s， 无连接");
                continue;
            }
            /*
            如果返回的大于0，就获取到相关的selectionKey集合
            1.如果返回的大于0，表示已经获取到关注的事件
            2.selector.selectedKeys() 返回关注事件的集合
            通过selectionKeys反向获取通道
             */
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            System.out.println("selectionKeySet 的数量 = " + selectionKeySet.size());

            // 遍历Set<SelectionKey>
            Iterator<SelectionKey> keyIterator = selectionKeySet.iterator();
            while (keyIterator.hasNext()) {
                // 获取selectionKey
                SelectionKey key = keyIterator.next();
                // 根据key对应的通道发生的事件，作相应的处理
                if(key.isAcceptable()) { // 如果是OP_ACCEPT，有新的客户端连接
                    //该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 将socketchannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功 生成一个socketChannel " + socketChannel.hashCode());
                    // 将socketChannel注册到selector, 关注事件为OP_READ，同时给socketChannel关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接成功后，注册的selectionKey数量 = " + selector.keys().size());
                }
                if(key.isReadable()){ // 发生OP_READ
                    // 通过key 反向获取对应的channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    int len = 0;
                    while((len = channel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println("客户端发送过来了数据： " + new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
                keyIterator.remove();
            }
        }
    }

}
