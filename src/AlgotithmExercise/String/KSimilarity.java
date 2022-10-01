package AlgotithmExercise.String;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KSimilarity {
    public int kSimilarity(String s1, String s2) {
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();

        Map<Character, Set<Integer>>  cIndexes = new HashMap<Character, Set<Integer>>();
        for (int i = 0; i < s2Chars.length; i++) {
            Set<Integer> indexes = cIndexes.getOrDefault(s2Chars[i], new HashSet<Integer>());
            indexes.add(i);
            if(!cIndexes.containsKey(s2Chars[i])){
                cIndexes.put(s2Chars[i], indexes);
            }
        }

        int k=0;
        for (int i = 0; i < s1Chars.length; i++) {
            if(s1Chars[i]!=s2Chars[i]){
                Set indexes = cIndexes.get(s1Chars[i]);
                Set indexes1 = cIndexes.get(s2Chars[i]);
                boolean flag = false;
                int lastIndex=i;
                for (Object index : indexes) {
                    System.out.println("index = " + index);
                    int iindex  = (int)index;
                    if(iindex > i && s1Chars[iindex] == s2Chars[i] ){
                        char temp = s2Chars[i];
                        s2Chars[i] = s2Chars[iindex];
                        s2Chars[iindex] = temp;
                        flag = true;
                        indexes.remove(iindex);
                        indexes.add(i);


                        indexes1.add(iindex);
                        indexes1.remove(i);
                        break;
                    }
                    lastIndex = iindex;
                }

                if(!flag){
                    char temp = s2Chars[i];
                    s2Chars[i] = s2Chars[lastIndex];
                    s2Chars[lastIndex] = temp;
                    indexes.remove(lastIndex);
                    indexes.add(i);

                    indexes1.add(lastIndex);
                    indexes1.remove(i);
                }
                k++;
            }
        }
        return k;
    }
}
