package AlgotithmExercise.tanxin;

import java.util.Deque;
import java.util.LinkedList;

public class MaxArrayValue {

    public long maxArrayValue(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        long maxSum = 0;
        for (int num :
                nums) {
            stack.push(num);
        }

        maxSum = stack.poll();
        long sum = maxSum;
        while (!stack.isEmpty()) {
            int temp = stack.poll();
            if(temp <= sum) {
                sum = temp + sum;
            } else {
                sum = temp;
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }


    /**
     * https://leetcode.cn/problems/largest-element-in-an-array-after-merge-operations/solutions/2679835/he-bing-hou-shu-zu-zhong-de-zui-da-yuan-s2b5o/
     *
     * 核心思想： 倒序+贪心
     * 先合并排在后面的元素，实现尽可能的多合并
     */
    class Solution {
        public long maxArrayValue(int[] nums) {
            long sum = nums[nums.length - 1];
            for (int i = nums.length - 2; i >= 0; i--) {
                sum = nums[i] <= sum ? nums[i] + sum : nums[i];
            }
            return sum;
        }
    }


}
