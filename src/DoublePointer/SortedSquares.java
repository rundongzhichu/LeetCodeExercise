package DoublePointer;

public class SortedSquares {
    // 也可以使用归并排序，双指针的解法关键是创建了一个ans数组用于保存排序结果
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;

        int[] ans = new int[n];

        for (int left = 0, right = n - 1, k = n - 1; left <= right; k--)
        {
            int a = nums[left] * nums[left], b = nums[right] * nums[right];
            if (a > b)
            {
                ans[k] = a;
                left++;
            }
            else
            {
                ans[k] = b;
                right--;
            }
        }

        return ans;
    }
}
