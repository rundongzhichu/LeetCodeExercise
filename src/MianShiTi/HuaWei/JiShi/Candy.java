package MianShiTi.HuaWei.JiShi;

import java.util.Arrays;

public class Candy {

    // https://leetcode.cn/problems/candy/solutions/533150/fen-fa-tang-guo-by-leetcode-solution-f01p/ 解答真不错
    // 左规则右规则两次遍历， 先从左往右把升序序列该有的糖果数记录好，然后把反向遍历，确定降序序列该有的糖果数
    class Solution {
        public int candy(int[] ratings) {
            int n = ratings.length;
            int[] left = new int[n];
            for (int i = 0; i < n; i++) {
                if (i > 0 && ratings[i] > ratings[i - 1]) {
                    left[i] = left[i - 1] + 1;
                } else {
                    left[i] = 1;
                }
            }
            int right = 0, ret = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                    right++;
                } else {
                    right = 1;
                }
                ret += Math.max(left[i], right);
            }
            return ret;
        }
    }

    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];

        for (int i = 0; i < candies.length; i++) {
            int preIndex = i-1 >=0 ? i-1:i;
            int subIndex = i+1 <candies.length? i+1:i;

            if(ratings[i] > ratings[preIndex]) {
                candies[i] = candies[preIndex] +1;
                continue;
            }
            if(ratings[i] > ratings[subIndex]) {
                candies[i] = 2;
                int cur = i;
                int curPre = cur-1>=0? cur-1:cur;
                while (cur>=0 && ratings[curPre] > ratings[cur] && candies[curPre] <= candies[cur]) {
                    if(curPre == cur) break;
                    candies[curPre] = candies[cur] + 1;
                    cur--;
                    curPre = cur-1>=0? cur-1:cur;
                }
                continue;
            }
            candies[i] = 1;
        }
        return Arrays.stream(candies).sum();
    }

}
