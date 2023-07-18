package AlgotithmExercise.String;

public class ShiftString {

    public static String shiftingLetters(String s, int[] shifts) {

        for (int i = 0; i < shifts.length; i++) {
            shifts[i] = shifts[i] % 26;
        }
        int sum = 0;
        for (int shift :
                shifts) {
            sum += shift % 26;
        }

        StringBuffer sb = new StringBuffer();
        int shiftForCurChar = sum;
        for (int i = 0; i < shifts.length; i++) {
            if(i>0) {
                shiftForCurChar = sum - shifts[i-1];
                sum -= shifts[i-1];
            }
            sb.append((char)(('a' + ((s.charAt(i) % 'a' + shiftForCurChar) % 26)%26)));
        }
        return sb.toString();
    }

    /**
     C++解答，不需要求和，只需要从shift数组，从后往前遍历一遍即可

    string shiftingLetters(string S, vector<int>& shifts) {
        int size = (int)S.size();
        int cnt = 0;
        for (int i = size - 1; i >= 0;i--) {
            cnt += shifts[i] % 26;
            int index = S[i] - 'a' + cnt;
            S[i] = (index % 26) + 'a';
        }
        return S;
    }
     */
}

