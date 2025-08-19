import java.util.*;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        Integer v1 = 2;
        Integer v2 = 2;
        System.out.println(v1.equals(v2));

    }




    public int closestSum(int[] nums, int target) {
        Executors.newCachedThreadPool();
        Arrays.sort(nums);

        int start=0, end = nums.length-1;
        int distance = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length -1; i++) {
            while (start < i && end > i){
                int sum = nums[start] + nums[i] + nums[end];
                int diff = target - sum;

                distance = Math.min(Math.abs(diff), distance);
                if(diff == 0) return 0;
                else if(diff > 0) start ++;
                else if(diff < 0) end --;
            }
        }
        return distance;
    }

//    public static boolean isPalindrome(String s) {
//        s = s.toLowerCase();
//        Deque<Character> queue = new LinkedList<>();
//        int size = s.length();
//
//        for (int i = 0; i < size; i++) {
//            char c = s.charAt(i);
//            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
//                queue.offer(s.charAt(i));
//            }
//        }
//        if(queue.isEmpty()) return true;
//
//        while (!queue.isEmpty() && queue.size() > 1) {
//            if(!queue.peekFirst().equals(queue.peekLast())) {
//                return false;
//            }
//            queue.pollLast();
//            queue.pollFirst();
//        }
//
//        if(queue.size() <= 1) return true;
//        return  false;
//    }

}
