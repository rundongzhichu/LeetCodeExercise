package AlgotithmExercise.Stack;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    /**
     * https://leetcode.cn/problems/implement-stack-using-queues/?envType=daily-question&envId=2024-03-03
     */

    private static Queue<Integer> queue1;
    private static Queue<Integer> queue2;

    static int size = 0;
    static int top = 0;

    public MyStack() {
        size = 0;
        top = 0;
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public static void push(int x) {
        if(queue1.isEmpty()) queue2.add(x);
        else queue1.add(x);
        top = x;
        size++;
    }

    public int pop() {
        int res = 0;
        if(!queue1.isEmpty()) {
            for (int i = 0; i < size - 1; i++) {
                top = queue1.peek();
                queue2.add(queue1.poll());
            }
            res = queue1.poll();
        } else {
            for (int i = 0; i < size - 1; i++) {
                top = queue2.peek();
                queue1.add(queue2.poll());
            }
            res = queue2.poll();
        }
        size--;
        return res;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return size <= 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

