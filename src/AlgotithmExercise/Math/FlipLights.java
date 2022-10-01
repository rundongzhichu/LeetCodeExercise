package AlgotithmExercise.Math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class FlipLights {

    int[] lights;
    Function<Integer, Integer>[] fucs = new Function[4];
    Set<String> recor = new HashSet<>();

    public int flipLights(int n, int presses) {
        fucs[0] = (i)->++i;
        fucs[1] = (i)->2*i +2;
        fucs[2] = (i)->2*i+1;
        fucs[3] = (i)->3*i +1;
        lights = new int[n+1];

        flipLights(presses);
        return recor.size();
    }


    public void flipLights(int press) {
        if(press == 0){
            StringBuilder sb = new StringBuilder();
            Arrays.stream(lights).forEach((li)-> {
                sb.append(li);
            });
            recor.add(sb.toString());
            return;
        }

        for(int i=0; i<4; i++){
            switchLightState(0, fucs[i]);
            flipLights(press-1);
        }
    }

    public void switchLightState(int start, Function<Integer, Integer> fuc){
        for (int i = fuc.apply(start); i < lights.length;) {
            lights[i] ^= 1;
            System.out.println("i = " + i);
            i = fuc.apply(i);
        }
    }

    public static void main(String[] args) {
//        Function<Integer, Integer>[] fucs = new Function[4];
//        fucs[0] = (i)->i+1;
//        fucs[1] = (i)->2*i +2;
//        fucs[2] = (i)->2*i+1;
//        fucs[3] = (i)->3*i +1;

        System.out.println("fucs = " + new FlipLights().flipLights(1,1));
    }


    class Solution {
        public int flipLights(int n, int presses) {
            Set<Integer> seen = new HashSet<Integer>();
            for (int i = 0; i < 1 << 4; i++) {
                int[] pressArr = new int[4];
                for (int j = 0; j < 4; j++) {
                    pressArr[j] = (i >> j) & 1;
                }
                int sum = Arrays.stream(pressArr).sum();
                if (sum % 2 == presses % 2 && sum <= presses) {
                    int status = pressArr[0] ^ pressArr[1] ^ pressArr[3];
                    if (n >= 2) {
                        status |= (pressArr[0] ^ pressArr[1]) << 1;
                    }
                    if (n >= 3) {
                        status |= (pressArr[0] ^ pressArr[2]) << 2;
                    }
                    if (n >= 4) {
                        status |= (pressArr[0] ^ pressArr[1] ^ pressArr[3]) << 3;
                    }
                    seen.add(status);
                }
            }
            return seen.size();
        }
    }

}
