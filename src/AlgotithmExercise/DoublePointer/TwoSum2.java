package AlgotithmExercise.DoublePointer;

import java.util.Arrays;
import java.util.Comparator;

public class TwoSum2 {

    /**
     *
     * 题解： https://leetcode.cn/problems/two-sum/solutions/434597/liang-shu-zhi-he-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked
     * 关键思想：双指针排序
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[][] numsPair = new int[nums.length][2];

        for (int i = 0; i < numsPair.length; i++) {
            numsPair[i][0] = nums[i];
            numsPair[i][1] = i;
        }
        Arrays.sort(numsPair, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int low = 0, high = nums.length - 1;
        int[] res = new int[2];
        while (low < high) {
            if (numsPair[low][0] + numsPair[high][0] > target) {
                high--;
            } else if (numsPair[low][0] + numsPair[high][0] < target) {
                low++;
            } else {
                res[0] = numsPair[low][1];
                res[1] = numsPair[high][1];
                return res;
            }
        }
        return null;
    }

}
