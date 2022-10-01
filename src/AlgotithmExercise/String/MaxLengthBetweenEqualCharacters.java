package AlgotithmExercise.String;

public class MaxLengthBetweenEqualCharacters {

    public int maxLengthBetweenEqualCharacters(String s) {
        char[] sChars = s.toCharArray();

        int res = -1;
        for (int i = 0; i < sChars.length -1; i++) {
            for (int j = i+1; j < sChars.length ; j++) {
                if(sChars[i]==sChars[j]){
                    res = Math.max(res, j-i-1);
                }
            }
        }
        return res;
    }

}
