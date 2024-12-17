package AlgotithmExercise.SlideWindow;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MaxSlidingWindow {


    /**
     * 链接：https://leetcode.cn/problems/sliding-window-maximum/solutions/543426/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
     * 滑动窗口最大值
     * 核心思想：单调队列
     * 由于队列中下标对应的元素是严格单调递减的，因此此时队首下标对应的元素就是滑动窗口中的最大值。
     * 但与方法一中相同的是，此时的最大值可能在滑动窗口左边界的左侧，并且随着窗口向右移动，它永远不可能出现在滑动窗口中了。
     * 因此我们还需要不断从队首弹出元素，直到队首元素在窗口中为止。
     *
     *
     */
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            Deque<Integer> deque = new LinkedList<Integer>();
            // 维护一个单调递减的队列元素
            for (int i = 0; i < k; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
            }

            int[] ans = new int[n - k + 1];
            ans[0] = nums[deque.peekFirst()];
            for (int i = k; i < n; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
                while (deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
            return ans;
        }
    }


    /**
     *
     * 核心思想：优先队列维护数组的值和idx的arr，根据数值排序，然后相同数值的根据idx升序排序。
     *
     */
    class Solution1 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                public int compare(int[] pair1, int[] pair2) {
                    return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
                }
            });
            for (int i = 0; i < k; ++i) {
                pq.offer(new int[]{nums[i], i});
            }
            int[] ans = new int[n - k + 1];
            ans[0] = pq.peek()[0];
            for (int i = k; i < n; ++i) {
                pq.offer(new int[]{nums[i], i});
                while (pq.peek()[1] <= i - k) {
                    pq.poll();
                }
                ans[i - k + 1] = pq.peek()[0];
            }
            return ans;
        }
    }

}
