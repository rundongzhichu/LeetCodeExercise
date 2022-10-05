package JavaBaseExercise;

import java.util.concurrent.CountDownLatch;

public class CountDownLathchDemo {
    public static void main(String[] args) throws InterruptedException {

    }

    private static void countDownLathDemo1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t上晚自习, 离开自习室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t ******  班长最后走关门！");
    }
}
