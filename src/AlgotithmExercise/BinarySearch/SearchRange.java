package AlgotithmExercise.BinarySearch;

public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        int start= 0,end = nums.length - 1, mid = (start + end)/2;
        int[] pos = new int[]{-1, -1};

        while(start <= end) {
            // System.out.println("mid = " + mid + ", start = " + start + ", end = " + end);
            if(nums[mid] < target) {
                start = mid + 1;
            } else if(nums[mid] > target) {
                end = mid - 1;
            } else {
                end = mid - 1;
                pos[0] = mid;
            }
            mid = (start + end)/2;
        }

        start= 0;
        end = nums.length - 1;
        mid = (start + end)/2;
        while(start <= end) {
            // System.out.println("1mid = " + mid + ", start = " + start + ", end = " + end);
            if(nums[mid] < target) {
                start = mid + 1;
            } else if(nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
                pos[1] = mid;
            }
            mid = (start + end)/2;
        }
        return pos;
    }


    /**
     *
     *链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/solutions/504484/zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-su-de-di-3-4/
     *
     * 核心思路： 二分搜索
     *
     */
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int leftIdx = binarySearch(nums, target, true);
            int rightIdx = binarySearch(nums, target, false) - 1;
            if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
                return new int[]{leftIdx, rightIdx};
            }
            return new int[]{-1, -1};
        }

        public int binarySearch(int[] nums, int target, boolean lower) {
            int left = 0, right = nums.length - 1, ans = nums.length;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] > target || (lower && nums[mid] >= target)) {
                    right = mid - 1;
                    ans = mid;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }
    }

}
