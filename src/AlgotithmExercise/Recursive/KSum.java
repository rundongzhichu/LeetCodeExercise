package AlgotithmExercise.Recursive;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KSum {

    // todo 这道题还没有理解
    public long kSum(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b)-> b-a);
        kSum(nums, 0, -1, 1, k, pq);
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return pq.poll();
    }

    public void kSum(int[] nums, int sum, int index, int subSize, int k, PriorityQueue<Integer> pq) {
        System.out.println("sum = " + sum);
        if(subSize > nums.length || index > nums.length) return ;
        for (int i = index + 1; i < nums.length; i++) {
            pq.offer(sum + nums[i]);
            kSum(nums, sum + nums[i], i, subSize + 1, k, pq);
        }
    }

    /*
    这里采用了二分的思想， 对数组进行了排序
        链接：https://leetcode.cn/problems/find-the-k-sum-of-an-array/solutions/2668280/zhao-chu-shu-zu-de-di-k-da-he-by-leetcod-z5kq/
     */
    class Solution {
        public long kSum(int[] nums, int k) {
            int n = nums.length;
            long total = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] >= 0) {
                    total += nums[i];
                } else {
                    nums[i] = -nums[i];
                }
            }
            Arrays.sort(nums);

            long ret = 0;
            PriorityQueue<long[]> pq = new PriorityQueue<long[]>((a, b) -> Long.compare(a[0], b[0]));
            pq.offer(new long[]{nums[0], 0});
            for (int j = 2; j <= k; j++) {
                long[] arr = pq.poll();
                long t = arr[0];
                int i = (int) arr[1];
                ret = t;
                if (i == n - 1) {
                    continue;
                }
                pq.offer(new long[]{t + nums[i + 1], i + 1});
                pq.offer(new long[]{t - nums[i] + nums[i + 1], i + 1});
            }
            return total - ret;
        }
    }


}
