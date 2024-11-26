package AlgotithmExercise.Array;

public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        int[] numsSum = new int[nums.length + 1];
        numsSum[1] = nums[0];
        for (int i = 2; i < numsSum.length; i++) {
            numsSum[i] = numsSum[i -1] + nums[i - 1];
        }

        int count = 0;
        for (int i = 0; i < numsSum.length - 1; i++) {
            for (int j = i+ 1; j < numsSum.length; j++) {
                if(numsSum[j] - numsSum[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *
     * 链接：https://leetcode.cn/problems/subarray-sum-equals-k/solutions/238572/he-wei-kde-zi-shu-zu-by-leetcode-solution/
     *
     * 核心思路： 用hash表保留数组的前缀核，只要能找到当前前和 preSum - k以后得前缀和在hash表中，那么就能提高效率
     *
     */
    public class Solution {
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            for (int start = 0; start < nums.length; ++start) {
                int sum = 0;
                for (int end = start; end >= 0; --end) {
                    sum += nums[end];
                    if (sum == k) {
                        count++;
                    }
                }
            }
            return count;
        }
    }

}
