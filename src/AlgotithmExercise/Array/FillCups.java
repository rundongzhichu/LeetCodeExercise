package AlgotithmExercise.Array;

import java.util.Arrays;

public class FillCups {

    public int fillCups(int[] amount) {
        Arrays.sort(amount);

        if(amount[2] - amount[1]-amount[0] >= 0){
            return amount[2];
        } else {
            int secods = 0;
            if(amount[0] > amount[1]){
               amount[0] --;
               amount[2] --;
               secods += fillCups(amount) +1;
            } else {
                amount[1]--;
                amount[2]--;
                secods += fillCups(amount) +1;
            }
            return secods;
        }
    }

}
