package JavaBaseExercise;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t writing now " + String.valueOf(value));
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t writing complete " + String.valueOf(value));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

    public Object get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t getting now ");
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t getting complete "  + String.valueOf(o));
            return o;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
        return null;
    }

}

/**
 *  多个线程同时读一个资源类没有仍和问题， 所以为了满足并发量，读取共享资源可以同时进行
 *  但是
 *  如果一个线程想去写共享资源，就不应该再有其他线程可以对该资源进行读写
 *
 *  小结：
 *  读读能共存
 *  读写不能共存
 *  写写不能共存
 *
 *  写操作 原子独占
 *
 *
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        Map map = new ConcurrentHashMap();

        for (int i = 0; i < 5; i++) {
            final  int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final  int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}
