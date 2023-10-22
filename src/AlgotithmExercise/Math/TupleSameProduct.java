package AlgotithmExercise.Math;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TupleSameProduct {

    /**
     * 超时了
     * @param nums
     * @return
     */
    public int tupleSameProduct(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(j == i) continue;
                int product = nums[i] * nums[j];

                for (int k = 0; k < nums.length; k++) {
                    if(k == i || k ==j) continue;
                    int divide = product/ nums[k];
                    if(product % nums[k] == 0 && nums[k] != divide && set.contains(divide)) {
                        System.out.println("product = " + product);
                        System.out.println("divide = " + divide);
                        res++;
                    }
                }
            }
        }
        return res;
    }

    /**
     *
     *   链接：https://leetcode.cn/problems/tuple-with-same-product/solutions/2470655/tong-ji-yuan-zu-by-leetcode-solution-7yyy/
     *
     */
    class Solution {
        public int tupleSameProduct(int[] nums) {
            int n = nums.length;
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for(int j = i + 1; j < n; j++) {
                    int key = nums[i] * nums[j];
                    cnt.put(key, cnt.getOrDefault(key, 0) + 1);
                }
            }
            int ans = 0;
            for (Integer v : cnt.values()) {
                // 乘以4是因为在大小为n的数对池子中选取两个数对而且还有序的情况下是n*(n-1)
                // 但是数对内部还要考虑顺序，（a,b） 这样一个数对实际上存在（a,b）和（b,a）两种，
                // 如果两个数对的搭配就应该会有2*2=4种
                ans += v * (v - 1) * 4;
            }
            return ans;
        }
    }

}
