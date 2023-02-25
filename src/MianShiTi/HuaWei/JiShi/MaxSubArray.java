package MianShiTi.HuaWei.JiShi;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i-1] + nums[i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                max = Math.max(sum[j] - sum[i] + nums[i], max);
            }
        }
        return max;
    }

    /**
     * todo 学习这个经典思想，定义子问题要无后效性，就是子问题的最优解不能由当前元素之后的元素结合得出，要以当前元素为截止，得出最优解
     * 动态规划的步骤：
     * 1.推到子问题（子问题无后效性）
     * 2.推导状态转换方程，由当前子问题状态转换到下一个子问题状态的公式
     * 3.确定临界条件处理
     * 参考url： https://leetcode.cn/problems/maximum-subarray/solutions/9058/dong-tai-gui-hua-fen-zhi-fa-python-dai-ma-java-dai/
     *
     */

    public int maxSubArray1(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(dp[i-1] > 0) {
                dp[i] = nums[i] + dp[i-1];
                max = Math.max(max, dp[i]);
            } else {
                dp[i] = nums[i];
                max = Math.max(max, dp[i]);
            }

        }
        return max;
    }

}
