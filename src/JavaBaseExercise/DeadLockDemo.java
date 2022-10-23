package JavaBaseExercise;


import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{

    private String LockA;
    private String LockB;

    public HoldLockThread(String lockA, String lockB) {
        LockA = lockA;
        LockB = lockB;
    }

    @Override
    public void run() {
        synchronized (LockA){
            System.out.println(Thread.currentThread().getName() + "\t 持有： "+ LockA + "\t 尝试获得："+ LockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LockB){
                System.out.println(Thread.currentThread().getName() + "\t 持有： "+ LockB + "\t 尝试获得："+ LockA);

            }
        }
    }
}


/**
 *
 * 死锁是指两个或者两个以上的进程在执行过程中，
 * 因争夺资源而造成的互相等待的现象。
 * 如无外力干涉将无法推进下去
 *
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String LockA = "LockA";
        String LockB = "LockB";

        new Thread(new HoldLockThread(LockA, LockB), "AAA").start();
        new Thread(new HoldLockThread(LockB, LockA), "BBB").start();

        /**
         * jps java虚拟机的进程查看命令
         *  jps -l 列出当前所有的java启动进程
         *  jstack id 打印出对应id进程的虚拟机栈
         *
         */
    }
}
