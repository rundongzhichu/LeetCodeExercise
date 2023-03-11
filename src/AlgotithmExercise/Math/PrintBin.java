package AlgotithmExercise.Math;

import java.util.PriorityQueue;

public class PrintBin {

    public String printBin(double num) {
        int count = 0;
        int cur = 0;
        String res = "0.";
        while (count<32 && num != 0.0) {
            System.out.println("num = " + num);
            num = 2*num;
            cur = (int) (num%2);
            res += cur;
            count++;
            num = num - cur;
        }
        if(count > 32) return "ERROR";
        return res;
    }

}
