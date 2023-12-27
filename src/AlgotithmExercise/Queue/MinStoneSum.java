package AlgotithmExercise.Queue;

import java.util.PriorityQueue;

public class MinStoneSum {

    /**
     * https://leetcode.cn/problems/remove-stones-to-minimize-the-total/description/?envType=daily-question&envId=2023-12-23
     * @param piles
     * @param k
     * @return
     */
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> (b - a));
        for (int pile : piles) {
            pq.offer(pile);
        }
        for (int i = 0; i < k; i++) {
            int pile = pq.poll();
            pile -= pile / 2;
            pq.offer(pile);
        }
        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }

}
