package AlgotithmExercise.DynamicProgramming;

import java.util.Arrays;
import java.util.Stack;

public class MinimumMountainRemovals {

    public int minimumMountainRemovals(int[] nums) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();


        int[] deletePrefix = new int[nums.length];
        int[] deleteSubfix = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            while (!stack1.isEmpty() && nums[stack1.peek()] >= nums[i]) {
                stack1.pop();
            }
            if(!stack1.isEmpty()) {
                deletePrefix[i] += deletePrefix[stack1.peek()] + i - stack1.peek() -1;
            } else if (i > 0) {
                deletePrefix[i] += i + 1;
            }
            stack1.push(i);
        }

        int minDelete = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0;  i--) {
            while (!stack2.isEmpty() && nums[stack2.peek()] >= nums[i]) {
                stack2.pop();
                deleteSubfix[i] ++;
            }
            if(!stack2.isEmpty()) {
                deleteSubfix[i] += deleteSubfix[stack2.peek()];
            } else if(i < nums.length - 1) {
                deleteSubfix[i] += deleteSubfix[i + 1];
            }
            minDelete = Math.min(minDelete, deletePrefix[i] + deleteSubfix[i]);
            stack2.push(i);
        }
        return minDelete;
    }


    /**
     *
     * 核心思想： 枚举以i为山形数组中心的山形数组的上升序列和下降序列的的长度记作pre[i] 和suf[i]
     * 以i为中心的山形数组的长度就是ans = pre[i] + suf[i] - 1， 那么要删除的元素就是n - ans
     *  TODO: 理解这道题的动态规划思想
     *     链接：https://leetcode.cn/problems/minimum-number-of-removals-to-make-mountain-array/
     *
     */
    class Solution {
        public int minimumMountainRemovals(int[] nums) {
            int n = nums.length;
            int[] pre = getLISArray(nums);
            int[] reversed = reverse(nums);
            int[] suf = getLISArray(reversed);
            suf = reverse(suf);

            int ans = 0;
            for (int i = 0; i < n; ++i) {
                if (pre[i] > 1 && suf[i] > 1) {
                    ans = Math.max(ans, pre[i] + suf[i] - 1);
                }
            }

            return n - ans;
        }

        public int[] getLISArray(int[] nums) {
            // 采用dp的方案记录以i为中心的山形数组升序列的长度
            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < i; ++j) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            return dp;
        }

        public int[] reverse(int[] nums) {
            int n = nums.length;
            int[] reversed = new int[n];
            for (int i = 0; i < n; i++) {
                reversed[i] = nums[n - 1 - i];
            }
            return reversed;
        }
    }

}
