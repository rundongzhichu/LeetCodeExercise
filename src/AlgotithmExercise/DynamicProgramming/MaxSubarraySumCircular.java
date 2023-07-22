package AlgotithmExercise.DynamicProgramming;

public class MaxSubarraySumCircular {



    /*
        子数组的定义：一个或连续多个数组中的元素组成一个子数组(子数组最少包含一个元素)
        子序列的定义：子序列就是在原来序列中找出一部分组成的序列（子序列不一定连续）

        双循环会超时
        双指针思路: 可以通过，耗时太长
     */
    public int maxSubarraySumCircular(int[] nums) {
        /*
        int nlen = nums.length;
        int maxSum = Integer.MIN_VALUE;
        if (nlen == 1) return nums[0];
        for (int start = 0; start < nlen; start++) {
            int sum = nums[start];
            for (int end = (start + 1)%nlen; start != end && end < nlen; end = (++end)%nlen) {
                maxSum = Math.max(sum, maxSum);
                sum += nums[end];
            }
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
         */

        int nlen = nums.length;
        int allSum = 0;
        int maxVal = Integer.MIN_VALUE;
        boolean allNeg = true;

        for (int i = 0; i < nlen; i++) {
            allSum+=nums[i];
            allNeg = allNeg && nums[i]<0;
            maxVal = Math.max(maxVal, nums[i]);
        }
        if (allNeg) return maxVal;

        int maxSum = allSum;
        for (int i = 0; i < nlen; i++) {
            if(nums[i] < 0 ) {
                int negativeSum = nums[i];
                for (int end=(i + 1)%nlen; i != end && negativeSum < 0 ; end=++end%nlen) {
                    maxSum = Math.max(allSum - negativeSum, maxSum);
                    negativeSum += nums[end];
                }
                while (i + 1 < nlen && nums[i +1] < 0) {
                    i ++;
                }
            }
        }
        return maxSum;
    }

    /*

    动态规划思路
     */
    class Solution {
        public int maxSubarraySumCircular(int[] nums) {
            int n = nums.length;
            int[] leftMax = new int[n];
            // 对坐标为 0 处的元素单独处理，避免考虑子数组为空的情况
            leftMax[0] = nums[0];
            // 记录前缀和
            int leftSum = nums[0];
            // 记录最大前缀和
            int pre = nums[0];
            // 考虑的是循环列表中最大子序列和在中间的情况
            int res = nums[0];
            for (int i = 1; i < n; i++) {
                pre = Math.max(pre + nums[i], nums[i]);
                res = Math.max(res, pre);
                leftSum += nums[i];
                leftMax[i] = Math.max(leftMax[i - 1], leftSum);
            }

            // 从右到左枚举后缀，固定后缀，选择最大前缀
            int rightSum = 0;
            for (int i = n - 1; i > 0; i--) {
                rightSum += nums[i];
                res = Math.max(res, rightSum + leftMax[i - 1]);
            }
            return res;
        }
    }


    /*
    https://leetcode.cn/problems/maximum-sum-circular-subarray/solutions/2350660/huan-xing-zi-shu-zu-de-zui-da-he-by-leet-elou/
     */
    class Solution1 {
        public int maxSubarraySumCircular(int[] nums) {
            int n = nums.length;
            int preMax = nums[0], maxRes = nums[0];
            int preMin = nums[0], minRes = nums[0];
            int sum = nums[0];
            for (int i = 1; i < n; i++) {
                preMax = Math.max(preMax + nums[i], nums[i]);
                maxRes = Math.max(maxRes, preMax);
                preMin = Math.min(preMin + nums[i], nums[i]);
                minRes = Math.min(minRes, preMin);
                sum += nums[i];
            }
            if (maxRes < 0) {
                return maxRes;
            } else {
                return Math.max(maxRes, sum - minRes);
            }
        }
    }

}
