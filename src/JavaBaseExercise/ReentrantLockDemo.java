package JavaBaseExercise;


import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {

    public synchronized void senSMS(){
        System.out.println(Thread.currentThread().getId()+"\t invoke senSMS()");
        senEmails();
    }

    public synchronized void senEmails(){
        System.out.println(Thread.currentThread().getId()+"\t invoke senEmails()");
    }

    // ===============================================================================

    Lock lock = new ReentrantLock();


    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+"\t invoke get()");
            set();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+"\t invoke set()");
        }finally {
            lock.unlock();
        }
    }

}

/**
 * 可重入锁（也叫做递归锁）
 *
 * 指的是同一线程外层函数获得所之后，内层函数仍然能获取该锁的代码
 * 在同一线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 *
 * 也即是说，线程可以进入任何一个他已经拥有的锁所同步的代码块
 *
 * 15	 invoke senSMS()
 * 15	 invoke senEmails()
 * 16	 invoke senSMS()
 * 16	 invoke senEmails()
 *
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.senSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(()->{
            try {
                phone.senSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        }, "t2").start();

        System.out.println();
        System.out.println();
        System.out.println();

        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);

        t3.start();
        t4.start();

    }

}
