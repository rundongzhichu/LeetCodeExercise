package AlgotithmExercise.tanxin;

public class MovesToMakeZigzag {

    /**
     * 核心思想：
     * 分两种情况处理：
     * 1. 计算数组中每个偶数位置都大于两边的奇数位置所需要的变动
     * 2. 计算数组中每个技术位置都大于两边偶数位置所需要的变动
     * 3. 上述两者的值取最小
     *
     * @param nums
     * @return
     */
    public int movesToMakeZigzag(int[] nums) {
        return Math.min(help(nums, 0), help(nums, 1));
    }

    public int help(int[] nums, int pos) {
        int res = 0;
        for (int i = pos; i < nums.length; i += 2) {
            int a = 0;
            if (i - 1 >= 0) {
                a = Math.max(a, nums[i] - nums[i - 1] + 1);
            }
            if (i + 1 < nums.length) {
                a = Math.max(a, nums[i] - nums[i + 1] + 1);
            }
            res += a;
        }
        return res;
    }

}
