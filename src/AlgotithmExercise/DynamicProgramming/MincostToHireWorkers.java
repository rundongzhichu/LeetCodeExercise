package AlgotithmExercise.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class MincostToHireWorkers {

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int qlen = quality.length;

        TreeMap<Double, Integer> tmap =  new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return (int)(o1-o2);
            }
        });

        double res = Double.MAX_VALUE;
        for(int i = 0; i< qlen; i++){
            int num=0;
            for (int j = 0; j < qlen; j++) {
                double calWage = (double)wage[i]/((double) quality[i]/(double) quality[j]);
                if(calWage >= wage[j]){
                    num++;
                    tmap.put(calWage, tmap.getOrDefault(calWage, 0)+1);
                }
            }

            if(num >= k){
                double sum = 0;
                int count = k;
                for (Double key: tmap.keySet()) {
                    int val = tmap.get(key);
                    System.out.println("key = " + key);
                    System.out.println("count = " + count);
                    if(count>val){
                        sum += key*val;
                        count -= val;
                    }else {
                        sum += key*count;
                        break;
                    }
                }
                System.out.println("sum = " + sum);
                res = Math.min(res, sum);
            }
            tmap.clear();
        }
        return res;
    }

    class Solution {
        public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
            int n = quality.length, sumQ = 0;
            var id = IntStream.range(0, n).boxed().toArray(Integer[]::new);
            Arrays.sort(id, (i, j) -> wage[i] * quality[j] - wage[j] * quality[i]); // 按照 r 值排序
            var pq = new PriorityQueue<Integer>((a, b) -> b - a); // 最大堆
            for (var i = 0; i < k; ++i) {
                pq.offer(quality[id[i]]);
                sumQ += quality[id[i]];
            }
            var ans = sumQ * ((double) wage[id[k - 1]] / quality[id[k - 1]]); // 选 r 值最小的 k 名工人组成当前的最优解
            for (var i = k; i < n; ++i) {
                var q = quality[id[i]];
                if (q < pq.peek()) { // sumQ 可以变小，从而可能得到更优的答案
                    sumQ -= pq.poll() - q;
                    pq.offer(q);
                    ans = Math.min(ans, sumQ * ((double) wage[id[i]] / q));
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        var id = IntStream.range(0, 10).boxed().toArray(Integer[]::new);
        System.out.println("id = " + id);
        for(int i : id)
            System.out.println("i = " + i);
    }

}
