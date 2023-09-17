package AlgotithmExercise.DynamicProgramming;

public class Rob {
    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];

        int maxMoney = 0;
        int[] moneys = new int[len];
        moneys[0] = nums[0];
        moneys[1] =  Math.max(moneys[0], nums[1]);
        maxMoney =Math.max(moneys[0], moneys[1]);
        for (int i = 2; i < len - 1; i++) {
            moneys[i] = Math.max(nums[i] + moneys[i-2], moneys[i-1]);
            maxMoney = Math.max(moneys[i], maxMoney);
        }

        moneys = new int[len];
        moneys[len -1] = nums[len -1];
        moneys[len - 2] = nums[len -1];
        maxMoney = Math.max(moneys[len -1], maxMoney);
        for (int i = len - 3; i > 0; i--) {
            moneys[i] = Math.max(nums[i] + moneys[i+2], moneys[i+1]);
            maxMoney = Math.max(moneys[i], maxMoney);
        }
        return maxMoney;
    }
}

/**
 *
 * 假设数组 nums 的长度为 nnn。如果不偷窃最后一间房屋，则偷窃房屋的下标范围是 [0,n−2]；
 * 如果不偷窃第一间房屋，则偷窃房屋的下标范围是 [1,n−1]。在确定偷窃房屋的下标范围之后，即可用第 198 题的方法解决。
 * 对于两段下标范围分别计算可以偷窃到的最高总金额，其中的最大值即为在 nnn 间房屋中可以偷窃到的最高总金额。
 *
 *
 * 假设偷窃房屋的下标范围是 [start,end]
 * ，用 dp[i]表示在下标范围 [start,i] 内可以偷窃到的最高总金额，那么就有如下的状态转移方程：
 *
 * dp[i]=max(dp[i−2]+nums[i],dp[i−1])
 *
 * 作者：力扣官方题解
 * 链接：https://leetcode.cn/problems/house-robber-ii/solutions/722767/da-jia-jie-she-ii-by-leetcode-solution-bwja/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 * 链接：https://leetcode.cn/problems/house-robber-ii/solutions/722767/da-jia-jie-she-ii-by-leetcode-solution-bwja/
 *
 */

class Solution1 {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}

