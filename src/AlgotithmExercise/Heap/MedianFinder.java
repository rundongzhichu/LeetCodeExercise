package AlgotithmExercise.Heap;

import java.util.PriorityQueue;

public class MedianFinder {

    /**
     * 我这个算法超时了
     *
     */
    private PriorityQueue<Integer> pq = null;
    private PriorityQueue<Integer> pq1 = null;

    public MedianFinder() {
        pq = new PriorityQueue<>();
        pq1 = new PriorityQueue<>();
    }

    public void addNum(int num) {
        PriorityQueue<Integer> tpq = pq.isEmpty() ? pq1 : pq;
        if(pq.isEmpty() && pq1.isEmpty()) {
            tpq = pq;
        }
        tpq.add(num);
    }

    public double findMedian() {
        PriorityQueue<Integer> tpq = pq.isEmpty() ? pq1 : pq;
        PriorityQueue<Integer> epq = pq.isEmpty() ? pq : pq1;
        int size = tpq.size();

        double sum = 0;
        if (size % 2 == 0) {
            for (int i = 0; i < size; i++) {
                if (i == size / 2 - 1 || i == size / 2) {
                    sum += tpq.peek();
                }
                epq.add(tpq.poll());
            }
            return sum/2;
        } else {
            for (int i = 0; i < size; i++) {
                if (i == size / 2) {
                    sum += tpq.peek();
                }
                epq.add(tpq.poll());
            }
        }
        return sum;
    }

    /**
     *
     *   链接：https://leetcode.cn/problems/find-median-from-data-stream/solutions/961062/shu-ju-liu-de-zhong-wei-shu-by-leetcode-ktkst/
     *   核心思想： 将数字序列拆分到两个堆里面，一个大顶堆，一个小顶堆
     *
     *
     */
    class MedianFinder1 {
        PriorityQueue<Integer> queMin;
        PriorityQueue<Integer> queMax;

        public MedianFinder1() {
            queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
            queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
        }

        public void addNum(int num) {
            // 先放小顶堆
            if (queMin.isEmpty() || num <= queMin.peek()) {
                queMin.offer(num);
                // 这里的判断用来均衡大顶堆和小顶堆的数据
                if (queMax.size() + 1 < queMin.size()) {
                    queMax.offer(queMin.poll());
                }
            } else {
                queMax.offer(num);
                // 这里的判断用来均衡大顶堆和小顶堆的数据
                if (queMax.size() > queMin.size()) {
                    queMin.offer(queMax.poll());
                }
            }
        }

        public double findMedian() {
            if (queMin.size() > queMax.size()) {
                return queMin.peek();
            }
            return (queMin.peek() + queMax.peek()) / 2.0;
        }
    }

}
