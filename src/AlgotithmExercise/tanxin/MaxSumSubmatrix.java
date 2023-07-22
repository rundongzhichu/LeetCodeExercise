package AlgotithmExercise.tanxin;

import java.util.TreeSet;

public class MaxSumSubmatrix {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        // record value sum of rectangle whose lef-top is (0,0)
        int[][] areaSum = new int[rows][columns];

        areaSum[0][0] = matrix[0][0];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int valTop = 0, valLeft = 0, valLeftTop = 0;
                if(i>0) {
                    valTop = areaSum[i-1][j];
                }

                if(j > 0) {
                    valLeft = areaSum[i][j-1];
                }

                if(i>0 && j>0) {
                    valLeftTop = areaSum[i-1][j-1];
                }

                areaSum[i][j] = valLeft + valTop - valLeftTop + matrix[i][j];
            }
        }

        int maxSum = Integer.MIN_VALUE;
        for (int starti = 0; starti < rows; starti++) {
            for (int startj = 0; startj < columns; startj++) {
                for (int endi = starti; endi < rows; endi++) {
                    for (int endj = startj; endj < columns; endj++) {
                        int valTop = 0, valLeft = 0, valLeftTop = 0;
                        if(starti > 0) {
                            valTop = areaSum[starti -1][endj];
                        }

                        if(startj > 0) {
                            valLeft = areaSum[endi][startj - 1];
                        }

                        if(starti > 0 && startj >0){
                            valLeftTop = areaSum[starti-1][startj-1];
                        }

                        int sum = areaSum[endi][endj] - valLeft - valTop + valLeftTop;
                        if(sum == k) return sum;
                        if(sum <k)
                            maxSum  = Math.max(sum, maxSum);
                    }
                }
            }
        }
        return maxSum;
    }


    /**
     * 核心思想：枚举矩形的上下边界，计算矩形每一列的和，形成有序数组
     * https://leetcode.cn/problems/max-sum-of-rectangle-no-larger-than-k/solutions/736564/ju-xing-qu-yu-bu-chao-guo-k-de-zui-da-sh-70q2/
     */
    class Solution {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            int ans = Integer.MIN_VALUE;
            int m = matrix.length, n = matrix[0].length;
            for (int i = 0; i < m; ++i) { // 枚举上边界
                int[] sum = new int[n];
                for (int j = i; j < m; ++j) { // 枚举下边界
                    for (int c = 0; c < n; ++c) {
                        sum[c] += matrix[j][c]; // 更新每列的元素和
                    }
                    TreeSet<Integer> sumSet = new TreeSet<Integer>();
                    sumSet.add(0);
                    int s = 0;
                    for (int v : sum) {
                        s += v;
                        // 获取大于指定值的最小键值，也就是获取矩形左边界的最小和，具体看解释，ceil满足ceil》=s-k，ceil越小，所求区域和越靠近k值
                        // 通过公式sr-sl<=k推到而来
                        Integer ceil = sumSet.ceiling(s - k);
                        if (ceil != null) {
                            ans = Math.max(ans, s - ceil);
                        }
                        sumSet.add(s);
                    }
                }
            }
            return ans;
        }
    }

}
