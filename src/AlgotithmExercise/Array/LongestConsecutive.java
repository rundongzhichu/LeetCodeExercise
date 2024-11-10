package AlgotithmExercise.Array;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int maxConsecCount = 1;
        // 这里有一个错误之处在于，没有排除掉已经处理过的子序列
        for (int i : set) {
            int consecCount = 0;
            while (set.contains(i)) {
                i++;
                consecCount++;
            }
            maxConsecCount =  Integer.max(maxConsecCount, consecCount);
        }
        return maxConsecCount;
    }

    /**
     *
     * 核心思想：采用hashSet保存数据，然后判断i, i+1, i+2,......,i+n 是否存在，计算连续子序列长度
     * 链接：https://leetcode.cn/problems/longest-consecutive-sequence/solutions/276931/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
     *
     *
     */
    class Solution {
        public int longestConsecutive(int[] nums) {
            Set<Integer> num_set = new HashSet<Integer>();
            for (int num : nums) {
                num_set.add(num);
            }

            int longestStreak = 0;

            for (int num : num_set) {
                // 这里是一个关键处理，防止处理之前已经扫描过的序列的子序列。
                if (!num_set.contains(num - 1)) {
                    int currentNum = num;
                    int currentStreak = 1;

                    while (num_set.contains(currentNum + 1)) {
                        currentNum += 1;
                        currentStreak += 1;
                    }

                    longestStreak = Math.max(longestStreak, currentStreak);
                }
            }

            return longestStreak;
        }
    }

}
