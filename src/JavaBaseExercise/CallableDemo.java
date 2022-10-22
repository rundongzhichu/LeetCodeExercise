package JavaBaseExercise;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("*************** Come in Callable ***********");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}

/**
 * 多线程三种获得多线程的方式
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        // 多个线程执行一个futureTask对象，只会执行一次，如果要执行多次，就创建多个futuretask对象
        Thread t1  = new Thread(futureTask, "AA");
        t1.start();
        Thread t2  = new Thread(futureTask, "BB");
        t2.start();
        // int result1 = futureTask.get(); // 这里会阻塞主线程，直到callable计算完成

        System.out.println(Thread.currentThread().getName() + "********");
        int result0 = 100;

        while (!futureTask.isDone()){

        }
        int result1 = futureTask.get();
        /* futureTask.get() 获得callable线程的计算结果，如果没有计算完成，会导致阻塞，直到计算完成 */
        System.out.println("***** results = " + (result0 + result1));
    }
}
