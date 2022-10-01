package AlgotithmExercise.DoublePointer;

public class MiddleNote {
    public ListNode middleNode(ListNode head) {
        ListNode tail = head;

        for(int i=0; tail!=null; i++){
            if((i+1)%2==0) head=head.next;
            tail = tail.next;
        }
        return head;
    }
}
