package MianShiTi.HuaWei.JiShi;

public class MaxArea {

    public int maxArea(int[] height) {
        int maxVol = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = 0; j < height.length; j++) {
                maxVol = Math.max(maxVol, (height[j] > height[i]? height[i] : height[j])*(j-i+1) );
            }
        }
        return maxVol;
    }

    class Solution {
        public int maxArea(int[] height) {
            int i = 0, j = height.length - 1, res = 0;
            while(i < j) {
                res = height[i] < height[j] ?
                        Math.max(res, (j - i) * height[i++]):
                        Math.max(res, (j - i) * height[j--]);
            }
            return res;
        }
    }
}
