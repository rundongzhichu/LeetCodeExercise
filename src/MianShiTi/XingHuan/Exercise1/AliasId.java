package MianShiTi.XingHuan.Exercise1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AliasId {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int N = Integer.valueOf(in.next());
        List<Integer> ids = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int id = Integer.valueOf(in.next());
            int index = Integer.valueOf(in.next());
            ids.add(index,id);
        }
        int size = ids.size();
        for (int i = 0; i < size -1; i++) {
            System.out.println(ids.get(i) + " ");
        }
        System.out.println(ids.get(size-1));
    }

}
