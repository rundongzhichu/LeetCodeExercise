package AlgotithmExercise.String;

import java.util.*;

public class UniqueLetterString {
    public int uniqueLetterString(String s) {
        char[] chars = s.toCharArray();

        Map<String,Integer> recor = new HashMap<>();

        int total = 0;
        for(int i=0; i< chars.length; i++){
            for(int j=i; j<chars.length;j++){
                String key = s.substring(i,j+1);
                if(recor.containsKey(key)){
                    total+= recor.get(key);
                    continue;
                }
                int c = countUniqueChars(chars, i,j);
                recor.put(key, c);
                total += c;
            }
        }

        return total;
    }

    public int countUniqueChars(char[] chars, int start, int end) {
        Map<Character, Boolean> flags = new HashMap<>();
        Set<Character> recor = new HashSet<>();

        for(int i=start;i<=end;i++){
            if(flags.containsKey(chars[i])){
                if(flags.get(chars[i])){
                    flags.put(chars[i], Boolean.FALSE);
                    recor.remove(chars[i]);
                }
            }else{
                flags.put(chars[i], Boolean.TRUE);
                recor.add(chars[i]);
            }
        }
        return recor.size();
    }

    public static void main(String[] args) {
//        System.out.println(new UniqueLetterString().countUniqueChars("LEETCODE"));
    }

    class Solution {
        public int uniqueLetterString(String s) {
            Map<Character, List<Integer>> index = new HashMap<Character, List<Integer>>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!index.containsKey(c)) {
                    index.put(c, new ArrayList<Integer>());
                    index.get(c).add(-1);
                }
                index.get(c).add(i);
            }
            int res = 0;
            for (Map.Entry<Character, List<Integer>> entry : index.entrySet()) {
                List<Integer> arr = entry.getValue();
                arr.add(s.length());
                for (int i = 1; i < arr.size() - 1; i++) {
                    res += (arr.get(i) - arr.get(i - 1)) * (arr.get(i + 1) - arr.get(i));
                }
            }
            return res;
        }
    }

}
