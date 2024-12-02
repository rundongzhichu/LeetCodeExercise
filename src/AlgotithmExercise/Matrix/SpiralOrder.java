package AlgotithmExercise.Matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int rlen = matrix.length;;
        int clen = matrix[0].length;
        int row = 0, col = 0;
        int count = rlen * clen;
        int[] direction = new int[]{0, 1};
        char dChar = 'R';

        int len = 0;
        while(len <count) {
            if(matrix[row][col] != Integer.MIN_VALUE) {
                len++;
                res.add(matrix[row][col]);
                matrix[row][col] = Integer.MIN_VALUE;
            }
            int tempRow = row + direction[0];
            int tempCol = col + direction[1];
            if(0 <= tempRow && tempRow < rlen && 0 <= tempCol && tempCol < clen && matrix[tempRow][tempCol] != Integer.MIN_VALUE) {
                row = tempRow;
                col = tempCol;
                continue;
            }

            if(dChar == 'R') {
                direction = new int[]{1,0};
                dChar = 'D';
            } else if(dChar == 'D') {
                direction = new int[] {0, -1};
                dChar = 'L';
            } else if(dChar == 'L') {
                direction = new int[] {-1, 0};
                dChar = 'U';
            } else if(dChar == 'U') {
                direction = new int[] {0, 1};
                dChar = 'R';
            }
        }
        return res;
    }


    /**
     *
     * 描述： 螺旋矩阵
     * 链接：https://leetcode.cn/problems/spiral-matrix/solutions/275393/luo-xuan-ju-zhen-by-leetcode-solution/
     * 核心思路：  模拟
     *
     */
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> order = new ArrayList<Integer>();
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return order;
            }
            int rows = matrix.length, columns = matrix[0].length;
            boolean[][] visited = new boolean[rows][columns];
            int total = rows * columns;
            int row = 0, column = 0;
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int directionIndex = 0;
            for (int i = 0; i < total; i++) {
                order.add(matrix[row][column]);
                visited[row][column] = true;
                int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
                // 这里切换方向的思路值得参考
                if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                    directionIndex = (directionIndex + 1) % 4;
                }
                row += directions[directionIndex][0];
                column += directions[directionIndex][1];
            }
            return order;
        }
    }

}
