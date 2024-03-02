package AlgotithmExercise.Array;

public class IsPossibleToSplit {

    // https://leetcode.cn/problems/split-the-array/submissions/505390445/
    public boolean isPossibleToSplit(int[] nums) {
        int[] statisticArr = new int[101];

        for (int i = 0; i < nums.length; i++) {
            statisticArr[nums[i]]++;
            if(statisticArr[nums[i]] > 2) return false;
        }
        return true;
    }

}
