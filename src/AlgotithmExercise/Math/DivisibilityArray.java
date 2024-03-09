package AlgotithmExercise.Math;

import java.math.BigDecimal;

public class DivisibilityArray {

    public int[] divisibilityArray(String word, int m) {
        char[] wordArr = word.toCharArray();
        int[] res = new int[wordArr.length];
        BigDecimal preRemain = BigDecimal.ZERO;
        for (int i = 0; i < wordArr.length; i++) {
            BigDecimal remain = BigDecimal.valueOf(Integer.valueOf("" +wordArr[i])).add(preRemain.multiply(BigDecimal.TEN)).remainder(BigDecimal.valueOf(m));
            if(remain.compareTo(BigDecimal.ZERO) == 0) {
                res[i] = 1;
                preRemain = BigDecimal.ZERO;

            } else {
                res[i] = 0;
                preRemain = remain;
            }
        }
        return res;
    }


    /**

     https://leetcode.cn/problems/find-the-divisibility-array-of-a-string/solutions/2668264/zhao-chu-zi-fu-chuan-de-ke-zheng-chu-shu-pv8v/

     主要是在于long是八字节，8*8=64 位的长整型
     可以包含更多的数据
     */
    class Solution {
        public int[] divisibilityArray(String word, int m) {
            int[] res = new int[word.length()];
            long cur = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                cur = (cur * 10 + (c - '0')) % m;
                res[i] = (cur == 0) ? 1 : 0;
            }
            return res;
        }
    }


}
