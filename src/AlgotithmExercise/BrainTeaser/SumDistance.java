package AlgotithmExercise.BrainTeaser;

import java.util.Arrays;
import java.util.Comparator;

public class SumDistance {

    private int modNum = (int) (Math.pow(10, 9) + 7);

    public int sumDistance(int[] nums, String s, int d) {
        char[] directions = s.toCharArray();

        int[][] numsD = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {
            numsD[i][0] = nums[i];
            numsD[i][1] = directions[i] == 'L' ? -1 : 1;
        }

        Arrays.sort(numsD, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < nums.length; j++) {
                numsD[j][0] += numsD[j][1];
                if(j > 0) {
                    if(numsD[j-1][0] > numsD[j][0]) {
                        numsD[j][1] = -numsD[j][1];
                        numsD[j-1][1] = -numsD[j-1][1];
                        numsD[j][0] += numsD[j][1];
                        numsD[j-1][0] += numsD[j-1][1];
                    }

                    if(numsD[j-1][0] == numsD[j][0]) {
                        numsD[j][1] = -numsD[j][1];
                        numsD[j-1][1] = -numsD[j-1][1];
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < numsD.length; i++) {
            for (int j = i +1 ; j < nums.length; j++) {
                res += numsD[j][0] - numsD[i][0];
                res %= modNum;
            }
        }
        return res;
    }


    /**
     *
     *  https://leetcode.cn/problems/movement-of-robots/solutions/2470646/yi-dong-ji-qi-ren-by-leetcode-solution-tm4n/
     *
     * 基本思路： 当两个机器人相撞时，它们会沿着原本相反的方向移动。由于机器人之间并没有任何区别，相撞可以看做是穿透，
     * 原本左边的机器人相撞后交换为右边的机器人，原本右边的机器人相撞后交换为左边的机器人，
     * 这样一来，两个机器人仿佛没有相撞过。因此，我们可以无视相撞，独立计算每个机器人 ddd 秒后所处的位置。
     *
     */
    class Solution {
        static final int MOD = 1000000007;

        public int sumDistance(int[] nums, String s, int d) {
            int n = nums.length;
            long[] pos = new long[n];
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == 'L') {
                    pos[i] = (long) nums[i] - d;
                } else {
                    pos[i] = (long) nums[i] + d;
                }
            }
            Arrays.sort(pos);
            long res = 0;

            // 这里采用单循环做的累加，实际上把每个位置和前一个位置之间的距离和在计算过程中可能会算到的次数相乘，完成累加
            for (int i = 1; i < n; i++) {
                res += 1L * (pos[i] - pos[i - 1]) * i % MOD * (n - i) % MOD;
                res %= MOD;
            }
            return (int) res;
        }
    }

}
