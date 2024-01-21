package AlgotithmExercise.LinkedList;

import AlgotithmExercise.DoublePointer.ListNode;

public class DeleteDuplicates {

    public AlgotithmExercise.DoublePointer.ListNode deleteDuplicates(AlgotithmExercise.DoublePointer.ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            if(cur.next != null && cur.val == cur.next.val) {
                cur.next = cur.next.next;
                continue;
            }
            cur = cur.next;
        }
        return head;
    }

}
