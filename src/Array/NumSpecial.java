package Array;

import java.util.HashMap;
import java.util.Map;

public class NumSpecial {
    public int numSpecial(int[][] mat) {

        Map<Integer, Integer> rowRcor = new HashMap<>();
        Map<Integer, Integer> colsRecor = new HashMap<>();

        int rows = mat.length, cols = mat[0].length;

        for(int i=0; i<rows;i++){
            for(int j=0; j<cols; j++){
                if(mat[i][j]==1){
                    rowRcor.put(i,rowRcor.getOrDefault(i,0)+1);
                    colsRecor.put(j, colsRecor.getOrDefault(j,0)+1);
                }
            }
        }

        int rtn=0;
        for(int i=0; i<rows;i++){
            for(int j=0; j<cols; j++){
                if(mat[i][j]==1 && rowRcor.get(i) == 1 && colsRecor.get(j) == 1){
                    rtn++;
                }
            }
        }

        return rtn;
    }
}
