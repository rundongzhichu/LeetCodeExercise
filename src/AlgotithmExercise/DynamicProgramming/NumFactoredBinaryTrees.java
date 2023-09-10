package AlgotithmExercise.DynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumFactoredBinaryTrees {


    /**
     * 这个解法目前是有问题的
     */
    private double mod = Math.pow(10, 9) + 7;

    public int numFactoredBinaryTrees(int[] arr) {
        Set products = new HashSet();
        for (int i1 : arr) {
            products.add(i1);
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += numFactoredBinaryTrees(products, arr[i]) % mod;
            res += 1;
        }
        return (int) (res % mod);
    }

    public int numFactoredBinaryTrees(Set<Integer> products, int parent) {
        int res = 0;
        if (products.contains(parent)) {
            for (int j = 2; j < parent; j++) {
                int remain = parent % j;
                int divide = parent / j;
                if (remain == 0 && products.contains(j) && products.contains(divide)) {
                    int left = (int) (numFactoredBinaryTrees(products, divide) % mod);
                    int right = (int) (numFactoredBinaryTrees(products, j) % mod);
                    if(left == 0)
                        res += 1 + right%mod;
                    else if (right==0) {
                        res += 1 + left%mod;
                    } else
                        res += 1 + left*right%mod;
                }
            }
        }
        return res;
    }


    /**
     * 官方的答案是动态规划，和我上面的思路差不多
     *
     * 1.首先对数组进行排序，保证art[i]的因数一定在0-i-1，减少搜索范围
     * 2.引入dp数组，dp[i] 表示的是以arr[i]为根节点的因子二叉树的数目
     * 3. dp[i] = dp[i](初始为1， 表示无子节点时的一种情况) + dp[left]*dp[right];  left和right分别对应的是arr[i]的两个因子
     * 4. left==right的情况，表示两边的子树相同，不必考虑交换左右子树的情况
     *
     */
    class Solution {
         public int numFactoredBinaryTrees(int[] arr) {
             Arrays.sort(arr);
             int n = arr.length;
             long[] dp = new long[n];
             long res = 0, mod = 1000000007;
             for (int i = 0; i < n; i++) {
                 dp[i] = 1;
                 for (int left = 0, right = i - 1; left <= right; left++) {
                     while (right >= left && (long) arr[left] * arr[right] > arr[i]) {
                         right--;
                     }
                     if (right >= left && (long) arr[left] * arr[right] == arr[i]) {
                         if (right != left) {
                             dp[i] = (dp[i] + dp[left] * dp[right] * 2) % mod;
                         } else {
                             dp[i] = (dp[i] + dp[left] * dp[right]) % mod;
                         }
                     }
                 }
                 res = (res + dp[i]) % mod;
             }
             return (int) res;
         }

    }

}
