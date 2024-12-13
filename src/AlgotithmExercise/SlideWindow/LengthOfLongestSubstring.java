package AlgotithmExercise.SlideWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> cset = new HashSet<>();
        char[] schars = s.toCharArray();
        int maxLen=0;

        if(schars.length==0) return 0;

        int start = 0;
        cset.add(schars[start]);
        for(int j = start+1;j<schars.length; j++){
            if(cset.contains(schars[j])){
                maxLen = Math.max(maxLen, cset.size());
                int k=start;
                while (k<j) {
                    if (schars[k] == schars[j]) {
                        cset.remove(schars[k]);
                        k++;
                        break;
                    }
                    cset.remove(schars[k]);
                    k++;
                }
                start = k;
                j--;
                continue;
            }else cset.add(schars[j]);
        }

        maxLen = Math.max(maxLen, cset.size());
        return maxLen;
    }

    /**
     * 第二次写代码
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        if(s.isEmpty()) return 0;
        char[] sChars = s.toCharArray();
        Set<Character> set = new HashSet<>();

        int start = 0, end = 0;
        int maxLen = 1;
        int len = 0;
        while (start <= end && end < sChars.length) {
            if(set.contains(sChars[end])) {
                while (start < end) {
                    if(sChars[start] == sChars[end]) {
                        start++;
                        len = set.size();
                        break;
                    }
                    set.remove(sChars[start]);
                    start++;
                }
            } else {
                set.add(sChars[end]);
                len++;
            }
            maxLen = Math.max(maxLen, len);
            end++;
        }
        return maxLen;
    }


    /**
     *
     * 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/solutions/227999/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
     * 核心思想： 双指针 + Hashset
     *
     */
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            // 哈希集合，记录每个字符是否出现过
            Set<Character> occ = new HashSet<Character>();
            int n = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int rk = -1, ans = 0;
            for (int i = 0; i < n; ++i) {
                if (i != 0) {
                    // 左指针向右移动一格，移除一个字符
                    occ.remove(s.charAt(i - 1));
                }
                while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                    // 不断地移动右指针
                    occ.add(s.charAt(rk + 1));
                    ++rk;
                }
                // 第 i 到 rk 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, rk - i + 1);
            }
            return ans;
        }
    }

}
