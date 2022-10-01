package JavaBaseExercise;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

class User{
    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    String username;
    int age;


}

public class ABADemo {

    static  AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {

        System.out.println("====> ABA 问题的产生");
        new Thread(()->{
            System.out.println("atomicReference = " + atomicReference.compareAndSet(100,101) + "\t" + atomicReference.get().toString());
            System.out.println("atomicReference = " + atomicReference.compareAndSet(101,100) + "\t" + atomicReference.get().toString());
        }, "t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("atomicReference = " + atomicReference.compareAndSet(100,2019) + "\t" + atomicReference.get().toString());
        }, "t2").start();

        System.out.println("====> ABA 问题的解决");
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+ "\tstamp = " + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+ "\tstamp = " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+ "\tstamp = " + atomicStampedReference.getStamp());
        }, "t3").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+ "\tstamp = " + atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp+1);
            System.out.println("result = " + result);
            System.out.println(Thread.currentThread().getName()+ "\tstamp = " + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+ "\t当前时间最新值：" + atomicStampedReference.getReference());
        }, "t4").start();

    }

    private static void ABADemo() {
        User z3 = new User("z3", 22);
        User l4 = new User("l4", 23);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println("atomicReference = " + atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get().toString());
        System.out.println("atomicReference = " + atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get().toString());
    }
}
