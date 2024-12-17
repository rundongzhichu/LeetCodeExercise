package AlgotithmExercise.Array;


import java.util.*;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(intervals[0][0]);
        queue.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            int[] intern = intervals[i];
            if(intern[0] > queue.peekLast()) {
                queue.offer(intern[0]);
                queue.offer(intern[1]);
            } else {
                if(intern[1] > queue.peekLast()) {
                    queue.pollLast();
                    queue.offer(intern[1]);
                }
            }
        }

        int[][] res = new int[queue.size()/2][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = new int[]{queue.poll(), queue.poll()};
        }
        return res;
    }


    /**
     *
     * 链接：https://leetcode.cn/problems/merge-intervals/solutions/203562/he-bing-qu-jian-by-leetcode-solution/
     *
     * 核心思路： 先把列表里面的区间进行排序，然后依次合并
     *
     */
    class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals.length == 0) {
                return new int[0][2];
            }
            Arrays.sort(intervals, new Comparator<int[]>() {
                public int compare(int[] interval1, int[] interval2) {
                    return interval1[0] - interval2[0];
                }
            });
            List<int[]> merged = new ArrayList<int[]>();
            for (int i = 0; i < intervals.length; ++i) {
                int L = intervals[i][0], R = intervals[i][1];
                if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                    merged.add(new int[]{L, R});
                } else {
                    merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
                }
            }
            return merged.toArray(new int[merged.size()][]);
        }
    }


}
