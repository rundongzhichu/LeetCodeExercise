package Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CanBeEqual {
    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> nums = new HashMap<>();

        for(int num: target){
            nums.put(num, nums.getOrDefault(num,0)+1);
        }

        for(int num: arr){
            if(!nums.containsKey(num)) return false;
            nums.put(num,nums.get(num)-1);
            if(nums.get(num)==0) nums.remove(num);
        }
        return nums.size()==0;
    }
}
