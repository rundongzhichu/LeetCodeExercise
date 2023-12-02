package AlgotithmExercise.Array;

public class MinDeletion {

    public int minDeletion(int[] nums) {
        int len = nums.length;
        int deleteCount = 0;
        int actualIndex = -1;
        for (int i = 0; i < len; i++) {
            actualIndex ++;
            int j = actualIndex%2;
            if(j == 0) {
                if(i+1 < len) {
                    int k = i+1;
                    while (k<len && nums[i] == nums[k]) {
                        k++;
                        deleteCount++;
                    }
                    i=k;
                    if(i<len)
                        actualIndex++;
                }
            }
        }
        if((actualIndex +1) % 2 != 0) deleteCount++;
        return deleteCount;
    }

    /**
     *
     *  https://leetcode.cn/problems/minimum-deletions-to-make-array-beautiful/
     *
     */
    class Solution {
        public int minDeletion(int[] nums) {
            int n = nums.length;
            int ans = 0;
            // 这个变量用于记录删除一些元素后的完美数组实际元素的当前坐标是不是偶数
            boolean check = true;
            for (int i = 0; i + 1 < n; ++i) {
                // 表示当前的i如果对应到完美数组的偶数位置元素，而且nums[i] == nums[i + 1]，这个时候把nums[i]从数组中删除
                // 然后进一步把i+1位置的元素作为完美数组偶数位置的元素，进一步检查
                // 当检查到符合条件的一个数对时，那么接下来i+1位置对应的元素就是一个奇数位置元素，需要跳过
                if (nums[i] == nums[i + 1] && check) {
                    ++ans;
                } else {
                    check = !check;
                }
            }
            if ((n - ans) % 2 != 0) {
                ++ans;
            }
            return ans;
        }
    }

}
