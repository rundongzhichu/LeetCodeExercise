package Stack;

import java.util.LinkedList;
import java.util.Stack;

public class ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();

        int i=0, j=0;
        stack.push(pushed[i]);
        i++;

        while (i<=pushed.length && !stack.isEmpty()){
            if(stack.peek()==popped[j]){
                j++;
                System.out.println("pop: "+ stack.peek());
                stack.pop();
            }else {
                if(i==pushed.length){
                    if(stack.isEmpty())
                        return true;
                    return false;
                }
                System.out.println("push: "+ pushed[i]);
                stack.push(pushed[i]);
                i++;
            }
        }

        System.out.println(" "+j);

        if(j+1 == popped.length) return true;
        return false;
    }
}
