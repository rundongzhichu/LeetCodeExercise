package Math;

import java.util.*;

public class MaximumSwap {
    public int maximumSwap(int num) {
        List<Integer> nums = new ArrayList<>();

        int numCopy = num;
        while (numCopy/10 >= 0){
            int remain = numCopy%10;
            nums.add(remain);
            if(numCopy/10==0 && remain>0){
                break;
            }
            numCopy/=10;
        }

        TreeMap<Integer, Integer> tmap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        int size = nums.size();
        for (int i = size - 1; i >= 0; i--) {
            tmap.put(nums.get(i),i);
        }

        for(int i = size - 1; i >= 0; i--){
            int ival = (int)nums.get(i);
            int maxIndex = tmap.firstEntry().getValue();
            if(ival == (int)tmap.firstKey()){
                if(i == maxIndex){
                    tmap.remove(tmap.firstKey());
                }
                continue;
            }else{
                if(maxIndex<i){
                    int mval = (int)nums.get(maxIndex);
                    return num+(mval-ival)*(int)Math.pow(10, i) + (ival-mval)*(int)Math.pow(10, maxIndex);
                }
                break;
            }
        }
        return num;
    }

    class Solution {
        public int maximumSwap(int num) {
            char[] charArray = String.valueOf(num).toCharArray();
            int n = charArray.length;
            int maxNum = num;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    swap(charArray, i, j);
                    maxNum = Math.max(maxNum, Integer.parseInt(new String(charArray)));
                    swap(charArray, i, j);
                }
            }
            return maxNum;
        }

        public void swap(char[] charArray, int i, int j) {
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
        }
    }
}
