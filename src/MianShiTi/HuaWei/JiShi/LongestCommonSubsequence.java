package MianShiTi.HuaWei.JiShi;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int text1Len = text1.length();
        int text2Len = text2.length();

        int[] p = new int[text1.length()];

        for (int i = 0; i < text2Len; i++) {
            for (int j = i; j < text1Len; j++) {
                if (text1.charAt(j) == text2.charAt(i)) {
                    if (j > 0)
                        p[j] = Math.max(p[j - 1] + 1, p[j]);
                    else {
                        p[j] = 1;
                    }
                } else {
                    if(j==0) p[j] = 0;
                    else
                        p[j] = p[j-1];
                }
            }
        }
        return Arrays.stream(p).max().getAsInt();
    }

    // https://leetcode.cn/problems/longest-common-subsequence/solutions/696763/zui-chang-gong-gong-zi-xu-lie-by-leetcod-y7u0/
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length(), n = text2.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                char c1 = text1.charAt(i - 1);
                for (int j = 1; j <= n; j++) {
                    char c2 = text2.charAt(j - 1);
                    if (c1 == c2) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[m][n];
        }
    }

}
