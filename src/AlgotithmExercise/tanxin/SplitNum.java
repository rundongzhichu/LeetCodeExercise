package AlgotithmExercise.tanxin;

import java.util.PriorityQueue;

public class SplitNum {

    // tanxin
    public int splitNum(int num) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int divide = num;
        int remain = 0;

        while (divide % 10 != 0 || divide /10 != 0) {
            pq.offer(divide%10);
            divide /= 10;
        }

        int num1 = 0;
        int num2 = 0;

        while (!pq.isEmpty()) {
            num1 = num1*10 + pq.poll();
            if(!pq.isEmpty())
                num2 = num2*10 + pq.poll();
        }

        return num1 + num2;
    }

}
