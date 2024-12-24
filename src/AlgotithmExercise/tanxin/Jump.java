package AlgotithmExercise.tanxin;

public class Jump {

    public int jump(int[] nums) {
        return jump(nums, 0, nums[0], 0);
    }

    public int jump(int[] nums, int idx, int step, int count) {
        if(idx >= nums.length - 1) {
            return count;
        }

        int minCnt = Integer.MAX_VALUE;
        if(step >0) {
            for (int i = 1; i <= step; i++) {
                if(i + idx < nums.length) {
                    minCnt = Math.min(minCnt, jump(nums, i + idx, nums[i + idx], count + 1));
                }
            }
        }
        return minCnt;
    }

    /**
     *
     * 链接：https://leetcode.cn/problems/jump-game-ii/solutions/230241/tiao-yue-you-xi-ii-by-leetcode-solution/
     * 核心思想：我们的目标是到达数组的最后一个位置，因此我们可以考虑最后一步跳跃前所在的位置，该位置通过跳跃能够到达最后一个位置。
     *
     * 如果有多个位置通过跳跃都能够到达最后一个位置，那么我们应该如何进行选择呢？直观上来看，我们可以「贪心」地选择距离最后一个位置最远的那个位置，
     * 也就是对应下标最小的那个位置。因此，我们可以从左到右遍历数组，选择第一个满足要求的位置。
     *
     * 找到最后一步跳跃前所在的位置之后，我们继续贪心地寻找倒数第二步跳跃前所在的位置，以此类推，直到找到数组的开始位置。
     *
     *
     */
    class Solution {
        public int jump(int[] nums) {
            int position = nums.length - 1;
            int steps = 0;
            while (position > 0) {
                for (int i = 0; i < position; i++) {
                    if (i + nums[i] >= position) {
                        position = i;
                        steps++;
                        break;
                    }
                }
            }
            return steps;
        }
    }

}
