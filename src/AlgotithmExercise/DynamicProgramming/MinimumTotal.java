package AlgotithmExercise.DynamicProgramming;

import java.util.List;

public class MinimumTotal {

    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size();
            int[][] f = new int[2][n];
            f[0][0] = triangle.get(0).get(0);
            for (int i = 1; i < n; ++i) {
                int curr = i % 2;
                int prev = 1 - curr;
                f[curr][0] = f[prev][0] + triangle.get(i).get(0);
                for (int j = 1; j < i; ++j) {
                    f[curr][j] = Math.min(f[prev][j - 1], f[prev][j]) + triangle.get(i).get(j);
                }
                f[curr][i] = f[prev][i - 1] + triangle.get(i).get(i);
            }
            int minTotal = f[(n - 1) % 2][0];
            for (int i = 1; i < n; ++i) {
                minTotal = Math.min(minTotal, f[(n - 1) % 2][i]);
            }
            return minTotal;
        }
    }
//
//    作者：力扣官方题解
//    链接：https://leetcode.cn/problems/IlPe0q/solutions/1036365/san-jiao-xing-zhong-zui-xiao-lu-jing-zhi-srun/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
