package com.netty.demo.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Arrays;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //创建BossGroup和WorkerGroup
        /**
         * 说明：
         * 1.创建两个线程组bossGroup和workerGroup
         * 2.bossGroup只是处理连接请求，真正的和客户端业务处理，会交给workerGroup完成
         * 3.两个都是无限循环
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // 创建服务端的启动对象，配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup). //设置两个线程组
                channel(NioServerSocketChannel.class)//使用NioServerSocketChannel作为服务器通道实现
        .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列得到的连接个数
        .childOption(ChannelOption.SO_KEEPALIVE, true)//保持活动连接状态
        .childHandler(new ChannelInitializer<SocketChannel>() {//通过匿名内部类创建一个通道测试对象
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(null);
            }
        });// 给workerGroup的EventLoop对应管道设置处理器

        System.out.println("......server is ready....");
        // 绑定一个端口并且同步，生成一个ChannelFuture对象
        ChannelFuture cf = bootstrap.bind(6668).sync();

        // 对关闭通道进行监听
        cf.channel().closeFuture().sync();
    }

}
