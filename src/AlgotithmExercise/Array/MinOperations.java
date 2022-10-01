package AlgotithmExercise.Array;

public class MinOperations {

    public int minOperations(String[] logs) {
        int cur=0;
        for(String ope: logs){
            if(ope.equals("../")){
                if(cur==0) continue;
                cur--;
                continue;
            }else if(ope.equals("./"))continue;
            cur++;
        }
        return cur;
    }
}
