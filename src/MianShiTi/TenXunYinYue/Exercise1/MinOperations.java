package MianShiTi.TenXunYinYue.Exercise1;

import java.util.*;

public class MinOperations {
    public int minOperations (String str) {
        if(str==null || str.length()==0) return 0;
        // write code here
        char[] strChars = str.toCharArray();
        Map<Character, Integer> recor = new HashMap<>();

        for(int i=0; i<strChars.length; i++){
            recor.put(strChars[i], recor.getOrDefault(strChars[i],0)+1);
        }

        Set<Character> keys = recor.keySet();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(Integer c: recor.values()){
            pq.offer(c);
        }

        int remain = 26 - keys.size();
        int operations = 0;
        for(int val: pq){
            int divide = val / 2;
            int dr = val % 2;

            if(remain>divide){
                operations += divide;
                if(val == 0){
                    remain -= divide-1;
                }else{
                    remain -= divide;
                }
            }else{
                int temp = (divide - remain)*2 + dr;
                operations += temp -1;
            }
        }
        return operations;
    }

    public static void main(String[] args) {
        System.out.println(new MinOperations().minOperations("abab"));
//
//        Pair p1 = new Pair('a',1);
//        Pair p2 = new Pair('b',2);
//        System.out.println(p1.hashCode() == p2.hashCode());
    }
}
