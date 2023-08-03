package AlgotithmExercise.Array;

import java.util.Arrays;

public class DeleteGreatestValue {

    public int deleteGreatestValue(int[][] grid) {
        int rows = grid.length;

        for (int i = 0; i < rows; i++) {
            Arrays.sort(grid[i]);
        }

        int res = 0;
        for (int i = 0; i < grid[0].length; i++) {
            int maxVal = 0;
            for (int j = 0; j < rows; j++) {
                maxVal = Math.max(maxVal, grid[j][i]);
            }

            res += maxVal;
        }
        return res;
    }

}
