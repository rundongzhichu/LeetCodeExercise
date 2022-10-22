package AlgotithmExercise.DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class DistinctSubseqII {
    public int distinctSubseqII(String s) {
        char[] sChars = s.toCharArray();

        Set<String> subQueueSet = new HashSet<>();
        int[] subQueueCounts = new int[sChars.length];

        String preSubQueue = "";
        for (int i = sChars.length -1; i >= 0 ; i--) {

            if(subQueueSet.contains(preSubQueue + sChars[i])){

            }
        }
        return 0;
    }
}
