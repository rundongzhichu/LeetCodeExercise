package Array;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }

        int major = 0;
        int countMax = 0;
        for(int num: map.keySet()){
            if(map.get(num)>countMax){
                countMax = map.get(num);
                major = num;
            }
        }
        return major;
    }
}
