package JavaBaseExercise;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    volatile int number = 0;

    public void addTo60(){
        number = 60;
    }

    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAutomicInteger(){
        atomicInteger.getAndIncrement();
    }

}

public class VolatileExercise {

    public static void main(String[] args) {

    }



    private static void noAtomicVolatile() {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addAutomicInteger();
                }
            }, "Thread-" + i).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " finally number value: "+myData.atomicInteger.get());
    }

    private static void SeeOKVolatile() {
        MyData myData = new MyData();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " common in");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();

            System.out.println(Thread.currentThread().getName() + " update number value to " + myData.number);
        }, "AAA").start();

        while (myData.number==0){

        }
        System.out.println(Thread.currentThread().getName() + " mission over");
    }

}
