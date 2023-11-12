package AlgotithmExercise.String;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxProduct {

    // 这个算法超时了
    private Map<String, Set<Character>> charsMap = new HashMap<>();

    public int maxProduct(String[] words) {
        int maxMulti = 0;

        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {
                if(!hasDuplicateLetter(words[i], words[j])) {
                    maxMulti = Math.max(words[i].length()*words[j].length(), maxMulti);
                }
            }
        }
        return maxMulti;
    }

    public boolean hasDuplicateLetter(String s1, String s2) {
        char[] sarr = s1.toCharArray();
        Set<Character> set = null;
        if(charsMap.containsKey(s1)) {
            set = charsMap.get(s1);
        } else {
            set = new HashSet<>();
            for (char c :
                    sarr) {
                set.add(c);
            }
        }

        sarr = s2.toCharArray();
        for (char c :
                sarr) {
            if(set.contains(c)) return true;
        }
        return false;
    }

    /**
     *
     * 核心思想： 将计算两个字符串是否有出现相同字符的算法复杂度降为1， 将字符的出现情况映射到26个bit位中记录
     *
     *  https://leetcode.cn/problems/maximum-product-of-word-lengths/
     *
     *
     */
    class Solution {
        public int maxProduct(String[] words) {
            int length = words.length;
            int[] masks = new int[length];
            for (int i = 0; i < length; i++) {
                String word = words[i];
                int wordLength = word.length();
                for (int j = 0; j < wordLength; j++) {
                    // 这里应该是1向左移动n多位， 划定26个比特位，来标记每个字符在当前字符串的歘现情况
                    masks[i] |= 1 << (word.charAt(j) - 'a');
                }
            }
            int maxProd = 0;
            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    // 这个判断就是判断两个字符串是否有重复字符
                    if ((masks[i] & masks[j]) == 0) {
                        maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                    }
                }
            }
            return maxProd;
        }
    }

}
