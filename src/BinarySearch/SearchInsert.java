package BinarySearch;

public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        int mid = (start + end)/2;

        while (nums[mid] != target){
            if (nums[mid] < target) start = mid;
            else if(nums[mid] > target) end = mid;
            else return mid;
            mid = (start + end)/2;
        }
        return mid+1;
    }
}
