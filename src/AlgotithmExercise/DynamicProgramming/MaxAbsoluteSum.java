package AlgotithmExercise.DynamicProgramming;

public class MaxAbsoluteSum {

    public int maxAbsoluteSum(int[] nums) {
        int leftSum = 0;
        int maxSum = 0;

        int[] numsSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsSum[i] = nums[i];
            if(Math.abs(nums[i] + numsSum[i-1]) > Math.abs(nums[i])) {
                numsSum[i] = nums[i] + numsSum[i-1];
            } else {
                numsSum[i] = nums[i];
            }
            maxSum = Math.max(maxSum, numsSum[i]);

            int temp = nums[i] + leftSum;
            if(nums[i] >= 0) {

            } else {

            }
            leftSum += nums[i];
        }
        return 0;
    }

    /*
    https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/solutions/2372374/ren-yi-zi-shu-zu-he-de-jue-dui-zhi-de-zu-qerr/
     */
    class Solution {
        public int maxAbsoluteSum(int[] nums) {
            int positiveMax = 0, negativeMin = 0;
            int positiveSum = 0, negativeSum = 0;
            for (int num : nums) {
                positiveSum += num;
                positiveMax = Math.max(positiveMax, positiveSum);
                positiveSum = Math.max(0, positiveSum);
                negativeSum += num;
                negativeMin = Math.min(negativeMin, negativeSum);
                negativeSum = Math.min(0, negativeSum);
            }
            return Math.max(positiveMax, -negativeMin);
        }
    }

}
