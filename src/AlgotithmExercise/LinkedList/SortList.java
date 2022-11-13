package AlgotithmExercise.LinkedList;

import java.util.Arrays;

/**
 * 1. 快慢指针
 * 2.归并排序
 *
 * 或者
 *
 * 把数值拿出来排好序以后，然后填到链表里
 *
 */
public class SortList {

    public ListNode sortList(ListNode head) {

        if(head == null){
            return head;
        }
        ListNode sortedHead = new ListNode();
        sortedHead.next = head;
        head = head.next;
        if(head !=null){
            sortedHead.next.next = null;
        }

        while (head != null){
            ListNode temp = head;
            head=  head.next;

            ListNode pre = sortedHead;
            ListNode cur = sortedHead.next;
            while (cur != null){
                if(cur.val < temp.val){
                    if(cur.next != null){
                        cur = cur .next;
                        pre = pre.next;
                    }else {
                        cur.next = temp;
                        temp.next = null;
                        break;
                    }
                } else {
                    pre.next = temp;
                    temp.next = cur;
                    break;
                }
            }
        }
        return sortedHead.next;
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = "abc";
        System.out.println(s1==s2);
        System.out.println(s1==s3);
    }

}
