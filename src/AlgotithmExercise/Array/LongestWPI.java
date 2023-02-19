package AlgotithmExercise.Array;

import java.util.HashMap;
import java.util.Map;

public class LongestWPI {

    public int longestWPI(int[] hours) {
        int goodWorkTerm = 0;
        for (int i = 0; i < hours.length; i++) {
            int tiredCount = 0;
            int noTiredCount = 0;

            for (int j = i; j < hours.length; j++) {
                if(hours[j] > 8) {
                    tiredCount ++;
                } else {
                    noTiredCount ++;
                }

                if(tiredCount > noTiredCount){
                    goodWorkTerm = Math.max(goodWorkTerm, j - i +1);
                }
            }
        }
        return goodWorkTerm;
    }

    class Solution {
        public int longestWPI(int[] hours) {
            int n = hours.length;
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            int s = 0, res = 0;
            for (int i = 0; i < n; i++) {
                s += hours[i] > 8 ? 1 : -1;
                if (s > 0) {
                    res = Math.max(res, i + 1);
                } else {
                    if (map.containsKey(s - 1)) {
                        res = Math.max(res, i - map.get(s - 1));
                    }
                }
                if (!map.containsKey(s)) {
                    map.put(s, i);
                }
            }
            return res;
        }
    }

}
