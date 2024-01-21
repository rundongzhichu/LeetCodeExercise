package AlgotithmExercise;


import AlgotithmExercise.DoublePointer.ListNode;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Main {

    private Semaphore aSem = new Semaphore(1);
    private Semaphore bSem = new Semaphore(0);
    private Semaphore cSem = new Semaphore(0);

    public static void main(String[] args) {
        new Main().test();
    }

    public void test() {

    }

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        int slen = s.length();
        int alen = a.length();
        int blen = b.length();

        List<Integer> beautiIndexes = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i <= slen - alen; i++) {
            String istr = s.substring(i, i + alen);
            for (int j = 0; j <= slen - blen; j++) {
                String jstr = s.substring(j, j + blen);
                if(istr.equals(a) && jstr.equals(b) && Math.abs(i-j) <= k ) {
                    set.add(i);
                }
            }
        }
        for (Integer num :
                set) {
            beautiIndexes.add(num);
        }
        return beautiIndexes;
    }

}


