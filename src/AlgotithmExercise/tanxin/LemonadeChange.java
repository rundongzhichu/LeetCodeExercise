package AlgotithmExercise.tanxin;

import java.util.HashMap;
import java.util.Map;

public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        int change = 0;
        Map<Integer, Integer> changes = new HashMap<>();
        changes.put(5,0);
        changes.put(10,0);
        changes.put(20,0);

        for (int i = 0; i < bills.length; i++) {
            if(bills[i] - 5 > change) return false;
            else {
                if(bills[i] == 10){
                    if(changes.get(5) > 0 ) {
                        changes.put(5, changes.get(5) - 1);
                        changes.put(10, changes.getOrDefault(10, 0) + 1);
                        change = change + 5;
                    } else {
                        return false;
                    }
                } else if(bills[i] == 20) {
                    if (changes.get(5) > 0 && changes.get(10) > 0) {
                        changes.put(10, changes.get(10) - 1);
                        changes.put(5, changes.get(5) - 1);
                        changes.put(20, changes.getOrDefault(20, 0) + 1);
                        change = change + 5;
                    } else if (changes.get(10) == 0 && changes.get(5) >= 3) {
                        changes.put(5, changes.get(5) - 3);
                        changes.put(20, changes.getOrDefault(20, 0) + 1);
                        change = change + 5;
                    } else {
                        return false;
                    }
                } else if (bills[i] == 5) {
                    changes.put(5, changes.getOrDefault(5, 0) + 1);
                    change = change + 5;
                }
            }
        }
        return true;
    }

    class Solution {
        public boolean lemonadeChange(int[] bills) {
            int five = 0, ten = 0;
            for (int bill : bills) {
                if (bill == 5) {
                    five++;
                } else if (bill == 10) {
                    if (five == 0) {
                        return false;
                    }
                    five--;
                    ten++;
                } else {
                    if (five > 0 && ten > 0) {
                        five--;
                        ten--;
                    } else if (five >= 3) {
                        five -= 3;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }

}
