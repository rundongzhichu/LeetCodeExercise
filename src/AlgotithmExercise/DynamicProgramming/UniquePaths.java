package AlgotithmExercise.DynamicProgramming;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];

        paths[0][0] = 1;
        for (int i = 1; i < n; i++) {
            paths[0][i] = paths[0][i-1];
        }

        for (int i = 1; i < m; i++) {
            paths[i][0] = paths[i-1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                paths[i][j] = paths[i-1][j] + paths[i][j-1];
            }
        }
        return paths[m-1][n-1];
    }

    /**
     *
     *  链接：https://leetcode.cn/problems/unique-paths/solutions/5772/dong-tai-gui-hua-by-powcai-2/
     *  核心思路：二维动态规划
     *
     */
    class Solution {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            for (int i = 0; i < n; i++) dp[0][i] = 1;
            for (int i = 0; i < m; i++) dp[i][0] = 1;
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m - 1][n - 1];
        }
    }

}
