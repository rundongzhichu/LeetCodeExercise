package AlgotithmExercise.LinkedList;

import java.util.Deque;
import java.util.LinkedList;

public class AddTwoNumbers {
    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode tail = null;
        ListNode head = null;

        int carry = 0;
        while (l1 != null || l2 != null){
            int l1Val = -1, l2Val = -1;
            if(l1 != null){
                l1Val = l1.val;
                l1 = l1.next;
            }

            if(l2 != null){
                l2Val = l2.val;
                l2 = l2.next;
            }

            int curVal = 0;
            if(l1Val == -1){
                curVal = (l2Val + carry)%10;
                carry = (l2Val + carry)/10;
            }else if(l2Val == -1){
                curVal = (l1Val + carry)%10;
                carry = (l1Val + carry)/10;
            }else{
                curVal = (l1Val + l2Val + carry)%10;
                carry = (l1Val + l2Val + carry)/10;
            }

            if(head == null){
                head = new ListNode(curVal, null);
                tail = head;
            }else{
                tail.next = new ListNode(curVal, null);
                tail = tail.next;
            }
        }
        if(carry != 0){
            tail.next = new ListNode(carry, null);
        }
        return head;
    }

}
