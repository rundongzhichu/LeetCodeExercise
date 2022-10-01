package AlgotithmExercise.DoublePointer;

public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tail = head;
        ListNode rm = head;

        if(head.next==null) return null;

        while (tail!=null){
            if(n>0){
                tail = tail.next;
                n--;
            }else{
                tail = tail.next;
                rm = rm.next;
            }
        }

        if(head == rm) return head.next;

        tail = head;
        while (tail.next != rm){
            tail = tail.next;
        }

        tail.next = tail.next.next;
        return head;
    }
/**
 * 关键在于添加一个头结点
 *
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
 */

}
