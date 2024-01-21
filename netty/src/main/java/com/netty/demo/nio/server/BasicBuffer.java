package com.netty.demo.nio.server;

import java.nio.IntBuffer;

public class BasicBuffer {

    public static void main(String[] args) {
        // 创建buffer可以存储5个int， 存入数据
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < 5; i++) {
            intBuffer.put(10 + i);
        }

        // 读取数据， 首先用flip 进行读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println("intBuffer = " + intBuffer.get());
        }
    }

}
