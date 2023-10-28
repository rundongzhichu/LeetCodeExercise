package AlgotithmExercise;

import java.awt.*;
import java.util.concurrent.Semaphore;

public class Main {

    private Semaphore aSem = new Semaphore(1);
    private Semaphore bSem = new Semaphore(0);
    private Semaphore cSem = new Semaphore(0);

    public static void main(String[] args) {
        new Main().test();
    }

    public void test() {
        PrintAJob printA = new PrintAJob();
        PrintBJob printB = new PrintBJob();
        PrintCJob printC = new PrintCJob();

        new Thread(printA).start();
        new Thread(printB).start();
        new Thread(printC).start();
    }

    class PrintAJob implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    aSem.acquire();
                    System.out.print('A');
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    bSem.release();
                }
            }
        }

    }

    class PrintBJob implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    bSem.acquire();
                    System.out.print('B');
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    cSem.release();
                }

            }
        }

    }

    class PrintCJob implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    cSem.acquire();
                    System.out.print('C');
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    aSem.release();
                }

            }
        }

    }

}

