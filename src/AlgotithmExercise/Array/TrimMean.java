package AlgotithmExercise.Array;

import java.util.Arrays;

public class TrimMean {

    public double trimMean(int[] arr) {
        Arrays.sort(arr);

        int sum=0;
        int delNum = arr.length/20;
        int count = 0;
        for (int i = delNum; i < arr.length - delNum; i++) {
            sum += arr[i];
            count ++;
        }

        return (double) sum/(double) count;
    }

}
