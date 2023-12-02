package AlgotithmExercise.String;

import java.util.*;

public class CloseStrings {

    public boolean closeStrings(String word1, String word2) {
        int w1len = word1.length();
        int w2len = word2.length();
        if(word1.length() != word2.length()) return false;

        Map<Character, Integer> w1Map = new HashMap<>();
        Map<Character, Integer> w2Map = new HashMap<>();
        Map<Integer, List<Character>> w2Map1 = new HashMap<>();

        for (int i = 0; i < w1len; i++) {
            w1Map.put(word1.charAt(i), w1Map.getOrDefault(word1.charAt(i), 0) + 1);
        }

        for (int i = 0; i < w2len; i++) {
            w2Map.put(word2.charAt(i), w2Map.getOrDefault(word2.charAt(i), 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry: w2Map.entrySet()) {
            int count = entry.getValue();
            Character c = entry.getKey();
            List<Character> list = w2Map1.getOrDefault(count, new ArrayList<>());
            list.add(c);
            w2Map1.put(count, list);
        }

        Set<Character> set1 = w1Map.keySet();
        Set<Character> set2 = w2Map.keySet();
        if(set1.size() != set2.size()) return false;
        for (Character c: set2) {
            if(!set1.contains(c)) return false;
        }

        for (Character c: set2) {
            if(w1Map.get(c).equals(w2Map.get(c))) {
                continue;
            }
            int count = w1Map.get(c);
            List<Character> list = w2Map1.get(count);
            if(list != null && list.size() > 0) {
                list.remove(0);
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     *
     * 核心思想：
     * 1. 比较两个字符串的字符集是否完全相同，word1中的不同字符的数目和word2中应该相同
     * 2. 分别将两个字符串的字符出现次数数组f1和f2进行排序后，两个数组从小到大一一相等
     *
     * ：https://leetcode.cn/problems/determine-if-two-strings-are-close/
     *
     *
     */
    class Solution {
        public boolean closeStrings(String word1, String word2) {
            int[] count1 = new int[26], count2 = new int[26];
            for (char c : word1.toCharArray()) {
                count1[c - 'a']++;
            }
            for (char c : word2.toCharArray()) {
                count2[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (count1[i] > 0 && count2[i] == 0 || count1[i] == 0 && count2[i] > 0) {
                    return false;
                }
            }
            Arrays.sort(count1);
            Arrays.sort(count2);
            return Arrays.equals(count1, count2);
        }
    }

}
