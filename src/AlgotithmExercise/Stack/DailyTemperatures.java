package AlgotithmExercise.Stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new LinkedList<>();
        int tLen = temperatures.length;
        int[] answers = new int[tLen];
        stack.push(0);
        for (int i = 0; i < tLen; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int idx = stack.pop();
                answers[idx] = i - idx;
            }
            stack.push(i);
        }
        return answers;
    }

    /**
     *
     * 链接：https://leetcode.cn/problems/daily-temperatures/solutions/283196/mei-ri-wen-du-by-leetcode-solution/
     *
     * 核心思路：我的单调栈的解法是最优的， 下面是暴力解法
     *
     */
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int length = temperatures.length;
            int[] ans = new int[length];
            int[] next = new int[101];
            Arrays.fill(next, Integer.MAX_VALUE);
            for (int i = length - 1; i >= 0; --i) {
                int warmerIndex = Integer.MAX_VALUE;
                for (int t = temperatures[i] + 1; t <= 100; ++t) {
                    if (next[t] < warmerIndex) {
                        warmerIndex = next[t];
                    }
                }
                if (warmerIndex < Integer.MAX_VALUE) {
                    ans[i] = warmerIndex - i;
                }
                next[temperatures[i]] = i;
            }
            return ans;
        }
    }

}
