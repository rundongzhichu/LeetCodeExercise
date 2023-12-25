package AlgotithmExercise.BruteForceSolution;

import java.util.HashMap;
import java.util.Map;

public class NextBeautifulNumber {

    public int nextBeautifulNumber(int n) {
        for (int i = n +1 ; ; i++) {
            if (isBeautifulNumber(i)) return i;
            System.out.println("i = " + i);
        }
    }

    public boolean isBeautifulNumber(int n) {
        String nStr = String.valueOf(n);
        int nlen = nStr.length();
        Map<Integer, Integer> digitMap = new HashMap<>();
        for (int i = 0; i < nlen; i++) {
            int d = Integer.valueOf(String.valueOf(nStr.charAt(i)));
            digitMap.put(d, digitMap.getOrDefault(d, 0) + 1);
        }
        boolean res = true;
        for (Map.Entry<Integer, Integer> entry :
                digitMap.entrySet()) {
            res &= entry.getKey().equals(entry.getValue());
        }
        return res;
    }


    /**
     *
     * https://leetcode.cn/problems/next-greater-numerically-balanced-number/description/
     *
     *
     */
    class Solution {
        public int nextBeautifulNumber(int n) {
            for (int i = n + 1; i <= 1224444; ++i) {
                if (isBalance(i)) {
                    return i;
                }
            }
            return -1;
        }

        private boolean isBalance(int x) {
            int[] count = new int[10];
            while (x > 0) {
                count[x % 10]++;
                x /= 10;
            }
            for (int d = 0; d < 10; ++d) {
                if (count[d] > 0 && count[d] != d) {
                    return false;
                }
            }
            return true;
        }
    }

}
