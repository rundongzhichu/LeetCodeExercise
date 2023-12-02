package AlgotithmExercise.Array;

import java.util.Arrays;
import java.util.List;

public class CountPairs {

    public int countPairs(List<Integer> nums, int target) {
        Integer[] numsArr  = new Integer[nums.size()];
        nums.toArray(numsArr);
        Arrays.sort(numsArr);
        int res = 0;
        for (int i = 0; i < numsArr.length - 1; i++) {
            for (int j = i + 1; j < numsArr.length; j++) {
                if(numsArr[i] + numsArr[j] >= target) break;
                res ++;
            }
        }
        return res;
    }


    /**
     *
     *     链接：https://leetcode.cn/problems/count-pairs-whose-sum-is-less-than-target/
     *
     */
    class Solution {
        public int countPairs(List<Integer> nums, int target) {
            int res = 0;
            for (int i = 0; i < nums.size(); i++) {
                for (int j = i + 1; j < nums.size(); j++) {
                    if (nums.get(i) + nums.get(j) < target) {
                        res++;
                    }
                }
            }
            return res;
        }
    }

}
