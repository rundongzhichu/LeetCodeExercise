package JavaBaseExercise.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Executor Executors
 *
 * 第四种获得、使用java多线程的方式，线程池
 *
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());


        // 一个池子5个处理线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 一个池子1个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 一个池子N个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 模拟10个用户办理业务，每个用户就是一个来自外部的请求线程,
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " 办理业务！");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    private static void fixedPoolDemo() {
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 一个池子5个处理线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // 模拟10个用户办理业务，每个用户就是一个来自外部的请求线程,
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " 办理业务！");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
