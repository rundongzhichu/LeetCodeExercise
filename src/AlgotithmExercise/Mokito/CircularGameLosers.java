package AlgotithmExercise.Mokito;

public class CircularGameLosers {

    public int[] circularGameLosers(int n, int k) {
        int[] friends = new int[n];
        int round = 0;
        int cout=0;
        for (int i = 0; friends[i] == 0; i = (round*k + i)%n) {
            friends[i] ++;
            round++;
            cout++;
        }

        int[] res = new int[n-cout];
        int ri = 0;
        for (int i = 0; i < n; i++) {
            if(friends[i] == 0) {
                res[ri] = i+1;
                ri++;
            }
        }
        return res;
    }

}
