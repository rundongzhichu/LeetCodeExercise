package AlgotithmExercise.String;

import java.util.HashSet;
import java.util.Set;

public class NumJewelsInStones {

    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelsSet = new HashSet<>();

        char[] jarray = jewels.toCharArray();
        for (int i = 0; i < jarray.length; i++) {
            jewelsSet.add(jarray[i]);
        }

        int jcount = 0;
        int slen = stones.length();
        for (int i = 0; i < slen; i++) {
            if(jewelsSet.contains(stones.charAt(i))) {
                jcount++;
            }
        }
        return jcount;
    }

}
