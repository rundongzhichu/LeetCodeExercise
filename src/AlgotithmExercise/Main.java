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

    public int subarraySum(int[] nums, int k) {
        int[] numsSum = new int[nums.length + 1];
        numsSum[1] = nums[0];
        for (int i = 2; i < numsSum.length; i++) {
            numsSum[i] = numsSum[i -1] + nums[i - 1];
        }

        int count = 0;
        for (int i = 0; i < numsSum.length - 1; i++) {
            for (int j = i+ 1; j < numsSum.length; j++) {
                if(numsSum[j] - numsSum[i] == k) {
                      count++;
                }
            }
        }
        return count;
    }



}


