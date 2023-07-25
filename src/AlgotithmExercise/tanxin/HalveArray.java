package AlgotithmExercise.tanxin;

import java.util.PriorityQueue;

public class HalveArray {

    public int halveArray(int[] nums) {
        PriorityQueue<Double> pq = new PriorityQueue<>((a, b)->{
            return b.compareTo(a);
        });

        double sum = 0;
        double half = 0;
        for (int i = 0; i < nums.length; i++) {
            pq.offer((double) nums[i]);
            sum += nums[i];
        }

        half = sum /2;
        int count=0;
        while (sum > half) {
            double temp = pq.poll()/2;
            System.out.println("temp = " + temp);
            pq.offer(temp);
            sum -= temp;
            count++;
            System.out.println("sum = " + sum);
        }

        return count;
    }

}
