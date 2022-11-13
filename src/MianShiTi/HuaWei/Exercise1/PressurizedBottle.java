package MianShiTi.HuaWei.Exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PressurizedBottle {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        try {
            while ((str = br.readLine()) != null){
                int bottles = Integer.valueOf(str);

                if(bottles==0) break;
                int count = bottles/3;
                int get = count;
                bottles = get + bottles%3;

                if(count == 0){
                    if(bottles == 1){
                        System.out.println(0);
                        continue;
                    }
                    if(bottles == 2){
                        System.out.println(1);
                        continue;
                    }
                }

                while (true){
                    get = bottles/3;
                    count += get;
                    bottles = get + bottles%3;
                    if(bottles == 1){
                        break;
                    }
                    if(bottles == 2){
                        count +=1;
                        break;
                    }
                }
                System.out.println(count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
