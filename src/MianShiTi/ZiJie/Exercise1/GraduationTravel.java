package MianShiTi.ZiJie.Exercise1;

import java.util.Scanner;

public class GraduationTravel {

    private static int minFee = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.valueOf(in.next());
        int[][] fees = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fees[i][j] = Integer.valueOf(in.next());
            }
        }

        int[] cities = new int[n];
        cities[0] = 1;
        minFees(fees, cities, 0, 0, 1);
        System.out.println(minFee);
    }

    public static void minFees(int[][] fees, int[] cities, int curFee, int preCity, int count) {
        // 剪枝操作
        if(curFee+fees[preCity][0] > minFee) return;

        if (count == cities.length) {
            minFee = Math.min(minFee, curFee+fees[preCity][0]);
            return;
        }

        for (int i = 0; i < cities.length; i++) {
            if (cities[i] == 0) {
                cities[i] = 1;
                minFees(fees, cities, curFee + fees[preCity][i], i, count + 1);
                cities[i] = 0;
            }
        }
    }
}
