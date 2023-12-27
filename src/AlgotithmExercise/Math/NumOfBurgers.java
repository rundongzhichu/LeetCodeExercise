package AlgotithmExercise.Math;

import java.util.ArrayList;
import java.util.List;

public class NumOfBurgers {

    /**
     * https://leetcode.cn/problems/number-of-burgers-with-no-waste-of-ingredients/solutions/101702/bu-lang-fei-yuan-liao-de-yi-bao-zhi-zuo-fang-an-2/?envType=daily-question&envId=2023-12-25
     *
     * @param tomatoSlices
     * @param cheeseSlices
     * @return
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if(tomatoSlices % 2 == 0) {
            int k = tomatoSlices / 2;
            int small  = 2*cheeseSlices - k;
            int big = k - cheeseSlices;
            if(small >= 0 && big >= 0) {
                return new ArrayList<>(){{add(big); add(small);}};
            }
        }
        return new ArrayList<>(){{add(0); add(0);}};
    }

}
