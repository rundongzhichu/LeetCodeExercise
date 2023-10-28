package AlgotithmExercise.DynamicProgramming;

import java.util.Arrays;

public class MaxSatisfaction {

    /**
     *  链接：https://leetcode.cn/problems/reducing-dishes/solutions/198214/zuo-cai-shun-xu-by-leetcode-solution/
     *
     *
     */
    class Solution {
        public int maxSatisfaction(int[] satisfaction) {
            int n = satisfaction.length;
            int[][] dp = new int[n + 1][n + 1];
            Arrays.sort(satisfaction);
            int res = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i][j] = dp[i - 1][j - 1] + satisfaction[i - 1] * j;
                    if (j < i) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                    }
                    res = Math.max(res, dp[i][j]);
                }
            }
            return res;
        }
    }

}
