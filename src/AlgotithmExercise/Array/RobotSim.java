package AlgotithmExercise.Array;

import java.util.*;

public class RobotSim {

    public int robotSim(int[] commands, int[][] obstacles) {
        int LEFT = -2, RIGHT = -1;
        int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
        Map<Integer, int[]> directions = new HashMap<>();
        directions.put(NORTH, new int[]{0,1});
        directions.put(EAST, new int[]{1,0});
        directions.put(SOUTH, new int[]{0,-1});
        directions.put(WEST, new int[]{-1,0});

        Map<Pair, Integer> directionsChange = new HashMap<>();
        directionsChange.put(new Pair(NORTH, LEFT), WEST);
        directionsChange.put(new Pair(NORTH, RIGHT), EAST);
        directionsChange.put(new Pair(EAST, LEFT), NORTH);
        directionsChange.put(new Pair(EAST, RIGHT), SOUTH);
        directionsChange.put(new Pair(SOUTH, LEFT), EAST);
        directionsChange.put(new Pair(SOUTH, RIGHT), WEST);
        directionsChange.put(new Pair(WEST, LEFT), SOUTH);
        directionsChange.put(new Pair(WEST, RIGHT), NORTH);

        Set<Pair> legalPosition = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            legalPosition.add(new Pair(obstacles[i][0], obstacles[i][1]));
        }

        int curD = NORTH;
        Pair curPos = new Pair(0,0);
        for (int i = 0; i < commands.length; i++) {
            if(commands[i] == LEFT) {
                curD = directionsChange.get(new Pair(curD, LEFT));
            } else if(commands[i] == RIGHT) {
                curD = directionsChange.get(new Pair(curD, RIGHT));
            } else {
                int[] directionChange = directions.get(curD);
                for (int j = 0; j < commands[i]; j++) {
                    Pair newPos = new Pair(curPos.a + directionChange[0], curPos.b + directionChange[1]);
                    if(legalPosition.contains(newPos)) {
                        break;
                    }
                    curPos = newPos;
                }
            }
        }
        return (int) (Math.pow(curPos.a, 2) + Math.pow(curPos.b, 2));
    }

    class Pair{
        int a,b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a && b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    class Solution{
        public int robotSim(int[] commands, int[][] obstacles) {
            int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            int px = 0, py = 0, d = 1;
            Set<Integer> set = new HashSet<Integer>();
            for (int[] obstacle : obstacles) {
                // 计算这个数我没看懂，反正能计算出一个点对应的唯一的数值
                set.add(obstacle[0] * 60001 + obstacle[1]);
            }
            int res = 0;
            for (int c : commands) {
                if (c < 0) {
                    d += c == -1 ? 1 : -1;
                    d %= 4;
                    if (d < 0) {
                        d += 4;
                    }
                } else {
                    for (int i = 0; i < c; i++) {
                        if (set.contains((px + dirs[d][0]) * 60001 + py + dirs[d][1])) {
                            break;
                        }
                        px += dirs[d][0];
                        py += dirs[d][1];
                        res = Math.max(res, px * px + py * py);
                    }
                }
            }
            return res;
        }
    }

}
