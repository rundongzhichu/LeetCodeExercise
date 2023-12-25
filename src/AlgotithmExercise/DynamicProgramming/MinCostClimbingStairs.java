package AlgotithmExercise.DynamicProgramming;

public class MinCostClimbingStairs {


    /**
     *
     * 这是一道很经典的动态规划
     *
     * 他的临界条件：可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     * https://leetcode.cn/problems/min-cost-climbing-stairs/?envType=daily-question&envId=2023-12-17
     *
     * 例子：
     * cost =
     * [10,15,20]
     *      顶部
     *  2阶 15cost后可以跳上去      |
     *  1阶 15cost后可以跳上去    |
     *  0阶 10cost后可以跳上去 |
     *
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0; //这个是0阶梯的位置，0阶梯永远不需要付费，所以为0， 我们可以选择从0阶梯开始向上跳一层，或者跳2层
        dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i-2] + cost[i - 2], dp[i-1] + cost[i -1]);
        }
        return dp[cost.length];
    }

}
