package AlgotithmExercise.Array;

import java.util.*;

public class ConstructArray {
    public int[] constructArray(int n, int k) {
        Set<Integer> divideRecor = new HashSet<>();
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            res[i] = i+1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int divide = res[i] - res[j];
                if(k<=0) break;
                if(!divideRecor.contains(divide)){
                    divideRecor.add(divide);
                }else {
                    int newDivideCount = 0;
                    if(i-1>=0){
                        divide = res[i-1] - res[i];
                    }

                }
            }
        }

        return res;
    }

    public void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    class Solution {
        public int[] constructArray(int n, int k) {
            int[] answer = new int[n];
            int idx = 0;
            for (int i = 1; i < n - k; ++i) {
                answer[idx] = i;
                ++idx;
            }
            for (int i = n - k, j = n; i <= j; ++i, --j) {
                answer[idx] = i;
                ++idx;
                if (i != j) {
                    answer[idx] = j;
                    ++idx;
                }
            }
            return answer;
        }
    }

}
