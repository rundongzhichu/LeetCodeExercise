package AlgotithmExercise.Array;

import java.util.HashSet;
import java.util.Set;

public class NumberOfPairs {

    public int[] numberOfPairs(int[] nums) {
        int[] answer = new int[2];

        answer[1] = nums.length;
        Set<Integer> records = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(records.contains(nums[i])){
                records.remove(nums[i]);
                answer[0] ++;
                answer[1] -= 2;
                continue;
            }
            records.add(nums[i]);
        }
        return answer;
    }

}
