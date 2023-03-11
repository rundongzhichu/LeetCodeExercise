package AlgotithmExercise.Array;

import java.util.*;

public class ThreeSum {

    /**
     * 关键思想在于排序
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<String> record = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = 0; j < nums.length -1; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if(nums[i] + nums[j] + nums[k] == 0 && !record.contains("" + nums[i] + nums[j] + nums[k])){
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        record.add("" + nums[i] + nums[j] + nums[k]);
                        record.add("" + nums[i] + nums[k] + nums[j]);
                        record.add("" + nums[j] + nums[i] + nums[k]);
                        record.add("" + nums[j] + nums[k] + nums[i]);
                        record.add("" + nums[k] + nums[i] + nums[j]);
                        record.add("" + nums[k] + nums[j] + nums[i]);
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSumOfficialSolution(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //双指针
        int len = nums.length;
        for(int i = 0;i < len;++i) {
            if(nums[i] > 0) return lists;

            if(i > 0 && nums[i] == nums[i-1]) continue;

            int curr = nums[i];
            int L = i+1, R = len-1;
            while (L < R) {
                int tmp = curr + nums[L] + nums[R];
                if(tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    while(L < R && nums[L+1] == nums[L]) ++L;
                    while (L < R && nums[R-1] == nums[R]) --R;
                    ++L;
                    --R;
                } else if(tmp < 0) {
                    ++L;
                } else {
                    --R;
                }
            }
        }
        return lists;
    }

}
