package JavaBaseExercise;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        // List list = new ArrayList;
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
//
//        // add remove 队满队空时抛出异常
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//        //System.out.println(blockingQueue.add("d"));
//
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//
//        // offer poll 队满队空时返回bool值
//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.add("d"));
//
//        System.out.println(blockingQueue.peek());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());

//
//        // put take 队满队空时阻塞
//        blockingQueue.put("a");
//        blockingQueue.put("b");
//        blockingQueue.put("c");
//        System.out.println("===================================");
//        // blockingQueue.put("d");
//
//        blockingQueue.take();
//        blockingQueue.take();
//        blockingQueue.take();
//        System.out.println("===================================");
//        blockingQueue.take();


        // 设置等待时间
        System.out.println(blockingQueue.offer("a", 1, TimeUnit.SECONDS));

    }
}
