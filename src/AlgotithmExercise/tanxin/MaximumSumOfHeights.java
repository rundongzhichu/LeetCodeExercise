package AlgotithmExercise.tanxin;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class MaximumSumOfHeights {

    /**
     * 这个解法超时了
     * @param maxHeights
     * @return
     */
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long maxSum = 0;
        int size = maxHeights.size();
        Integer[] maxHeigh = maxHeights.toArray(Integer[]::new);
        for (int i = 0; i < size; i++) {
            long sum = 0;
            for (int j = i - 1, preMh = maxHeigh[i]; j >= 0; j--) {
                if(maxHeigh[j] >= preMh) {
                    sum += preMh;
                } else {
                    sum += maxHeigh[j];
                    preMh = maxHeigh[j];
                }
            }

            for (int j = i + 1, preMh = maxHeigh[i]; j < size; j++) {
                if(maxHeigh[j] >= preMh) {
                    sum += preMh;
                } else {
                    sum += maxHeigh[j];
                    preMh = maxHeigh[j];
                }
            }
            maxSum = Math.max(maxSum, sum + maxHeigh[i]);
        }
        return maxSum;
    }


    /**
     * 基本思路： 贪心算法 + 单调栈
     *     链接：https://leetcode.cn/problems/beautiful-towers-ii/
     *
     */
    class Solution {
        public long maximumSumOfHeights(List<Integer> maxHeights) {
            int n = maxHeights.size();
            long res = 0;
            long[] prefix = new long[n];
            long[] suffix = new long[n];
            Deque<Integer> stack1 = new ArrayDeque<Integer>();
            Deque<Integer> stack2 = new ArrayDeque<Integer>();

            for (int i = 0; i < n; i++) {
                while (!stack1.isEmpty() && maxHeights.get(i) < maxHeights.get(stack1.peek())) {
                    stack1.pop();
                }
                if (stack1.isEmpty()) {
                    prefix[i] = (long) (i + 1) * maxHeights.get(i);
                } else {
                    prefix[i] = prefix[stack1.peek()] + (long) (i - stack1.peek()) * maxHeights.get(i);
                }
                stack1.push(i);
            }
            for (int i = n - 1; i >= 0; i--) {
                while (!stack2.isEmpty() && maxHeights.get(i) < maxHeights.get(stack2.peek())) {
                    stack2.pop();
                }
                if (stack2.isEmpty()) {
                    suffix[i] = (long) (n - i) * maxHeights.get(i);
                } else {
                    suffix[i] = suffix[stack2.peek()] + (long) (stack2.peek() - i) * maxHeights.get(i);
                }
                stack2.push(i);
                res = Math.max(res, prefix[i] + suffix[i] - maxHeights.get(i));
            }
            return res;
        }
    }

}
