package MianShiTi.XingHuan.Exercise1;

import java.util.Scanner;

public class SuperJump {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int p = Integer.valueOf(in.next());

        int[] liquid = new int[p];
        int index =0;
        while (in.hasNext()) {
            liquid[index] = Integer.valueOf(in.next());
            index++;
        }

        int height = 0, maxJump = 0;
        int start = 0;
        for (int i = 1; i <= p; i++) {
            for (int j = start; j < p - 1; j++) {
                if (i % 2 == 0) {
                    if(liquid[j+1] <= liquid[j]){
                        start = j + 1;
                    } else {
                        break;
                    }
                } else {
                    if(liquid[j+1] >= liquid[j]){
                        start = j + 1;
                    } else {
                        break;
                    }
                }
            }


            if(start>=p) break;
            if (i % 2 == 0) {
                height -= liquid[start];
            } else {
                height += liquid[start];
            }
            start++;
            maxJump = Math.max(maxJump, height);
        }
        System.out.println(maxJump);
    }

}
