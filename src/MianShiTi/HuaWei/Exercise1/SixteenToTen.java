package MianShiTi.HuaWei.Exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class SixteenToTen {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] sixteens = br.readLine().substring(2).toCharArray();

        Deque<Character> stack = new LinkedList();
        for (int i = 0; i < sixteens.length; i++) {
            stack.push(sixteens[i]);
        }

        int ten = 0;
        int multiply = 1;
        while (!stack.isEmpty()){
            char c = stack.pop();
            int multi;
            if(c-'A'<0){
                multi = Integer.valueOf(c-'0');
            }else {
                multi = 10 + c - 'A';
            }

            ten += multi*multiply;
            multiply *= 16;
        }

        System.out.println(ten);
    }
}
