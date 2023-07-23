package AlgotithmExercise.DynamicProgramming;

import java.util.Deque;
import java.util.LinkedList;

public class Trap {

    /*
        https://leetcode.cn/problems/trapping-rain-water/solutions/9112/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
        多种解法：按照水洼高度一行行求 动态规划 双指针 栈
     */
    public  int trap(int[] height) {
        Deque<Integer> stack = new LinkedList();

        int res = 0;
        for (int i = 0; i < height.length; i++) {
            if(stack.isEmpty() || height[stack.peek()] >= height[i]){
                stack.push(i);
                continue;
            }
            int rightHeightIndex = i;
            int bottomIndex = stack.pop();
            while (!stack.isEmpty()) {
                if(height[stack.peek()] <= height[rightHeightIndex]) {
                    res = res + (height[stack.peek()] - height[bottomIndex]) * (rightHeightIndex - stack.peek()-1);
                    bottomIndex = stack.pop();
                } else {
                    res = res + (height[rightHeightIndex] - height[bottomIndex]) * (rightHeightIndex - stack.peek()-1);
                    if(i+1<height.length ){
                        if( height[i+1] > height[rightHeightIndex]){
                            bottomIndex = rightHeightIndex;
                            rightHeightIndex = ++i;
                        } else {
                            break;
                        }
                    } else break;
                }
            }

            if (rightHeightIndex<height.length) stack.push(rightHeightIndex);
        }
        return res;
    }
}
