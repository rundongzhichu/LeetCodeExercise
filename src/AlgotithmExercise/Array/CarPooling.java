package AlgotithmExercise.Array;

import java.util.HashMap;
import java.util.Map;

public class CarPooling {

    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> onRecord = new HashMap<>();
        Map<Integer, Integer> offRecord = new HashMap<>();
        for(int[] trip: trips) {
            onRecord.put(trip[1], onRecord.getOrDefault(trip[1], 0) + trip[0]);
            offRecord.put(trip[2], offRecord.getOrDefault(trip[2], 0) + trip[0]);
        }

        int remainCapacity = capacity;
        for (int i = 0; i <= 1000; i++) {
            if(offRecord.containsKey(i)) {
                remainCapacity += offRecord.get(i);
            }
            if(onRecord.containsKey(i)) {
                int numPassenger = onRecord.get(i);
                if(numPassenger <= remainCapacity) {
                    remainCapacity -= numPassenger;
                } else return false;
            }
        }
        return true;
    }

    // todo 理解这种差分数组的思路

    /**
     *
     * 链接：https://leetcode.cn/problems/car-pooling/
     *
     */
    class Solution {
        public boolean carPooling(int[][] trips, int capacity) {
            int toMax = 0;
            for (int[] trip : trips) {
                toMax = Math.max(toMax, trip[2]);
            }

            int[] diff = new int[toMax + 1];
            for (int[] trip : trips) {
                diff[trip[1]] += trip[0];
                diff[trip[2]] -= trip[0];
            }

            int count = 0;
            for (int i = 0; i <= toMax; ++i) {
                count += diff[i];
                if (count > capacity) {
                    return false;
                }
            }
            return true;
        }
    }

}
