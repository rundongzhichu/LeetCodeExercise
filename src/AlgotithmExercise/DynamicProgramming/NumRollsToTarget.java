package AlgotithmExercise.DynamicProgramming;

public class NumRollsToTarget {

    private int mod = 1000000007;

    public int numRollsToTarget(int n, int k, int target) {
        if(n==1) {
            if(k>=target) return 1;
            return 0;
        }
        int a = factorial(target - 1, target - n + 1);
        int b = factorial(n-1, 1);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        return a/b%mod;
    }

    private int factorial(int n, int k) {
        if(n==k) return n;
        System.out.println("k = " + k);
        return (n% mod*factorial(n-1, k)% mod)% mod;
    }

    /**
     *
     * https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum/solutions/2490436/zhi-tou-zi-deng-yu-mu-biao-he-de-fang-fa-eewv/
     *
     */
    class Solution {
        static final int MOD = 1000000007;

        public int numRollsToTarget(int n, int k, int target) {
            int[][] f = new int[n + 1][target + 1];
            f[0][0] = 1;
            for (int i = 1; i <= n; ++i) {
                for (int j = 0; j <= target; ++j) {
                    for (int x = 1; x <= k; ++x) {
                        if (j - x >= 0) {
                            f[i][j] = (f[i][j] + f[i - 1][j - x]) % MOD;
                        }
                    }
                }
            }
            return f[n][target];
        }
    }

}
