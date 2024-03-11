package AlgotithmExercise;


import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Main {

    private Semaphore aSem = new Semaphore(1);
    private Semaphore bSem = new Semaphore(0);
    private Semaphore cSem = new Semaphore(0);

    public static void main(String[] args) {
        new Main().test();
    }

    public void test() {

    }


    public String capitalizeTitle(String title) {
        String[] words = title.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word :
                words) {
            int len = word.length();
            if(len == 1 || len == 2) {
                sb.append(" ").append(word.toLowerCase());
            } else {
                sb.append(" ").append(Character.toUpperCase(word.charAt(0))).append(word.substring(1, len).toLowerCase());
            }
        }
        return sb.toString().trim();
    }

}


