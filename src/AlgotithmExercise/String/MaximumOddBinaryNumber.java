package AlgotithmExercise.String;

import java.util.Arrays;

public class MaximumOddBinaryNumber {

    /*

     */
    public String maximumOddBinaryNumber(String s) {
        char[] sArr = s.toCharArray();
        Character[] scArr = new Character[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            scArr[i] = sArr[i];
        }

        Arrays.sort(scArr, (c1, c2) -> c2 -c1);
        for (int i = 0; i < scArr.length; i++) {
            if(i+1 < sArr.length && scArr[i] == '1' && scArr[i+1] == '0') {
                char c = scArr[i];
                scArr[i] = scArr[sArr.length - 1];
                scArr[sArr.length - 1] = c;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scArr.length; i++) {
            sb.append(scArr[i]);
        }
        return sb.toString();
    }

    /*
    核心思想： 统计出1的个数
    https://leetcode.cn/problems/maximum-odd-binary-number/solutions/2679827/zui-da-de-er-jin-zhi-qi-shu-by-leetcode-1mi14/?envType=daily-question&envId=2024-03-13
     */
    class Solution {
        public String maximumOddBinaryNumber(String s) {
            int n = s.length();
            int count=0;
            for(int i=0;i<n;i++){
                //将数组里的任意一个1放到末尾
                //统计1的个数
                if(s.charAt(i)=='1'){
                    count++;
                }
            }
            // int []t=new int[n];
            // t[length-1]=1;
            StringBuilder sb = new StringBuilder();
            for(int i =0;i<n-1;i++){
                if(i<count-1){
                    sb.append("1");
                }else{
                    sb.append("0");

                }
            }
            sb.append("1");
            return sb.toString();
        }
    }

}
