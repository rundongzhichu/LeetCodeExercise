package AlgotithmExercise.Hash;

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs {

    /*
    回旋镖
    https://leetcode.cn/problems/number-of-boomerangs/?envType=daily-question&envId=2024-01-08
     */
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p : points) {
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int[] q : points) {
                int dis = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int m = entry.getValue();
                ans += m * (m - 1);
            }
        }
        return ans;
    }

}
