package AlgotithmExercise.LinkedList;

import AlgotithmExercise.DoublePointer.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public class ReorderList {

    public void reorderList(AlgotithmExercise.DoublePointer.ListNode head) {
        Deque<AlgotithmExercise.DoublePointer.ListNode> deque = new LinkedList<>();
        while (head!=null){
            deque.offer(head);
            head = head.next;
        }
        ListNode cur =deque.pollFirst();
        head = cur;
        while (!deque.isEmpty()) {
            cur.next = deque.pollLast();
            cur = cur.next;
            cur.next = null;
            if(deque.isEmpty()) break;
            cur.next = deque.pollFirst();
            cur = cur.next;
            cur.next = null;
        }
    }

}
