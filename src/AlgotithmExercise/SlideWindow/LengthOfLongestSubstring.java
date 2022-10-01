package AlgotithmExercise.SlideWindow;

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
}
