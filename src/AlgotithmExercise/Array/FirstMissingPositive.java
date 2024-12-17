package AlgotithmExercise.Array;

import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int maxVal = -1;
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            maxVal = Math.max(maxVal, nums[i]);
        }

        int res = 1;
        for (int i = res; i < maxVal; i++) {
            if(set.contains(res)){
                res++;
            } else {
                return res;
            }
        }
        return res;
    }

    /**
     *
     *    链接：https://leetcode.cn/problems/first-missing-positive/solutions/304743/que-shi-de-di-yi-ge-zheng-shu-by-leetcode-solution/
     *     核心思想： O(N)复杂度的解法， TODO：需要进一步理解
     */
    class Solution {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= 0) {
                    nums[i] = n + 1;
                }
            }
            for (int i = 0; i < n; ++i) {
                int num = Math.abs(nums[i]);
                if (num <= n) {
                    nums[num - 1] = -Math.abs(nums[num - 1]);
                }
            }
            for (int i = 0; i < n; ++i) {
                if (nums[i] > 0) {
                    return i + 1;
                }
            }
            return n + 1;
        }
    }

}
