package AlgotithmExercise.BinarySearch;

import java.util.Arrays;

public class EarliestSecondToMarkIndices {

    /*
    递归的解法超时了
     */
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int res = earliestSecondToMarkIndices(nums, changeIndices, 1,changeIndices.length);
        if(res == Integer.MAX_VALUE) {
            return -1;
        }
        return res;
    }

    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices, int curSecond, int ddlSecond) {
        if(curSecond > ddlSecond) {
            return Integer.MAX_VALUE;
        }
        int res = Integer.MAX_VALUE;
        if(nums[changeIndices[curSecond - 1] - 1] == 0) {
            nums[changeIndices[curSecond - 1] - 1] = -1;
            if(allMarked(nums)) {
                nums[changeIndices[curSecond - 1] - 1] = 0;
                return curSecond;
            }
            res = Math.min(earliestSecondToMarkIndices(nums, changeIndices, curSecond + 1, ddlSecond), res);
            nums[changeIndices[curSecond - 1] - 1] = 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0 ) {
                nums[i]--;
                res = Math.min(earliestSecondToMarkIndices(nums, changeIndices, curSecond + 1, ddlSecond), res);
                nums[i]++;
            }
        }
        return Math.min(earliestSecondToMarkIndices(nums, changeIndices, curSecond + 1, ddlSecond), res);
    }

    public boolean allMarked(int[] nums) {
        for (int num :
                nums) {
            if(num != -1)
                return  false;
        }
        return true;
    }


    /**
     * 链接：https://leetcode.cn/problems/earliest-second-to-mark-indices-i/solutions/2653101/er-fen-da-an-pythonjavacgo-by-endlessche-or61/
        todo: 理解这个利用二分思想的解法
     */
    class Solution {
        public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
            int n = nums.length;
            int m = changeIndices.length;
            if (n > m) {
                return -1;
            }

            int[] lastT = new int[n];
            int left = n - 1, right = m + 1;
            while (left + 1 < right) {
                int mid = (left + right) / 2;
                if (check(nums, changeIndices, lastT, mid)) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            return right > m ? -1 : right;
        }

        private boolean check(int[] nums, int[] changeIndices, int[] lastT, int mx) {
            Arrays.fill(lastT, -1);
            for (int t = 0; t < mx; t++) {
                lastT[changeIndices[t] - 1] = t;
            }
            for (int t : lastT) {
                if (t < 0) { // 有课程没有考试时间
                    return false;
                }
            }

            int cnt = 0;
            for (int i = 0; i < mx; i++) {
                int idx = changeIndices[i] - 1;
                if (i == lastT[idx]) { // 考试
                    if (nums[idx] > cnt) { // 没时间复习
                        return false;
                    }
                    cnt -= nums[idx]; // 复习这门课程
                } else {
                    cnt++; // 留着后面用
                }
            }
            return true;
        }
    }

}
