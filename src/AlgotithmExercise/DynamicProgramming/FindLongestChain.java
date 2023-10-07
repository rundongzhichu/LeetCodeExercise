package AlgotithmExercise.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;


/**
 * leetcode 题目646： https://leetcode.cn/problems/maximum-length-of-pair-chain/description/
 *
 *
 */
public class FindLongestChain {
    public int findLongestChain(int[][] pairs) {
        int len = pairs.length;

        int[][] maxlen = new int[len][len];

        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // Arrays.stream(pairs).forEach(item->{
        //     System.out.println("item[0] = " + item[0]+ "item[1]  = " + item[1]);
        // });


        int resL = 1;

        for(int i = 0; i < len; i++){
            maxlen[i][i] = 1;
            for(int j = i + 1; j < len; j++){
                if(pairs[i][1] < pairs[j][0]){
                    int maxLenToi = 0;
                    for(int k=0; k <= i; k++){
                        maxLenToi =  Math.max(maxlen[k][i], maxLenToi);
                    }
                    maxlen[i][j] = maxLenToi + 1;
                    resL = Math.max(maxlen[i][j], resL);
                }
            }
        }
        return resL;
    }
}
