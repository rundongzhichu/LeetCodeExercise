package AlgotithmExercise.Array;

import java.util.Arrays;

public class MaximumGroups {

    public int maximumGroups(int[] grades) {
        Arrays.sort(grades);
        int group = 0;
        for (int i = 0; i < grades.length; i += group) {
            group++;
            if( i + group>grades.length) {
                group --;
                break;
            }
        }
        return group;
    }

}
