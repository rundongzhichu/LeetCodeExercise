package AlgotithmExercise.String;

import java.util.Arrays;

public class GetHint {

    public String getHint(String secret, String guess) {
        char[] sArr = secret.toCharArray();
        char[] gArr = guess.toCharArray();

        int a = 0;
        for (int i = 0; i < sArr.length; i++) {
            if(sArr[i] - gArr[i] == 0) a++;
        }

        Arrays.sort(sArr);
        Arrays.sort(gArr);
        int b=0;
        System.out.println("new String(sArr) = " + new String(sArr));
        for (int i = 0, j=0; i < sArr.length && j < sArr.length;) {
            if(sArr[i] == gArr[j]) {
                b++;
                i++;
                j++;
            }
            else if(sArr[i] < gArr[j]) i++;
            else j++;
        }
        return a+"A"+(b-a)+"B";
    }

    /*

    链接：https://leetcode.cn/problems/bulls-and-cows/solutions/1088724/cai-shu-zi-you-xi-by-leetcode-solution-q9lz/

    核心关键：每个字符都在0-9之间，只需要关注十个字符对应的数量就可以了。

     */
    class Solution {
        public String getHint(String secret, String guess) {
            int bulls = 0;
            int[] cntS = new int[10];
            int[] cntG = new int[10];
            for (int i = 0; i < secret.length(); ++i) {
                if (secret.charAt(i) == guess.charAt(i)) {
                    ++bulls;
                } else {
                    ++cntS[secret.charAt(i) - '0'];
                    ++cntG[guess.charAt(i) - '0'];
                }
            }
            int cows = 0;
            for (int i = 0; i < 10; ++i) {
                cows += Math.min(cntS[i], cntG[i]);
            }
            return Integer.toString(bulls) + "A" + Integer.toString(cows) + "B";
        }
    }




}
