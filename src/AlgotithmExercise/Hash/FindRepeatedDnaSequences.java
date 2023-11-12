package AlgotithmExercise.Hash;

import java.util.*;

public class FindRepeatedDnaSequences {

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> res = new HashSet<>();
        Set<String> record = new HashSet<>();
        int len = s.length();
        for (int i = 0; i < len - 10; i++) {
            String subStr = s.substring(i, i+10);
            if (record.contains(subStr)) {
                res.add(subStr);
            }
            record.add(subStr);
        }
        return res.stream().toList();
    }

    /**
     *
     * 链接：https://leetcode.cn/problems/repeated-dna-sequences/
     *
     * 基本思想： 哈希表+滑动窗口+位运算
     * 把字符通过bit位来表示
     *
     *
     */
    class Solution {
        static final int L = 10;
        Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }};

        public List<String> findRepeatedDnaSequences(String s) {
            List<String> ans = new ArrayList<String>();
            int n = s.length();
            if (n <= L) {
                return ans;
            }
            int x = 0;
            // 这里是先把9个字符放进int的比特位位里面
            for (int i = 0; i < L - 1; ++i) {
                x = (x << 2) | bin.get(s.charAt(i));
            }

            // 这里就是把第10个字符采用滑动窗口的方式推进位里面
            // 然后采用int实际数值记录到hashmap中
            // & ((1 << (L * 2)) - 1); 这个操作是把x高于20位的之前的记录清0，保证当前的x值代表的是当下的10个字符串
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int i = 0; i <= n - L; ++i) {
                x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
                cnt.put(x, cnt.getOrDefault(x, 0) + 1);
                if (cnt.get(x) == 2) {
                    ans.add(s.substring(i, i + L));
                }
            }
            return ans;
        }
    }


}
