package JavaBaseExercise.pool;

import java.util.concurrent.*;

public class ThreadPoolRejectPolicyDemo {

    class MyRunnable implements Runnable {

        private int id = 0;

        public MyRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + " 执行了当前线程" + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolRejectPolicyDemo main = new ThreadPoolRejectPolicyDemo();

        ExecutorService executorService = null;

//        // 调用者执行
//        executorService = new ThreadPoolExecutor(2,
//                2,
//                60L,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(2),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.CallerRunsPolicy());
//        for (int i = 0; i < 20; i++) {
//            executorService.submit(main.new MyRunnable(i));
//        }


//        // 终止策略 submit函数会抛出异常
//        executorService = new ThreadPoolExecutor(2,
//                2,
//                60L,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(2),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());
//
//            for (int i = 0; i < 20; i++) {
//                try {
//                    executorService.submit(main.new MyRunnable(i));
//                } catch (Exception e) {
//                    System.out.println("e.getMessage() = " + e.getMessage());
//                }
//            }


//        // 丢弃策略
//        executorService = new ThreadPoolExecutor(2,
//                2,
//                60L,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(2),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.DiscardPolicy());
//        for (int i = 0; i < 20; i++) {
//            executorService.submit(main.new MyRunnable(i));
//        }

        // 丢弃最老的任务
        executorService = new ThreadPoolExecutor(2,
                2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 20; i++) {
            executorService.submit(main.new MyRunnable(i));
        }

        executorService.shutdown();
        executorService.awaitTermination(60 , TimeUnit.SECONDS);
    }
    
    
}
