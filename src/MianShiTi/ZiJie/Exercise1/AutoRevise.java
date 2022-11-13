package MianShiTi.ZiJie.Exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * 主要分为三种状态处理：
 * 1：前面字符都不相同 即 A状态
 * 2： 前面两个字符相同，即AA状态
 * 3： 前面为 AAB的情况
 *
 * 然后每次处理记录好当前处理的字符，作为下次处理时的上一个字符记录，方便看是否重复
 *
 */
public class AutoRevise {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());

        for (int i = 0; i < n; i++) {
            char[] strChars = br.readLine().toCharArray();

            String res = "" + strChars[0];
            int state = 0;
            char last = strChars[0];
            for (int j = 1; j < strChars.length; j++) {
                switch (state){
                    case 0:
                        if(last == strChars[j]){
                            state = 1;
                        }else{
                            state = 0;
                        }
                        break;
                    case 1:
                        if(last == strChars[j]){
                            continue;
                        }else {
                            state = 2;
                        }
                        break;
                    case 2:
                        if(last == strChars[j]){
                            continue;
                        }else{
                            state = 0;
                        }
                        break;
                    default:
                        break;
                }
                last = strChars[j];
                res += last;

            }

            System.out.println(res);
        }
    }
}
