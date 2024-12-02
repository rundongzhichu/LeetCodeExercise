package JavaBaseExercise.thread;

import java.util.concurrent.*;

public class WriteThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        try{
            for (int i = 0; i < 10; i++) {
                final int tmpI = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "号窗口， 服务顾客" + tmpI);
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
