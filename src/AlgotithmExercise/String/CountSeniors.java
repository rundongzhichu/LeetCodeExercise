package AlgotithmExercise.String;

public class CountSeniors {

    public int countSeniors(String[] details) {
        int res = 0;
        for (String detail :
                details) {
            int size = detail.length();
            int age = Integer.valueOf(detail.substring(size - 4 , size - 2));
            if (age > 60) res++;
        }
        return res;
    }

}
