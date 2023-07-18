package AlgotithmExercise;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private Map<Character, Character> shiftMap = new HashMap<>();

    public static void main(String[] args) {
        String s = "aaa";
        int[] shifts = new int[]{1,2,3};
        System.out.println(shiftingLetters(s, shifts));
    }



}
