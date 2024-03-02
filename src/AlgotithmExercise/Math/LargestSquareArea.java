package AlgotithmExercise.Math;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LargestSquareArea {


    /*
    暴力求解，枚举所有情况，最愚蠢的做法，而且还没调试通
     */
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        Map<int[], int[]> btlr = new HashMap<>();
        for (int i = 0; i < bottomLeft.length; i++) {
            btlr.put(bottomLeft[0], topRight[0]);
        }

        Arrays.sort(bottomLeft, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        long maxSquare = 0;
        for (int i = 0; i < bottomLeft.length - 1; i++) {
            int[] bl1 = bottomLeft[i];
            int[] tr1 = btlr.get(bl1);
            for (int j = i + 1; j < bottomLeft.length; j++) {
                int[] bl2 = bottomLeft[j];
                if(bl2[0] <= tr1[0]) {
                    int[] tr2 = btlr.get(bl2);
                    if(tr1[0] >= tr2[0]) {
                        if(bl2[1] <= tr1[1] && tr1[1] <= tr2[1]) {
                            // 1 矩形的右上部分和2矩形的下面部分相交
                            maxSquare = (long) Math.max(Math.pow(Math.min(tr1[1] - bl2[1], tr2[0] - bl2[0]), 2), maxSquare);
                        } else if(tr1[1] >= tr2[1] && bl1[1] <= bl2[1]) {
                            // 1矩形Y轴边包含2矩形的y轴边
                            maxSquare = (long) Math.max(Math.pow(Math.min(tr2[1] - bl2[1], tr2[0] - bl2[0]), 2), maxSquare);
                        } else if(tr1[1] >= tr2[1] && bl2[1] <= bl1[1]) {
                            // 1 矩形的右下部分和2矩形的上面面部分相交
                            maxSquare = (long) Math.max(Math.pow(Math.min(tr2[1] - bl1[1], tr2[0] - bl2[0]), 2), maxSquare);
                        } else if(tr2[1] >= tr1[1] && bl2[1] <= bl1[1]) {
                            // 1 矩形的右下部分和2矩形的上面面部分相交
                            maxSquare = (long) Math.max(Math.pow(Math.min(tr1[1] - bl1[1], tr2[0] - bl2[0]), 2), maxSquare);
                        }
                    } else {
                        if(bl2[1] <= tr1[1] && tr1[1] <= tr2[1]) {
                            // 1 矩形的右上部分和2矩形的下面部分相交
                            maxSquare = (long) Math.max(Math.pow(Math.min(tr1[1] - bl2[1], tr1[0] - bl2[0]), 2), maxSquare);
                        } else if(tr1[1] >= tr2[1] && bl1[1] <= bl2[1]) {
                            // 1矩形Y轴边包含2矩形的y轴边
                            maxSquare = (long) Math.max(Math.pow(Math.min(tr1[0] - bl2[0], tr2[1] - bl2[1]), 2), maxSquare);
                        } else if(tr1[1] >= tr2[1] && bl2[1] <= bl1[1]) {
                            // 1 矩形的右下部分和2矩形的上面面部分相交
                            maxSquare = (long) Math.max(Math.pow(Math.min(tr2[1] - bl1[1], tr1[0] - bl2[0]), 2), maxSquare);
                        } else if(tr2[1] >= tr1[1] && bl2[1] <= bl1[1]) {
                            // 1 矩形的右下部分和2矩形的上面面部分相交
                            maxSquare = (long) Math.max(Math.pow(Math.min(tr1[0] - bl2[0], tr1[1] - bl1[1]), 2), maxSquare);
                        }
                    }
                }
            }
        }
        return maxSquare;
    }


    /*
    链接：https://leetcode.cn/problems/find-the-largest-area-of-square-inside-two-rectangles/
     */
    class Solution {
        public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
            long ans = 0;
            for (int i = 0; i < bottomLeft.length; i++) {
                int[] b1 = bottomLeft[i];
                int[] t1 = topRight[i];
                for (int j = i + 1; j < bottomLeft.length; j++) {
                    int[] b2 = bottomLeft[j];
                    int[] t2 = topRight[j];
                    int height = Math.min(t1[1], t2[1]) - Math.max(b1[1], b2[1]);
                    int width = Math.min(t1[0], t2[0]) - Math.max(b1[0], b2[0]);
                    // 这里的size > 0 巧妙地判断了两个矩形是否相交
                    int size = Math.min(width, height);
                    if (size > 0) {
                        ans = Math.max(ans, (long) size * size);
                    }
                }
            }
            return ans;
        }
    }

}
