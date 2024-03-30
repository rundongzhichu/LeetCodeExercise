package demo.java20.base.thread;

import java.util.concurrent.locks.ReentrantLock;

public class VirtualThreadDemo {

    /**
     * 这个命令执行编译后带包路径的java文件
     * E:\IdeaProjects\LeetCodeExercise\Java20\src\main\java>java --enable-preview demo.java20.base.thread.VirtualThreadDemo
     * 这个命令可以直接执行源文件
     * java --enable-preview --source 20 VirtualThreadDemo.java -Xlint:preview
     *
     * idea 直接build运行的话会出现（这个可能是idea本身的问题，java命令行能执行）：
     java: 源发行版 16 与 --enable-preview 一起使用时无效
     （仅发行版 20 支持预览语言功能）
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
            ReentrantLock lock = new ReentrantLock();
            Thread.startVirtualThread(() -> {
                lock.lock();     // <------ 这里确保锁已经被另一个虚拟线程持有
            });
            Thread.sleep(1000);
            Thread.startVirtualThread(() -> {
                System.out.println("first");
                lock.lock();
                try {
                    System.out.println("second");
                } finally {
                    lock.unlock();
                }
                System.out.println("third");
            });
            Thread.sleep(Long.MAX_VALUE);
        }


}
