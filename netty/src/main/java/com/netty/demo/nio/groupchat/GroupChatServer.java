package com.netty.demo.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final  int PORT = 6667;

    public GroupChatServer() {
        // 得到选择器
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                int cnt = selector.select(2000);
//                System.out.println("等待客户端连接。。。。");
                if(cnt>0){
                    Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                    while (selectionKeyIterator.hasNext()) {
                        SelectionKey key = selectionKeyIterator.next();
                        if(key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + " 上线！");
                        }

                        if(key.isReadable()) {
                            // 如果通道是可读状态，处理读取事件
                            readData(key);
                        }
                        selectionKeyIterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 执行完try或者catch的代码后执行的代码

        }
    }

    // 读取客户端消息
    private void readData(SelectionKey selectionKey) {
        SocketChannel sc =  null;
        try {
            sc = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = sc.read(byteBuffer);

            //根据coun的值做处理
            if(count > 0) {
                String msg = new String(byteBuffer.array(), 0, count);
                System.out.println("from client " + msg);

                // 向其他客户转发消息
                senInfoToOthers(msg, sc);
            }
        } catch (IOException e) {
            try {
                System.out.println(sc.getRemoteAddress() + " away...");
                // 取消注册
                selectionKey.cancel();
                // 关闭通道
                sc.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }

    private void senInfoToOthers(String msg, SocketChannel self) throws IOException {
        System.out.println("the server transfer message ....");
        for (SelectionKey key: selector.keys()) {
            // 通过key去除channel
            Channel targetChannel = key.channel();
            // 排除自己
            if(targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }

}
