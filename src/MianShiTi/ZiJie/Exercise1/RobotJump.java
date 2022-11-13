package MianShiTi.ZiJie.Exercise1;

import java.util.Scanner;

public class RobotJump {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int E = 0;

        int N = Integer.valueOf(in.next());
        int[] buildings = new int[N];
        int min=Integer.MAX_VALUE, max = 0;
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.valueOf(in.next());
            min = Math.min(min, buildings[i]);
            max = Math.max(max, buildings[i]);
        }


        int res = 0;
        for (int i = 0; i < max; i++) {
            res = canJump(buildings, i);
            if(res > 0){
                break;
            }
        }
        System.out.println(res);
    }

    public static int canJump(int[] buildings, int E){
        for (int i = buildings.length-1; i >= 0 ; i--) {
            // ceil 函数将计算结果向上取整
            E = (E + buildings[i] + 1)/2;
            if(E<=0) return E;
        }
        return E;
    }

}
