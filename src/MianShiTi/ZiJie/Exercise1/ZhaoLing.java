package MianShiTi.ZiJie.Exercise1;

import java.util.Arrays;
import java.util.Scanner;

public class ZhaoLing {

    private static int res = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int cost = Integer.valueOf(in.next());

        int remain = 1024 - cost;
        cal(cal(cal(cal(remain, 64),16),4),1);
        System.out.println(res);
    }

    public static int cal(int remain, int coin){
        res += remain / coin;
        remain %= coin;
        return remain;
    }
}
