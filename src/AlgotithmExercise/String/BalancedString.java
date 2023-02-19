package AlgotithmExercise.String;

import java.util.HashMap;
import java.util.Map;

public class BalancedString {

    public int balancedString(String s) {
        Map<Character, Integer> statistcs = new HashMap<>();
        char[] schars = s.toCharArray();

        for (int i = 0; i < schars.length; i++) {
            statistcs.put(schars[i], statistcs.getOrDefault(schars[i], 0) + 1) ;
        }

        int balanceCount = schars.length / 4;
        for(char key: statistcs.keySet()){
            int sub = statistcs.get(key) -  balanceCount;
            if(sub>=0){
                statistcs.put(key, sub);
            } else {
                statistcs.put(key, 0);
            }
        }
        Map<Character, Integer> subStringStatistic = new HashMap<>();
        if(checkIfSuitable(statistcs, subStringStatistic)){
            return 0;
        }
        int changeCount = Integer.MAX_VALUE;
        for (int start = 0, end = 0; end < schars.length; end ++) {
            subStringStatistic.put(schars[end], subStringStatistic.getOrDefault(schars[end], 0) + 1);
            while (checkIfSuitable(statistcs, subStringStatistic)){
                changeCount = Math.min(end - start + 1, changeCount);
                char startVal = schars[start];
                if(subStringStatistic.getOrDefault(startVal, 0) == 0) {
                    subStringStatistic.put(startVal, subStringStatistic.getOrDefault(startVal, 0));
                } else {
                    subStringStatistic.put(startVal, subStringStatistic.get(startVal) - 1);
                }
                start ++;
            }
        }
        return changeCount;
    }

    public boolean checkIfSuitable(Map<Character, Integer> statistcs, Map<Character, Integer> subStringStatistics) {
        char Q = 'Q', W = 'W', E = 'E', R = 'R';
        return statistcs.getOrDefault(Q, 0) <= subStringStatistics.getOrDefault(Q, 0)
                && statistcs.getOrDefault(W, 0) <= subStringStatistics.getOrDefault(W, 0)
                && statistcs.getOrDefault(E, 0) <= subStringStatistics.getOrDefault(E, 0)
                && statistcs.getOrDefault(R, 0) <= subStringStatistics.getOrDefault(R, 0);
    }

    public static void main(String[] args) {
        BalancedString balancedString = new BalancedString();
        System.out.println(balancedString.balancedString("QQQW"));
    }

}
