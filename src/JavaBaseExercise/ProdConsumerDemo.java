package JavaBaseExercise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try{
            //1 判断
            while (number != 0){
                // 等待， 不能生产
                condition.await(); // 该方法会释放锁并进入等待
            }
            // 2 干活
            number ++;
            System.out.println(Thread.currentThread().getName() + "\t"+ number);
            // 3 通知唤醒
            condition.signal(); // 随机唤醒一个await中的线程
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try{
            //1 判断
            while (number == 0){ // 通过while循环判断，防止虚假唤醒， 用if的话，线程数一多可能会出错
                // 等待， 不能生产
                condition.await();
            }
            // 2 干活
            number --;
            System.out.println(Thread.currentThread().getName() + "\t"+ number);
            // 3 通知唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

/**
 *
 * 一个初始值为0的变量，两个线程对其进行交替操作，一个加一，一个减一，来5轮
 *
 * 1 线程  操作 资源类
 * 2 判断 干活  通知
 * 3 防止虚假唤醒机制
 *
 */
public class ProdConsumerDemo {
    public static void main(String[] args) {
        // traditional
        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try{
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();


        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try{
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }
}
