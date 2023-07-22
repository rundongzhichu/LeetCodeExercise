package AlgotithmExercise.Heap;

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

}
