package AlgotithmExercise.Array;

public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == 0) {
                int j = i + 1;
                while (j < nums.length && nums[j] == 0) {
                    j++;
                }
                if(j < nums.length) {
                    nums[i] = nums[j];
                    nums[j] = 0;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 核心思想： 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     *
     * 链接：https://leetcode.cn/problems/move-zeroes/solutions/489622/yi-dong-ling-by-leetcode-solution/
     *
     */
    class Solution {
        public void moveZeroes(int[] nums) {
            int n = nums.length, left = 0, right = 0;
            while (right < n) {
                if (nums[right] != 0) {
                    swap(nums, left, right);
                    left++;
                }
                right++;
            }
        }

        public void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }



}
