package MianShiTi.TenXunYinYue.Exercise1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

public class MinMax {
    public int minMax (ArrayList<Integer> a, int k, int x) {
        // write code here
        int size = a.size();

        TreeMap<Integer, Integer> treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for(int item : a){
            treeMap.put(item, 0);
        }

        for(int i=0; i<k; i++){
            int firstKey = treeMap.firstKey();
            treeMap.remove(firstKey);
            treeMap.put(firstKey-x, 0);
        }
        return treeMap.firstKey();
    }
}
