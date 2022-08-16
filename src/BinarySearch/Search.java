package BinarySearch;

public class Search {

    /**
        public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
     */

    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        int mid = (start + end)/2;

        if(nums[start]>target) return 0;
        if(nums[end]<target) return nums.length;

        while (start < end){
            if (nums[mid] < target) start = mid +1;
            else if(nums[mid] > target) end = mid;
            else return mid;
            mid = (start + end)/2;
        }
        return mid ;
    }
}
