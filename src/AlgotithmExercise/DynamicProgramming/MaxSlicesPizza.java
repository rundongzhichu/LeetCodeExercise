package AlgotithmExercise.DynamicProgramming;

import java.util.Arrays;

public class MaxSlicesPizza {

    private int maxSum = 0;
    public int maxSizeSlices(int[] slices) {
        maxSizeSlices(slices, 0, 0, slices.length);
        return maxSum;
    }

    public void maxSizeSlices(int[] slices, int sum, int count, int len) {
        if(count == len) {
            maxSum = Math.max(sum, maxSum);
            return ;
        }
        for (int i = 0; i < len; i++) {
            if(slices[i] != 0) {
                int a = slices[i];
                slices[i] = 0;
                int k1=i;
                while (slices[k1] == 0) {
                    k1 = (k1-1 +len)%len;
                }
                int b = slices[k1];
                slices[k1] =0;
                int k2 = i;
                while (slices[k2] ==0) {
                    k2 = (k2+1)%len;
                }
                int c = slices[k2];
                slices[k2] = 0;
//               System.out.println("a = " + a);
                maxSizeSlices(slices, sum +a, count +3, len);
                slices[i] = a;
                slices[k1] = b;
                slices[k2] = c;
            }
        }
    }


    /**
     * 最优的解法还是动态规划
     *
     * 核心思想： 问题可以转化成就是3n个数中选择n个不相邻数. 而且我们还要去除换装队列的考虑，只需要考虑第一个选择最后一个不选择，
     * 然后最后一个选择第一个不选择两种情况，两者合并的结果就是环装队列的最终结果
     *
     * dp[i][j] 相当于从i长度的序列中选择j个不相邻数的最大和
     * (1) 当i<2或j=0时，
     * dp[0][0] = 0;
     * dp[0][1] = slices[0];
     * dp[1][0] = 0;
     * dp[1][1] = Math.max(slices[0], slices[1]);
     * （2）当i>=2, j>0时
     * 如果我们选择了第 i 个数，那么第 i−1个数不能被选择，相当于需要在前 i−2个数中选择 j−1 个, 即 dp[i][j]=dp[i−2][j−1]+slices[i]
     *如果我们没有选择第 i 个数，那么需要在前 i−1 个数中选择 j 个，即 dp[i][j]=dp[i−1][j]。
     * 状态转移方程： dp[i][j] = Math.max(dp[i - 1][j], dp[i - 2][j - 1] + slices[i]);
     *

     * https://leetcode.cn/problems/pizza-with-3n-slices/solutions/177086/3n-kuai-pi-sa-by-leetcode-solution/
     *
     * 环状序列相较于普通序列，相当于添加了一个限制：普通序列中的第一个和最后一个数不能同时选。这样一来，
     * 我们只需要对普通序列进行两遍动态即可得到答案，第一遍动态规划中我们删去普通序列中的第一个数，表示我们不会选第一个数；
     * 第二遍动态规划中我们删去普通序列中的最后一个数，表示我们不会选最后一个数。将这两遍动态规划得到的结果去较大值，即为在环状序列上的答案
     *
     *
     */
    class Solution {
        public int maxSizeSlices(int[] slices) {
            int[] v1 = new int[slices.length - 1];
            int[] v2 = new int[slices.length - 1];
            //不选择第一个的情况
            System.arraycopy(slices, 1, v1, 0, slices.length - 1);
            // 选择第一个的情况
            System.arraycopy(slices, 0, v2, 0, slices.length - 1);
            int ans1 = calculate(v1);
            int ans2 = calculate(v2);
            return Math.max(ans1, ans2);
        }

        public int calculate(int[] slices) {
            int N = slices.length, n = (N + 1) / 3;
            int[][] dp = new int[N][n + 1];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], Integer.MIN_VALUE);
            }
            // 当i<2或j=0时
            dp[0][0] = 0;
            dp[0][1] = slices[0];
            dp[1][0] = 0;
            dp[1][1] = Math.max(slices[0], slices[1]);
            for (int i = 2; i < N; i++) {
                dp[i][0] = 0;
                for (int j = 1; j <= n; j++) {
                    // 如果我们选择了第 i 个数，那么第 i−1个数不能被选择，相当于需要在前 i−2个数中选择 j−1 个, 即 dp[i][j]=dp[i−2][j−1]+slices[i]
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 2][j - 1] + slices[i]);
                }
            }
            return dp[N - 1][n];
        }
    }

}


