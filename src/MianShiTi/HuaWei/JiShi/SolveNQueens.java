package MianShiTi.HuaWei.JiShi;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens {

    List<List<String>> res = null;

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        List<Integer> columnIndexs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            columnIndexs.add(i);
            solveNQueens(columnIndexs,  1, n);
            columnIndexs.remove(columnIndexs.size()-1);
        }
        return res;
    }

    public void solveNQueens(List<Integer> columIndexs, int line, int n) {

        if(line == n) {
            List<String> matrix = generateMatrix(columIndexs, n);
            res.add(matrix);
            return ;
        }

        StringBuilder sb = getOneLineStr(n);
        for (int i = 0; i < n; i++) {
            if(checkCanPlaceQueen(columIndexs, i, line, n)) {
                columIndexs.add(i);
                solveNQueens(columIndexs, line + 1, n);
                columIndexs.remove(columIndexs.size()-1);
            }
        }
    }

    public List<String> generateMatrix(List<Integer> columIndexs, int n){
        List<String> matrix = new ArrayList<>();
        for (int index : columIndexs) {
            StringBuilder sb = getOneLineStr(n);
            sb.setCharAt(index, 'Q');
            matrix.add(sb.toString());
        }
        return matrix;
    }

    public StringBuilder getOneLineStr(int n){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(".");
        }
        return sb;
    }

    public boolean checkCanPlaceQueen(List<Integer> columIndexs, int queenIndex, int line, int n){
        for (int i = 0; i < line; i++) {
            if(columIndexs.get(i) == queenIndex) return false;
        }
        for (int i = line-1, j = queenIndex -1; i>=0 && j>=0 ; i--, j--) {
            if(columIndexs.get(i) == j) return false;
        }
        for (int i = line-1, j = queenIndex +1; i>=0 && j< n ; i--, j++) {
            if(columIndexs.get(i) == j) return false;
        }
        return true;
    }

}
