package AlgotithmExercise.Thread;

import AlgotithmExercise.Main;

import java.util.concurrent.Semaphore;


/**
 *
 * 解决三个线程按顺序打印ABC
 *
 */
public class ThreeThreadPrintABC {

    private Semaphore aSem = new Semaphore(1);
    private Semaphore bSem = new Semaphore(0);
    private Semaphore cSem = new Semaphore(0);

    public static void main(String[] args) {
        new ThreeThreadPrintABC().test();
    }

    public void test() {
        ThreeThreadPrintABC.PrintAJob printA = new ThreeThreadPrintABC.PrintAJob();
        ThreeThreadPrintABC.PrintBJob printB = new ThreeThreadPrintABC.PrintBJob();
        ThreeThreadPrintABC.PrintCJob printC = new ThreeThreadPrintABC.PrintCJob();

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
