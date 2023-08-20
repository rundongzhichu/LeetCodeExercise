package AlgotithmExercise.LinkedList;

import AlgotithmExercise.DoublePointer.ListNode;

public class SwapPairs {

    public AlgotithmExercise.DoublePointer.ListNode swapPairs(AlgotithmExercise.DoublePointer.ListNode head) {
        AlgotithmExercise.DoublePointer.ListNode res = new AlgotithmExercise.DoublePointer.ListNode(-1);
        res.next = head;
        ListNode prev = head, cur = head, sub, prevprev = res;

        while(cur!=null && cur.next != null) {
            cur = cur.next;
            sub = cur.next;
            cur.next = prev;
            prev.next = sub;
            prevprev.next = cur;
            prevprev = prev;
            prev=sub;
            cur = sub;
            System.out.println("sub.val = " + sub.val);

        }
        return res.next;
    }

}
