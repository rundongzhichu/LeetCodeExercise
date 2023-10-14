package AlgotithmExercise.DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class NumSquares {

    public int numSquares(int n) {

        List<Integer> numList = new ArrayList<>();
        int sqrt = (int) Math.sqrt(n);

        for (int i = 1; i*i <= n; i++) {
            numList.add((int) Math.pow(i, 2));
        }


        int[] dp = new int[n +1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int num :
                    numList) {
                if(i>=num) {
                    dp[i] = Math.min(dp[i - num] + 1, dp[i]);
//                    System.out.println("i = " + i +" dp[i] = " + dp[i]);
                }
            }
        }

        return dp[n];
    }


    /**
     * https://leetcode.cn/problems/perfect-squares/solutions/822940/wan-quan-ping-fang-shu-by-leetcode-solut-t99c/
     *
     */
    class Solution {
        public int numSquares(int n) {
            int[] f = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                int minn = Integer.MAX_VALUE;
                for (int j = 1; j * j <= i; j++) {
                    minn = Math.min(minn, f[i - j * j]);
                }
                f[i] = minn + 1;
            }
            return f[n];
        }
    }


}
