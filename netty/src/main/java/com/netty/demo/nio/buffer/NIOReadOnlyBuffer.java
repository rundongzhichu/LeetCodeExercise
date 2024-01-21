package com.netty.demo.nio.buffer;

import java.nio.ByteBuffer;

public class NIOReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for (int i = 0; i < 64; i++) {
            buffer.put((byte) i);
        }

        buffer.flip();
        ByteBuffer readOnlybuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlybuffer.getClass());
        while (readOnlybuffer.hasRemaining()) {
            System.out.println("readOnlybuffer = " + readOnlybuffer.get());
        }
        readOnlybuffer.put((byte) 100); // ReadOnlyBufferException
    }

}
