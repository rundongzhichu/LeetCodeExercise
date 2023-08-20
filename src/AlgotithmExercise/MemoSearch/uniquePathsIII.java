package AlgotithmExercise.MemoSearch;

import AlgotithmExercise.Main;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class uniquePathsIII {

    public static void main(String[] args) {

        int[][] grid = new int[][] {
                new int[] {1,0,0,0},
                new int[] {0,0,0,0},
                new int[] {0,0,0,2}
        };
        System.out.println(new uniquePathsIII().uniquePathsIII(grid));

    }


    /**
     * 参考： https://leetcode.cn/problems/unique-paths-iii/solutions/2365866/bu-tong-lu-jing-iii-by-leetcode-solution-cndw/
     * 回溯
     *  可以不那么复杂的，就是直接用操作grid，当等于0的时候记个数，然后经过这个把他设置成-1，然后递归出来以后把它设置为0
     * @param grid
     * @return
     */
    public int uniquePathsIII(int[][] grid) {

        int[][] directions = new int[][]{
                new int[]{0,1},
                new int[]{1,0},
                new int[]{0,-1},
                new int[]{-1,0}
        };

        Position start = null;
        Position end = null;
        int emptyCellCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) emptyCellCount++;
                else if(grid[i][j] == 1) {
                    start = new Position(i, j);
                } else if(grid[i][j] == 2) {
                    end = new Position(i, j);
                }
            }
        }

        Set<Position> record = new HashSet<>();
        return memorySearch(start, end, grid, directions, record, emptyCellCount +1);
    }

    public int memorySearch(Position start, Position end, int[][] grid, int[][] directions,
                            Set<Position> record, int emptyCellCount) {
        if(start.x < 0 || start.x >= grid.length || start.y<0||start.y>=grid[0].length
                || grid[start.x][start.y] == -1 || record.contains(start)) return 0;
        if(start.equals(end) ) {
            if(record.size() == emptyCellCount) {
                return 1;
            } else {
                return 0;
            }
        }

        int path = 0;
        if(grid[start.x][start.y] == 0 || grid[start.x][start.y] == 1) {
            record.add(start);
            for (int[] direction :
                    directions) {
                Position nexPos = new Position(start.x+ direction[0], start.y +direction[1]);
                path += memorySearch(nexPos,
                        end, grid, directions, record, emptyCellCount);
            }
            record.remove(start);
        }

        return path;
    }

    class Position {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return getX() == position.getX() && getY() == position.getY();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getX(), getY());
        }
    }

    class Solution {
        static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int uniquePathsIII(int[][] grid) {
            int r = grid.length, c = grid[0].length;
            int si = 0, sj = 0, n = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (grid[i][j] == 0) {
                        n++;
                    } else if (grid[i][j] == 1) {
                        n++;
                        si = i;
                        sj = j;
                    }
                }
            }
            return dfs(grid, si, sj, n);
        }

        public int dfs(int[][] grid, int i, int j, int n) {
            if (grid[i][j] == 2) {
                return n == 0 ? 1 : 0;
            }
            int r = grid.length, c = grid[0].length;
            int t = grid[i][j];
            grid[i][j] = -1;
            int res = 0;
            for (int[] dir : dirs) {
                int ni = i + dir[0], nj = j + dir[1];
                if (ni >= 0 && ni < r && nj >= 0 && nj < c && (grid[ni][nj] == 0 || grid[ni][nj] == 2)) {
                    res += dfs(grid, ni, nj, n - 1);
                }
            }
            grid[i][j] = t;
            return res;
        }
    }
    
}
