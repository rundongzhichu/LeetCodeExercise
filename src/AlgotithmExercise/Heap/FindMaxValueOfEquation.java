package AlgotithmExercise.Heap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class FindMaxValueOfEquation {

    public int findMaxValueOfEquation(int[][] points, int k) {
        int maxRes = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            int start = i;
            int end = points.length - 1;
            int mid = (start + end + 1) / 2;
            while (start < end && start != mid && mid != end) {
                if (points[mid][0] - points[i][0] <= k) {
                    start = mid;
                } else {
                    end = mid;
                }
                mid = (start + end + 1) /2;
                System.out.println("start = " + start + " end = " + end + " mid = " + mid);
            }

            for (int j = i + 1; j <= end; j++) {
                int xi = points[i][0];
                int yi = points[i][1];
                int xj = points[j][0];
                int yj = points[j][1];
                if(xj - xi <= k) {
                    maxRes = Math.max(maxRes, yi + yj + xj - xi);
                }
            }
        }
        return maxRes;
    }


    /*

    关键思想： https://leetcode.cn/problems/max-value-of-equation/solutions/2351324/man-zu-bu-deng-shi-de-zui-da-zhi-by-leet-5rbj/
    把目标公式装换成： yi + yj + |xi - xj| => (yi-xi) + (xj + yj)，其中yi-xi最大，xj + yj尽量大，yi-xi《=》xi-yi最小
    这样子就转换成记录每个点的xi-yi,维护一个小顶堆，并记录x的值，然后当xj-xi>k,则抛掉不符合条件的点，poingts中的点是按照x有小到大排序的
     */
    class Solution {
        public int findMaxValueOfEquation(int[][] points, int k) {
            int res = Integer.MIN_VALUE;
            PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
            for (int[] point : points) {
                int x = point[0], y = point[1];
                while (!heap.isEmpty() && x - heap.peek()[1] > k) {
                    heap.poll();
                }
                if (!heap.isEmpty()) {
                    res = Math.max(res, x + y - heap.peek()[0]);
                }
                heap.offer(new int[]{x - y, x});
            }
            return res;
        }
    }

    /*
    这里采用双端队列的解法，在队列中每次保存y-x的最大值
     */
    class Solution1 {
        public int findMaxValueOfEquation(int[][] points, int k) {
            int res = Integer.MIN_VALUE;
            Deque<int[]> queue = new ArrayDeque<int[]>();
            for (int[] point : points) {
                int x = point[0], y = point[1];
                while (!queue.isEmpty() && x - queue.peekFirst()[1] > k) {
                    queue.pollFirst();
                }
                if (!queue.isEmpty()) {
                    res = Math.max(res, x + y + queue.peekFirst()[0]);
                }
                while (!queue.isEmpty() && y - x >= queue.peekLast()[0]) {
                    queue.pollLast();
                }
                queue.offer(new int[]{y - x, x});
            }
            return res;
        }
    }

}
