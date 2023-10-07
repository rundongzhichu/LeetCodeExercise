package AlgotithmExercise.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class StockSpanner {

    class ListNode {
        int val;
        ListNode next;
        ListNode pre;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode pre, ListNode next) { this.val = val; this.pre = pre; this.next = next; }
    }

    private ListNode heead;
    private ListNode tail;

    public StockSpanner() {
    }

    public int next(int price) {
        if(heead == null) {
            heead = new ListNode(price, null, null);
            tail = heead;
        } else {
            tail.next = new ListNode(price, tail, null);
            tail = tail.next;
        }

        ListNode cur = tail;
        int cnt = 0;
        while (cur != null && cur.val <= price) {
            cnt++;
            cur = cur.pre;
        }
        return cnt;
    }
}


/**
 *
 *   链接：https://leetcode.cn/problems/online-stock-span/solutions/1906765/gu-piao-jie-ge-kua-du-by-leetcode-soluti-5cm7/
 *
 *   单调栈的思路： 创建一个栈，每个栈元素是一个数组，数组记录了当前元素的顺序值和当前元素值，每次把比当前元素小的都弹出去，然后把当前元素记录到栈中就行了。
 *
 */
class StockSpanner1 {
    Deque<int[]> stack;
    int idx;

    public StockSpanner1() {
        stack = new ArrayDeque<int[]>();
        stack.push(new int[]{-1, Integer.MAX_VALUE});
        idx = -1;
    }

    public int next(int price) {
        idx++;
        while (price >= stack.peek()[1]) {
            stack.pop();
        }
        // 核心代码
        int ret = idx - stack.peek()[0];
        stack.push(new int[]{idx, price});
        return ret;
    }
}


