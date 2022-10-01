package AlgotithmExercise.Array;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class FrequencySort {
    public int[] frequencySort(int[] nums) {

        TreeMap<Integer, PriorityQueue<Integer>> tmap = new TreeMap<>();

        for (int i = nums.length - 1; i >= 0 ; ) {
            int val = nums[i];
            int count = 1;
            for(int j=i-1; j>=0 && nums[i] == nums[j]; j--){
                count++;
            }

            if (tmap.containsKey(count)) {
                tmap.get(count).offer(val);
            }else{
                PriorityQueue pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                });
                pq.offer(val);
                tmap.put(count, pq);
            }
            i-=count;
        }

        int index=0;
        for(int key: tmap.keySet()){
            int count = key;
            for(int val:tmap.get(key)){
                while (key>0){
                    nums[index] = val;
                    index++;
                    count--;
                    System.out.println("val = " + val);
                }
            }
        }
        return nums;
    }
}
