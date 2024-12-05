package AlgotithmExercise;


import AlgotithmExercise.BinaryTree.TreeNode;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        new Main().test();
    }

    public void test() {
        int[] nums = new int[] {1,2,3,4,3,2,1};
        int[] idxs = twoSum(nums, 4);
        System.out.println("idxs[0] = " + idxs[0] + " , idxs[1] = " + idxs[1]);

        idxs = twoSum1(nums, 4);
        System.out.println("idxs[0] = " + idxs[0] + " , idxs[1] = " + idxs[1]);
    }

    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        for (int i=0;i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int[] twoSum1(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, List<Integer>> statistics = new HashMap<>();

        for (int i = 0; i < len; i++) {
            List<Integer> lis = statistics.getOrDefault(nums[i], new ArrayList<>());
            lis.add(i);
            statistics.put(nums[i], lis);
        }

        for (int i=0;i < len; i++) {
            int remain = target - nums[i];
            if(statistics.containsKey(remain)) {
                for (Integer idx :
                        statistics.get(remain)) {
                    if(idx != i) {
                        return new int[]{i, idx};
                    }
                }
            }
        }
        return null;
    }

}


