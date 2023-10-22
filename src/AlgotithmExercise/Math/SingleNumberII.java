package AlgotithmExercise.Math;

import java.util.HashMap;
import java.util.Map;

public class SingleNumberII {

    /**
     * https://leetcode.cn/problems/single-number-ii/solutions/746993/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetc-23t6/
     */
    class Solution {
        public int singleNumber(int[] nums) {
            int a = 0, b = 0;
            for (int num : nums) {
                b = ~a & (b ^ num);
                a = ~b & (a ^ num);
            }
            return b;
        }
    }


    class Solution1 {
        public int singleNumber(int[] nums) {
            Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
            int ans = 0;
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                int num = entry.getKey(), occ = entry.getValue();
                if (occ == 1) {
                    ans = num;
                    break;
                }
            }
            return ans;
        }
    }


}
