package AlgotithmExercise.Heap;

import java.util.*;

public class MinInterval {

    // 超时了
     public int[] minInterval(int[][] intervals, int[] queries) {
         Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

         for (int i = 0; i < intervals.length; i++) {
             PriorityQueue pq = map.getOrDefault(intervals[i][0], new PriorityQueue<Integer>((v1, v2)-> {
                 return v1 - v2;
             }));
             pq.offer(intervals[i][1]);
             map.put(intervals[i][0], pq);
         }

         int[] res = new int[queries.length];
         Set<Integer> keys = map.keySet();
         for (int i = 0; i < queries.length; i++) {
             int minGap = Integer.MAX_VALUE;
             for (int key :
                     keys) {
                 if(queries[i] >= key) {
                     PriorityQueue<Integer> pq = map.get(key);
                     for (int v :
                             pq) {
                         if (v >= queries[i]) {
                             minGap = Math.min(minGap, v- key+1);
                         }
                     }
                 }
             }
             res[i] = minGap == Integer.MAX_VALUE? -1: minGap;
         }
         return res;
     }

    class Solution {

        /**
         * 核心思想在于排序，对于qindex的剖析许实际上相当于在保证结果位置的情况下，对于queries从小到大进行排序
         * @param intervals
         * @param queries
         * @return
         */
        public int[] minInterval(int[][] intervals, int[] queries) {
            Integer[] qindex = new Integer[queries.length];
            for (int i = 0; i < queries.length; i++) {
                qindex[i] = i;
            }
            Arrays.sort(qindex, (i, j) -> queries[i] - queries[j]);
            Arrays.sort(intervals, (i, j) -> i[0] - j[0]);
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
            int[] res = new int[queries.length];
            Arrays.fill(res, -1);
            int i = 0;
            for (int qi : qindex) {
                // 把所有左边界符合条件的都计算到堆里面
                while (i < intervals.length && intervals[i][0] <= queries[qi]) {
                    pq.offer(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][0], intervals[i][1]});
                    i++;
                }
                // 去除右边界不符合条件的
                while (!pq.isEmpty() && pq.peek()[2] < queries[qi]) {
                    pq.poll();
                }
                if (!pq.isEmpty()) {
                    res[qi] = pq.peek()[0];
                }
            }
            return res;
        }
    }

}
