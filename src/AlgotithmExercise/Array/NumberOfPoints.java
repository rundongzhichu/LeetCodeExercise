package AlgotithmExercise.Array;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfPoints {
    public int numberOfPoints(List<List<Integer>> nums) {
        Set<Integer> res = new HashSet<>();
        for (List<Integer> num:
                nums){
            for (int i = num.get(0); i <= num.get(1); i++) {
                res.add(i);
            }
        }
        return res.size();
    }
}
