package com.netty.demo.nio.buffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class NIOByteBufferPutGet {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        // 类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(123L);
        buffer.putChar('测');
        buffer.putShort((short) 4);

        // 取出
        buffer.flip();
        System.out.println("args = " + Arrays.deepToString(args));

        System.out.println("buffer.getInt() = " + buffer.getInt());
        System.out.println("buffer.getLong() = " + buffer.getLong());
        System.out.println("buffer.getChar() = " + buffer.getChar());
        System.out.println("buffer.getShort() = " + buffer.getShort());
    }

}
