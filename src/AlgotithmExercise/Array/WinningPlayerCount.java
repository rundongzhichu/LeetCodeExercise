package AlgotithmExercise.Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WinningPlayerCount {

    public int winningPlayerCount(int n, int[][] pick) {
        Map<Integer, int[]> balls = new HashMap<>();
        Set<Integer> players = new HashSet<>();
        for (int i = 0; i < pick.length; i++) {
            if(players.contains(pick[i][0])) continue;
            int[] sameColorBallArr = balls.getOrDefault(pick[i][0], new int[11]);
            sameColorBallArr[pick[i][1]] ++;
            balls.put(pick[i][0], sameColorBallArr);
            if(sameColorBallArr[pick[i][1]] > pick[i][0]) {
                players.add(pick[i][0]);
            }
        }
        return players.size();
    }

    /**
     *  链接：https://leetcode.cn/problems/find-the-number-of-winning-players/solutions/2984415/qiu-chu-sheng-li-wan-jia-de-shu-mu-by-le-nf6v/
     *
     * 基本思路： 二维数组保存统计结果
     */
    class Solution {
        public int winningPlayerCount(int n, int[][] pick) {
            int[][] cnt = new int[n][11];
            for (int[] p : pick) {
                cnt[p[0]][p[1]]++;
            }
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= 10; j++) {
                    if (cnt[i][j] > i) {
                        ans++;
                        break;
                    }
                }
            }
            return ans;
        }
    }


}
