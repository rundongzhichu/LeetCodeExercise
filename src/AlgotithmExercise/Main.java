package AlgotithmExercise;


import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

public class Main {

    private Semaphore aSem = new Semaphore(1);
    private Semaphore bSem = new Semaphore(0);
    private Semaphore cSem = new Semaphore(0);

    public static void main(String[] args) {
        new Main().test();
    }

    public void test() {

    }

    public int distinctIntegers(int n) {
//        Set<Integer> set = new HashSet<>();
//        set.add(n);
//        for (int i = 0; i < 1000000000; i++) {
//            for (int j = 1; j <= n; j++) {
//                boolean flag = true;
//                for (int x :
//                        set) {
//                    if (x % j != 1) {
//                        flag = false;
//                        break;
//                    }
//                }
//                if(flag)
//                    set.add(j);
//            }
//        }
        return n-1;
    }


}


