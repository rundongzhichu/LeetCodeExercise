package AlgotithmExercise;


import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class Main {

    private Semaphore aSem = new Semaphore(1);
    private Semaphore bSem = new Semaphore(0);
    private Semaphore cSem = new Semaphore(0);

    public static void main(String[] args) {
        new Main().test();
    }

    public void test() {
        System.out.println("aSem = " + aSem);

    }




}


