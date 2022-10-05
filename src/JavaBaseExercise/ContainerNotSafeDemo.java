package JavaBaseExercise;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合不安全问题
 * ArrayList
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {

    }

    private static void notSafeSet() {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,8), Integer.MIN_VALUE);
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void notSafeList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 1 故障
     *        java.util.ConcurrentModificationException
     *
     * 2 导致原因
     *      并发争抢导致，
     *      一个正在写入， 另一个过来抢，导致数据不一致的异常，并发修改异常。
     *
     *
     * 3 解决方案
     *      (1) Vector()
     *      (2) Collections.synchronizedList(new ArrayList<>());
     *      (3) CopyOnWriteArrayList
     *      (4)
     *
     *
     * 4 优化建议
     *
     *
     *
     */

}
