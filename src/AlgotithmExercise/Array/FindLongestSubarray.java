package AlgotithmExercise.Array;

import java.util.*;

public class FindLongestSubarray {

    public String[] findLongestSubarray(String[] array) {
        Map<Integer, Integer> statistics = new HashMap<>();

        int charCount = 0;
        int numCount = 0;
        int leftIndex = array.length;
        int rightIndex = array.length;
        int maxSubLen = 0;
        for (int i = 0; i < array.length; i++) {
            if(Character.isAlphabetic(array[i].charAt(0))) charCount++;
            if (Character.isDigit(array[i].charAt(0))) numCount++;
            System.out.println("charCount = " + charCount);
            System.out.println("numCount = " + numCount);
            System.out.println("array = " + array[i]);

            int diff = charCount - numCount;
            if(statistics.containsKey(diff)) {
                Integer preIndex = statistics.get(diff);
                if(maxSubLen < i-preIndex +1) {
                    leftIndex = preIndex;
                    rightIndex = i + 1;
                    maxSubLen = i-preIndex +1;
                }
            }else if(diff==0){
                leftIndex = 0;
                rightIndex = i + 1;
                maxSubLen = i+1;
            }
            else {
                statistics.put(diff, i+1);
            }
        }
        return Arrays.copyOfRange(array, leftIndex, rightIndex);
    }

}
