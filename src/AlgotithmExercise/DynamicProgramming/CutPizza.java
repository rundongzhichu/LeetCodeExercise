package AlgotithmExercise.DynamicProgramming;

public class CutPizza {

    private int mode = (int) (Math.pow(10, 9) + 7);
    private int res = 0;

    // 采用了深度游戏那搜索的思路，但是超时了
    public int ways(String[] pizza, int k) {
        ways(pizza, k-1, 0, 0, pizza[0].length());
        return res;
    }

    public void ways(String[] pizza, int k, int lstart, int cstart, int clen) {
        if(k == 0){
            for (int i = lstart; i < pizza.length; i++) {
                if(pizza[i].substring(cstart, clen).contains("A")){
                    res = (res+1)%mode;
                    break;
                }
            }
            return;
        }

        boolean hasA = false;
        for (int i = lstart; i < pizza.length; i++) {
            if(hasA || (hasA = pizza[i].substring(cstart).contains("A"))) {
                hasA = true;
                ways(pizza, k-1, i+1, cstart, clen) ;
            }
        }

        hasA = false;
        for (int i = cstart; i < clen; i++) {
            if(hasA || (hasA = hasApple(pizza, lstart, i))) {
                ways(pizza, k-1, lstart, i + 1, clen);
            }
        }
    }

    public boolean hasApple(String[] pizza, int lstart, int cstart) {
        for (int i = lstart; i < pizza.length; i++) {
            if (pizza[i].charAt(cstart) == 'A') return true;
        }
        return false;
    }
}

/**
 *
 * 解题思路：
 * 1.首先计算一个apples矩阵，计算出每个位置右下角的包括当前位置的矩阵
 * 2. 因为切割一刀后，大披萨的问题转化为求小披萨的切割方案数，我们考虑用动态规划的方式来解答。 dp[k][i][j]
 * 表示把坐标 (i,j) 右下方的披萨切割成 k 块披萨的方案数量， 如果 apples[i][j]==1，则初始化 dp[1][i][j]=1，
 * 表示当前坐标右下方的披萨符合题目条件，否则为零。
 *
 * dp[k][i][j] 表示将披萨矩阵i，j右下角的矩阵切成k份有多少种切法
 * dp[k][i][j] 如果在ij右下角矩阵中固定一刀，先切一刀满足某一块至少一个苹果， 比如横着切成(i1, j)右下角的矩阵M1和i,j 右下角矩阵M去除M1的矩阵M0
 * ，那么剩下部分的dp[k-1][i1][j]的k-1种切法当前切法下dp[k][i][j]的其中一类类切法，
 *
 * dp[k][i][j] = dp[k][i][j] + dp[k-1][i1][j] ，i1是
 * 从i开始到矩阵最后一行中的某一行，要遍历，必须满足切出来的两个矩阵至少有一个苹果，满足条件apples[i][j] > apples[i2][j]（则个条件桥面在保证切出来的两部分都有苹果）
 *
 * 同理竖着切的时候dp[k][i][j] = dp[k][i][j] + dp[k-1][i][j1]， j1是
 *  * 从j开始到矩阵最后一列中的某一列，要遍历， 条件同理
 *
 *  最后获取到dp[k][0][0]就是最终答案。
 *
 *  首先k得从2开始，因为你至少切一刀才有意义
 *
 *https://leetcode.cn/problems/number-of-ways-of-cutting-a-pizza/solutions/2387392/qie-pi-sa-de-fang-an-shu-by-leetcode-sol-7ik7/
 */
class Solution {
    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length(), mod = 1_000_000_007;
        int[][] apples = new int[m + 1][n + 1];
        int[][][] dp = new int[k + 1][m + 1][n + 1];

        // 预处理
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                apples[i][j] = apples[i][j + 1] + apples[i + 1][j] - apples[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
                dp[1][i][j] = apples[i][j] > 0 ? 1 : 0;
            }
        }

        for (int ki = 2; ki <= k; ki++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 水平方向切
                    for (int i2 = i + 1; i2 < m; i2++) {
                        if (apples[i][j] > apples[i2][j]) {
                            dp[ki][i][j] = (dp[ki][i][j] + dp[ki - 1][i2][j]) % mod;
                        }
                    }
                    // 垂直方向切
                    for (int j2 = j + 1; j2 < n; j2++) {
                        if (apples[i][j] > apples[i][j2]) {
                            dp[ki][i][j] = (dp[ki][i][j] + dp[ki - 1][i][j2]) % mod;
                        }
                    }
                }
            }
        }
        return dp[k][0][0];
    }
}

