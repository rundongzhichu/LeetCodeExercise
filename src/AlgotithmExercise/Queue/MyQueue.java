package AlgotithmExercise.Queue;

import java.util.Deque;
import java.util.LinkedList;

public class MyQueue {

    /*
  https://leetcode.cn/problems/implement-queue-using-stacks/?envType=daily-question&envId=2024-03-04
     */

    private int size = 0;

    private int top;

    private Deque<Integer> stack1;
    private Deque<Integer> stack2;

    public MyQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void push(int x) {
        if(size == 0) top = x;
        size++;
        if(stack1.isEmpty()) {
            stack2.push(x);
        }
        else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            stack2.push(x);
        }
    }

    public int pop() {
        int res = 0;
        if(!stack1.isEmpty()) {
            res = stack1.pop();
            for (int i = 0; i < size - 1; i++) {
                top = stack1.peek();
                stack2.push(stack1.pop());
            }
        } else {
            for (int i = 0; i < size - 1; i++) {
                top = stack2.peek();
                stack1.push(stack2.pop());
            }
            res = stack2.pop();
        }
        System.out.println("size = " + size);
        size--;
        return res;
    }

    public int peek() {
        return top;
    }

    public boolean empty() {
        return size <= 0;
    }
}
