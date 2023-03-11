package AlgotithmExercise.Array;

import java.util.concurrent.ConcurrentHashMap;

public class LargestLocal {

    public int[][] largestLocal(int[][] grid) {
        int[][] res = new int[grid.length-2][grid[0].length -2];
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                res[i-1][j-1] = largetNumOf3x3Matrix(grid, i, j);
            }
        }
        return res;
    }

    public int largetNumOf3x3Matrix(int[][] grid, int row, int col) {
        int maxVal = 0;
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col -1; j < col + 2; j++) {
                maxVal = Math.max(grid[i][j], maxVal);
            }
        }
        return maxVal;
    }

}
