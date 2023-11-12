package AlgotithmExercise.String;

public class FindTheLongestBalancedSubstring {

    public int findTheLongestBalancedSubstring(String s) {
        char[] sarr = s.toCharArray();
        int len = 0;
        int maxLen = 0;


        for (int i = sarr.length - 1; i >= 0; ) {
            int temp = 0;
            int tempCount1 = 0;
            int tempCount0 = 0;
            while (i>=0 && sarr[i] == '1') {
                temp++;
                tempCount1++;
                i--;
            }
            while (i>=0 && sarr[i] == '0') {
                temp++;
                tempCount0++;
                i--;
            }
            if(tempCount0 >= 1 && tempCount1 >= 1) {
                if(tempCount0 > tempCount1)
                    maxLen = Math.max(maxLen, 2*tempCount1);
                else
                    maxLen = Math.max(maxLen, 2*tempCount0);
            }
        }
        return maxLen;
    }

    /**
     *
     * https://leetcode.cn/problems/find-the-longest-balanced-substring-of-a-binary-string/
     *
     */
    class Solution {
        public int findTheLongestBalancedSubstring(String s) {
            int res = 0;
            int n = s.length();
            int[] count = new int[2];
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    count[1]++;
                    res = Math.max(res, 2 * Math.min(count[0], count[1]));
                } else if (i == 0 || s.charAt(i - 1) == '1') {
                    count[0] = 1;
                    count[1] = 0;
                } else {
                    count[0]++;
                }
            }
            return res;
        }
    }

}
