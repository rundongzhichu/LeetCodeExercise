package AlgotithmExercise.Array;

import java.util.Arrays;

public class SpecialArray {
    public int specialArray(int[] nums) {
        Arrays.sort(nums);

        boolean flag = false;
        int count=1;
        for(int i=nums.length-1; i>=0; i--){
            if(nums[i] >= count) {
                if(i-1 < 0 || (i-1>0 && nums[i-1] < count));
                    return count;
            }
            count++;
        }

        return -1;
    }
}
