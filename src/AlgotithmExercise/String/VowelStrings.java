package AlgotithmExercise.String;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VowelStrings {

    public int vowelStrings(String[] words, int left, int right) {
        Set vowelSet = new HashSet(Arrays.asList('a','e', 'i', 'o', 'u'));

        int count=0;
        for (int i = left; i <= right; i++) {
            String word = words[i];
            char first = word.charAt(0);
            char last = word.charAt(word.length() - 1);

            if( vowelSet.contains(first) && vowelSet.contains(last))
                count++;
        }
        return count;
    }


    /**
     *
     *  链接：https://leetcode.cn/problems/count-the-number-of-vowel-strings-in-range/
     *
     */
    class Solution {
        public int vowelStrings(String[] words, int left, int right) {
            Set<Character> vowels = new HashSet<Character>() {{
                add('a');
                add('e');
                add('i');
                add('o');
                add('u');
            }};
            int ans = 0;
            for (int i = left; i <= right; ++i) {
                String word = words[i];
                if (vowels.contains(word.charAt(0)) && vowels.contains(word.charAt(word.length() - 1))) {
                    ++ans;
                }
            }
            return ans;
        }
    }

}
