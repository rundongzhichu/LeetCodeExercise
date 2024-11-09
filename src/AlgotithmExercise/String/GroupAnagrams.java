package AlgotithmExercise.String;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String key = calcuKey(strs[i]);
            List<String> arr = res.getOrDefault(key, new ArrayList<>());
            arr.add(strs[i]);
            res.put(key, arr);
        }
        return res.values().stream().toList();
    }

    public String calcuKey(String str) {
        int str1Len = str.length();
        int[] alpas = new int[26];
        for (int i = 0; i < str1Len; i++) {
            alpas[str.charAt(i) - 'a'] ++;
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 26; i++) {
            if(alpas[i] != 0) {
                sb.append(alpas[i]).append((char)('a' + i));
            }
        }
        return sb.toString();
    }


    /**
     *  基本思想：
     *  将字符串拆出来排序，生成key， 放到map中统计
     *
     * 参考链接： https://leetcode.cn/problems/group-anagrams/solutions/520469/zi-mu-yi-wei-ci-fen-zu-by-leetcode-solut-gyoc/?envType=study-plan-v2&envId=top-100-liked
     *
     */
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<String, List<String>>();
            for (String str : strs) {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                String key = new String(array);
                List<String> list = map.getOrDefault(key, new ArrayList<String>());
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<List<String>>(map.values());
        }
    }

}
