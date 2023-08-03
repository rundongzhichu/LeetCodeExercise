package AlgotithmExercise.LinkedList;

import AlgotithmExercise.DoublePointer.ListNode;

public class HasCycle {

    public boolean hasCycle(AlgotithmExercise.DoublePointer.ListNode head) {
        if (head == null) return false;
        AlgotithmExercise.DoublePointer.ListNode quick = head.next;
        ListNode slow = head;

        while (quick != null && slow != null){
            if(slow==quick) return true;
            if(quick.next == null) return false;
            slow = slow.next;
            quick = quick.next.next;
        }
        return false;
    }

}
