package AlgotithmExercise.LinkedList;

public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tail, preTail = null, newHead =  null, cur = null, pre = null;
        while (head != null) {
            tail = head;
            int i=0;
            for (i = 0; i < k;) {
                cur = head;
                head = head.next;
                cur.next = pre;
                pre = cur;

                if(head != null) {
                    i++;
                } else {
                    break;
                }
            }

            // 最后如果不是k的整数倍的数目的时候，再给它翻转回来
            if(i < k-1) {
                // System.out.println("cur.val0 = " + cur.next.val + " , j: " + i);
                ListNode temp = cur;
                tail = cur;
                pre = null;
                for (int j = 0; j <= i; j++) {
                    // System.out.println("temp.val0 = " + temp.val + " , j: " + i);
                    cur = temp;
                    temp = temp.next;
                    cur.next = pre;
                    pre = cur;
                }
            }
            if(newHead == null) {
                newHead = cur;
                tail.next = null;
                preTail = tail;
            } else {
//                System.out.println("cur.val = " + cur.val);
                preTail.next = cur;
                tail.next = null;
                preTail = tail;
            }
        }
        return newHead;
    }

    /**
     *
     *     链接：https://leetcode.cn/problems/reverse-nodes-in-k-group/solutions/248591/k-ge-yi-zu-fan-zhuan-lian-biao-by-leetcode-solutio/
     *
     *     核心思想：直接模拟操作就行了，找到要翻转的链表的头部和尾部指标，然后进行翻转就可以
     *
     */
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode hair = new ListNode(0);
            hair.next = head;
            ListNode pre = hair;

            while (head != null) {
                ListNode tail = pre;
                // 查看剩余部分长度是否大于等于 k
                for (int i = 0; i < k; ++i) {
                    tail = tail.next;
                    if (tail == null) {
                        return hair.next;
                    }
                }
                ListNode nex = tail.next;
                ListNode[] reverse = myReverse(head, tail);
                head = reverse[0];
                tail = reverse[1];
                // 把子链表重新接回原链表
                pre.next = head;
                tail.next = nex;
                pre = tail;
                head = tail.next;
            }

            return hair.next;
        }

        public ListNode[] myReverse(ListNode head, ListNode tail) {
            ListNode prev = tail.next;
            ListNode p = head;
            while (prev != tail) {
                ListNode nex = p.next;
                p.next = prev;
                prev = p;
                p = nex;
            }
            return new ListNode[]{tail, head};
        }
    }

}
