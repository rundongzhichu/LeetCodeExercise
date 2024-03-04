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
     nums[i] 我们类比到第i门课程需要nums[i]天才能完成，不包括考试这一天
     题意有点抽象，形象地解释一下：
     你有 n 门课程需要考试，第 i 门课程需要用 nums[i] 天复习。同一天只能复习一门课程。
     在第 i 天，你可以选择参加第 changeIndices[i]门课程的考试。考试这一天不能复习。
     搞定所有课程的复习+考试，至少要多少天？

     */
    class Solution {
        public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
            int n = nums.length;
            int m = changeIndices.length;
            if (n > m) {
                return -1;
            }

            int[] lastT = new int[n];

            // 为什么left从n-1位置开始，因为就算nums中所有课程准备时间都为0，那么还是至少需要n天才能完成考试
            int left = n - 1, right = m + 1;
            while (left + 1 < right) {
                // 这里的left right 和mid都对应到的是changeIndices中的index，表示第index天我们可以对changeIndices[i]门课程进行学习或者考试
                // 二分操作要有有序性，这里的有序性就是index指数本身是有序的，天数是递增的
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
            // lastT 记录的是nums数组的每个index在changeIndices数组中最后出现的位置
            for (int t = 0; t < mx; t++) {
                lastT[changeIndices[t] - 1] = t;
            }
            for (int t : lastT) {
                if (t < 0) { // 有课程没有考试时间
                    return false;
                }
            }
            
            // 这里只要计算出 在计算到lastT[idx]对应的changeIndices的位置的时候尝试去完成这门课程的考试，如果之前累积的空闲时间cnt不能
            // 小于完成课程需要的事件nums[idx], 则在mx天数限定下无法完成所有的课程
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
