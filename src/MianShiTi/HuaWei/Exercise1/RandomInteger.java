package MianShiTi.HuaWei.Exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RandomInteger {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        int n = Integer.valueOf(br.readLine());
        int[] res = new int[501];
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            res[Integer.valueOf(str)] = 1;
        }

        for (int i = 0; i < 501; i++) {
            if(res[i] == 1){
                System.out.println(i);
            }
        }
    }
}
