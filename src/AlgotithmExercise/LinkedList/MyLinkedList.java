package AlgotithmExercise.LinkedList;

public class MyLinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;

    // 传入数组 构建一个链表
    public MyLinkedList(int[] arr) {
        size = arr.length;
        head = new ListNode(-1, null);
        tail = head;
        for (int i = 0; i < arr.length; i++) {
            ListNode cur = new ListNode(arr[i], null);
            tail.next = cur;
            tail = cur;
        }
        head = head.next;
    }

    // 传入链表头获得打印链表
    public void print() {
        ListNode cur = head;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println(cur.value);
    }

    // 完成题目计算要求
    public void sortList() {
        ListNode oddHead = new ListNode(-1, null), evenHead= new ListNode(-1, null),
                cur = head, oddCur = oddHead, evenCur = null, evenNext;
        while(cur != null) {
            oddCur.next = cur;
            oddCur = oddCur.next;
            cur = cur.next;
            oddCur.next = null;

            if(cur == null) {
                break;
            }
            evenNext = evenCur;
            evenCur = cur;
            cur = cur.next;
            evenCur.next = evenNext;
            evenHead.next = evenCur;
        }


        head = new ListNode(-1, null);
        cur = head;
        oddHead = oddHead.next;
        evenHead = evenHead.next;
        while(oddHead != null && evenHead != null) {
            if(oddHead.value <= evenHead.value) {
                cur.next = oddHead;
                oddHead = oddHead.next;
            } else {
                cur.next = evenHead;
                evenHead = evenHead.next;
            }
            cur = cur.next;
        }
        if(oddHead != null) {
            cur.next = oddHead;
        }
        if(evenHead != null) {
            cur.next = evenHead;
        }
        head = head.next;
    }

    class ListNode {
        ListNode next;
        int value;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

}
