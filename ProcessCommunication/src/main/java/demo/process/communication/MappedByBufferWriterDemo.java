package demo.process.communication;

import java.io.RandomAccessFile;
        import java.nio.MappedByteBuffer;
        import java.nio.channels.FileChannel;
        import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;

public class MappedByBufferWriterDemo {

    private static RandomAccessFile raf;
    public static void main(String[] args) throws Exception {
        //建立文件和内存的映射，即时双向同步
        raf = new RandomAccessFile("ProcessCommunication/src/main/java/demo/process/communication/data.dat", "rw");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, 1024);

        //清除文件内容 ，对 MappedByteBuffer 的操作就是对文件的操作
        for(int i=0;i<1024;i++){
            mbb.put(i,(byte)0);
        }

        //从文件的第二个字节开始，依次写入 A-Z 字母，第一个字节指明当前操作的位置
        for(int i=65;i<91;i++){
            int index = i-63;
            int flag = mbb.get(0);  //可读标置第一个字节为 0
            if(flag != 0){          //不是可写标示 0，则重复循环，等待
                i--;
                continue;
            }
            mbb.put(0,(byte)1);         //正在写数据，标志第一个字节为 1
            mbb.put(1,(byte)(index));   //文件第二个字节说明，写数据的位置
            System.out.println(System.currentTimeMillis() +  ":position:" + index +"write:" + (char)i);
            mbb.put(index,(byte)i);     //index 位置写入数据
            mbb.put(0,(byte)2);         //置可读数据标志第一个字节为 2
            Thread.sleep(3000);
        }
    }
}

class MappedByBufferWriterDemoLockVersion {
    private static RandomAccessFile raf;
    public static void main(String[] args) throws Exception {
        //获取随机存取文件对象，建立文件和内存的映射，即时双向同步
        raf = new RandomAccessFile("ProcessCommunication/src/main/java/demo/process/communication/data.dat", "rw");
        FileChannel fc = raf.getChannel();      //获取文件通道
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1024);  //获取共享内存缓冲区
        FileLock flock=null;

        for(int i=65;i<91;i++){
            //阻塞独占锁，当文件锁不可用时，当前进程会被挂起
            flock=fc.lock();
            System.out.println(System.currentTimeMillis() +  ":write:" + (char)i);
            mbb.put(i-65,(byte)i);  //从文件第一个字节位置开始写入数据
            flock.release();        //释放锁
            Thread.sleep(1000);
        }

    }
}


