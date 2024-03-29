package AlgotithmExercise.DynamicProgramming;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > dp[i-1] + nums[i]) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i-1] + nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     *
     * 链接：https://leetcode.cn/problems/maximum-subarray/description/
     *
     */
    public class Solution {

        public int maxSubArray(int[] nums) {
            int len = nums.length;
            // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
            int[] dp = new int[len];
            dp[0] = nums[0];

            for (int i = 1; i < len; i++) {
                if (dp[i - 1] > 0) {
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    dp[i] = nums[i];
                }
            }

            // 也可以在上面遍历的同时求出 res 的最大值，这里我们为了语义清晰分开写，大家可以自行选择
            int res = dp[0];
            for (int i = 1; i < len; i++) {
                res = Math.max(res, dp[i]);
            }
            return res;
        }
    }

}
