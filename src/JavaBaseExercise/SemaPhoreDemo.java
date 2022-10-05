package JavaBaseExercise;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaPhoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟三个车位

        // 模拟6部车
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位！");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t 停车3秒后离开！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

}
