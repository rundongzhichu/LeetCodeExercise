package JavaBaseExercise.designModel;

public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "\t 我再调用构造方法");
    }

    // 双端检索机制
    private static  SingletonDemo getInstance(){
        if(instance==null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());

        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                SingletonDemo.getInstance();
            }).start();
        }
    }

}
