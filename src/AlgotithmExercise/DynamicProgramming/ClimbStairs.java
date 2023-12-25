package AlgotithmExercise.DynamicProgramming;

public class ClimbStairs {

    /**
     * https://leetcode.cn/problems/climbing-stairs/solutions/286022/pa-lou-ti-by-leetcode-solution/?envType=daily-question&envId=2023-12-10
     *
     * 斐波拉契数列就是经典的动态规划题目
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] feibo = new int[46];
        feibo[0] = 1;
        feibo[1] = 1;
        for (int i = 2; i <= n; i++) {
            feibo[i] = feibo[i-1] + feibo[i -2];
        }
        return feibo[n];
    }

}
