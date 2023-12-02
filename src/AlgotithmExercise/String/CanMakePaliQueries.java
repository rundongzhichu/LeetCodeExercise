package AlgotithmExercise.String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CanMakePaliQueries {

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        for (int[] query :
                queries) {
            res.add(canMakePaliQueries(s, query));
        }
        return res;
    }

    public boolean canMakePaliQueries(String s, int[] queries) {
        int left = queries[0];
        int right = queries[1];
        int k = queries[2];

        int len = right - left + 1;
        int diffCount = 0;
        int end = len / 2;
        Set<Character> numSet = new HashSet<>();
        for (int i = left; i <= right; i++) {
            Character c = s.charAt(i);
            if(numSet.contains(c)) {
                numSet.remove(c);
                continue;
            }
            numSet.add(c);
        }
        if(len % 2 ==0) {
            diffCount = numSet.size() / 2;
        } else {
            diffCount = (numSet.size() -1) /2;
        }
        return diffCount <= k;
    }


    /**
     *
     *     链接：https://leetcode.cn/problems/can-make-palindrome-from-substring/
     *     todo: try to comprehense the solution
     *
     */
    class Solution {
        public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
            int n = s.length();
            int[] count = new int[n + 1];
            for (int i = 0; i < n; i++) {
                count[i + 1] = count[i] ^ (1 << (s.charAt(i) - 'a'));
            }
            List<Boolean> res = new ArrayList<>();
            for (int i = 0; i < queries.length; i++) {
                int l = queries[i][0], r = queries[i][1], k = queries[i][2];
                int bits = 0, x = count[r + 1] ^ count[l];
                while (x > 0) {
                    x &= x - 1;
                    bits++;
                }
                res.add(bits <= k * 2 + 1);
            }
            return res;
        }
    }

}
