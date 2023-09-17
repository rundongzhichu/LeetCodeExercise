package AlgotithmExercise.tanxin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ScheduleCourse {

    private int cnt = 0;

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        scheduleCourse(courses, 0, 0, 0);
        return cnt;
    }

    public void scheduleCourse(int[][] courses, int index, int count, int consumeDays) {
        if(index >= courses.length) {
            cnt = Math.max(cnt, count);
            return ;
        }

        int duration = courses[index][0];
        int lastDay = courses[index][1];
        if(duration <= lastDay)
            if(consumeDays <= lastDay - duration) {
                scheduleCourse(courses, index +1, count + 1, consumeDays + duration);
            } else {
                scheduleCourse(courses, index +1, 1, duration);
            }
        scheduleCourse(courses, index +1, count, consumeDays);
    }

}

/**
 * https://leetcode.cn/problems/course-schedule-iii/solutions/1155605/ke-cheng-biao-iii-by-leetcode-solution-yoyz/
 * todo 优先队列+贪心算法
 * 记录一个优先队列，优先队列记录的是i-1门可能里面所有满足条件的k门课程的课程时间t
 * 这个优先队列从大到小排序，
 * 是为了当课程i不满足条件时，去替换掉之前满足条件的所有ti里面的最大值
 * 这个最大值要比ti大，才能去替换，不然，达不到最优替换
 *
 */
class Solution1 {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
        // 优先队列中所有课程的总时间
        int total = 0;

        for (int[] course : courses) {
            int ti = course[0], di = course[1];
            if (total + ti <= di) {
                total += ti;
                q.offer(ti);
            } else if (!q.isEmpty() && q.peek() > ti) {
                total -= q.poll() - ti;
                q.offer(ti);
            }
        }

        return q.size();
    }
}
