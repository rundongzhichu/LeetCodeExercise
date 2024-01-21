package com.netty.demo.nio.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 *
 * 说明:
 * 1.MappedByteBUffer 可以让文件在堆外内存中修改，操作系统不需要拷贝一次
 *
 */
public class NIOMappedByteBUffer {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("netty/src/main/java/com/netty/demo/nio/buffer/file01.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        /**
         * param1: FileChannel.MapMode.READ_WRITE 使用的读写模式
         * param2： 0  可以直接修改的起始位置
         * param3： 5 映射到内存的大小，即将文件的多少个字节映射到内存
         *
         * 可以修改的范围就是0-5 字节
         * map 实际类型是DirectByteBuffer
         */
        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0, (byte) 'H');
        map.put(3, (byte) '9');
        randomAccessFile.close();
        System.out.println("修改成功！");
    }

}
