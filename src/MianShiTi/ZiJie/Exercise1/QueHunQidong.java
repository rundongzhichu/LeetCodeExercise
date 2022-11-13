package MianShiTi.ZiJie.Exercise1;

import java.util.*;

public class QueHunQidong {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] majiangs = new int[10];
        for (int i = 0; i < 13; i++) {
            int mj = Integer.valueOf(in.next());
            majiangs[mj] += 1;
        }


        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
             if(majiangs[i] == 4) continue;
             int[] temp = Arrays.copyOf(majiangs, 10);
             temp[i] += 1;

             boolean canH = false;
            // 拿一对做雀头
            for (int j = 1; j < 10; j++) {
                if (temp[j] >= 2 ) {
                    int[] temp1 = Arrays.copyOf(temp, 10);
                    temp1[j] -= 2;
                    canH = canHu(temp1, 2, 1);
                    if(canH) break;
                }
            }
            if(canH) res.add(i);
        }

        int size = res.size();
        for (int i = 0; i < size -1; i++) {
            System.out.print(res.get(i)+ " ");
        }

        System.out.print(res.get(size -1));
    }

    public static boolean canHu(int[] majiangs, int count, int index) {


        if(count == 14){
            return true;
        }

        boolean canH = false;
        if(majiangs[index]>0){
            int[] temp = Arrays.copyOf(majiangs, 10);
            if(deleteOneShunzi(temp, index)){
                canH = canH || canHu(temp, count +3, index);
            }

            temp = Arrays.copyOf(majiangs, 10);
            if(deleteOneKezi(temp, index)) {
                canH = canH || canHu(temp, count + 3, index);
            }
            return canH;
        }else{
            int[] temp = Arrays.copyOf(majiangs, 10);
            canH =  canHu(temp, count, index + 1);
            return canH;
        }
    }

    public static boolean deleteOneShunzi(int[] majiangs, int index){
        if(index + 2 < 10){
            if(majiangs[index] >=1 && majiangs[index+1] >=1 && majiangs[index+2] >=1){
                majiangs[index] -= 1;
                majiangs[index +1] -= 1;
                majiangs[index +2] -= 1;
                return true;
            }
        }
        return false;
    }

    public static boolean deleteOneKezi(int[] majiangs, int index){
        if(majiangs[index] >= 3){
            majiangs[index] -= 3;
            return true;
        }
        return false;
    }

}
