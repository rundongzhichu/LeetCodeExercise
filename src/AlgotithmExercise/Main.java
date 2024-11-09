package AlgotithmExercise;


import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

public class Main {

    private Semaphore aSem = new Semaphore(1);
    private Semaphore bSem = new Semaphore(0);
    private Semaphore cSem = new Semaphore(0);

    public static void main(String[] args) {
        new Main().test();
    }

    public void test() {

    }

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

}


