package AlgotithmExercise.Math;

import java.util.Arrays;

public class ReverseNumber {
    public static int reverse(int x) {

        int remain = 0;
        int res = 0;

        while (x != 0){
            if((res>=0 && res <=  Integer.MAX_VALUE / 10) || (res<=0 && res >= Integer.MIN_VALUE / 10)) {
                remain = x%10;
                x = x/10;
                res = res*10 + remain;
                System.out.println("res = " + res);
            } else {
                return 0;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println("args = " + String.valueOf(reverse(1463847412)));
    }

}
