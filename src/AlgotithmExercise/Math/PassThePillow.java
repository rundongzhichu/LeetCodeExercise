package AlgotithmExercise.Math;

public class PassThePillow {
    public int passThePillow(int n, int time) {
        int divide = time/(n-1);
        int remain = time%(n-1);
        if(divide % 2 == 0 ) {
            return remain + 1;
        } else {
            return n - remain;
        }
    }

}
