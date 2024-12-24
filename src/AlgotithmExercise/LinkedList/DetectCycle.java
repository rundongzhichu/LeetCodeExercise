package AlgotithmExercise.LinkedList;

import AlgotithmExercise.DoublePointer.ListNode;

import java.util.HashSet;
import java.util.Set;

public class DetectCycle {

    public AlgotithmExercise.DoublePointer.ListNode detectCycle(AlgotithmExercise.DoublePointer.ListNode head) {
        if(head == null) return null;
        ListNode quick = head, slow = head;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if(quick == slow) {
                break;
            }
        }

        if(quick == null || quick.next == null) {
            return null;
        }

        quick = head;

        // 找到入口的关键在于，当快慢指针相遇以后，重新从头开始然后一步一步往后走，直到和慢指针相遇
        // 环外距离为x， 环内相遇的时候慢指针在环内走了y距离， 此时快指针走了x+2y的距离，x+2y-y=x+y的距离等于n*c（c表示环的周长）
        while (quick != slow) {
            slow = slow.next;
            quick = quick.next;
        }
        return slow;
    }

    /**
     *
     * 链接：https://leetcode.cn/problems/linked-list-cycle-ii/solutions/441131/huan-xing-lian-biao-ii-by-leetcode-solution/
     * 核心思想：采用hashSet记录每一个访问过的节点，然后找到第一个重复保存的节点就是循环的入口
     *
     */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode pos = head;
            Set<ListNode> visited = new HashSet<ListNode>();
            while (pos != null) {
                if (visited.contains(pos)) {
                    return pos;
                } else {
                    visited.add(pos);
                }
                pos = pos.next;
            }
            return null;
        }
    }

    /**
     *
     * 核心思想：因此，当发现 slow 与 fast 相遇时，我们再额外使用一个指针 ptr。起始，它指向链表头部；
     * 随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇。
     *
     *
     */
    public class Solution1 {
        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode slow = head, fast = head;
            while (fast != null) {
                slow = slow.next;
                if (fast.next != null) {
                    fast = fast.next.next;
                } else {
                    return null;
                }
                if (fast == slow) {
                    ListNode ptr = head;
                    while (ptr != slow) {
                        ptr = ptr.next;
                        slow = slow.next;
                    }
                    return ptr;
                }
            }
            return null;
        }
    }

}
