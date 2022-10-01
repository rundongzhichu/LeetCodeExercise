package AlgotithmExercise.BinarySearch;

public class PreimageSizeFZF {

    public int preimageSizeFZF(int k) {

        int twoCount=0;
        int fiveCount=0;

        int x=0;
        while (Math.min(twoCount,fiveCount)<k){
            twoCount += count(x,2);
            fiveCount += count(x,5);
            x++;
        }

        int res=0;
        while (true){
            twoCount += count(x,2);
            fiveCount += count(x,5);
            System.out.println("twoCount = " + twoCount);
            System.out.println("fiveCount = " + fiveCount);
            System.out.println("x = " + x);

            if(Math.min(twoCount,fiveCount)==k) {
                res++;
                x++;
            }
            else break;
        }
        return res;
    }

    public int count(int x, int divisor){
        int res = 0;
        while (x%divisor==0){
            if(x==0) break;
            res++;
            x%=divisor;
        }
        return  res;
    }

}
