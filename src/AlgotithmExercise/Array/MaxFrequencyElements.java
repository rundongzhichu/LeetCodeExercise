package AlgotithmExercise.Array;

import java.util.Arrays;

public class MaxFrequencyElements {

    public int maxFrequencyElements(int[] nums) {
        int[] statisticArr = new int[101];
        for (int num :
                nums) {
            statisticArr[num]++;
        }

        Arrays.sort(statisticArr);

        Integer max = null;
        int res = 0;
        for (int i = 100; i >=0; i--) {
            if(max == null && statisticArr[i] > 0) {
                max = statisticArr[i];
                res += statisticArr[i];
            }
            if(max != null && max ==statisticArr[i]) {
                res += statisticArr[i];
            }
        }
        return res;
    }

}
