package AlgotithmExercise.Array;

import AlgotithmExercise.DoublePointer.ListNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class IsPalindrome {

    public boolean isPalindrome(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size ++;
            cur = cur.next;
        }
        if(size == 1) return true;

        boolean palindrome = true;
        cur = head;
        if(size % 2 == 0) {
            int mid = size / 2 -1;
            for (int i = 0; i <= mid; i++) {
                stack.push(cur.val);
                cur = cur.next;
            }
            for (int i = mid +1; i < size; i++) {
                if(cur.val != stack.pop()) {
                    return false;
                }
                cur = cur.next;
            }
        } else {
            int mid = size/2;
            for (int i = 0; i <= mid; i++) {
                stack.push(cur.val);
                cur = cur.next;
            }
            cur =cur.next;
            for (int i = mid + 2; i < size; i++) {
                if(cur.val != stack.pop()) {
                    return false;
                }
                cur = cur.next;
            }
        }
        return true;
    }

    /**
     *
     *     链接：https://leetcode.cn/problems/palindrome-linked-list/solutions/457059/hui-wen-lian-biao-by-leetcode-solution/
     *     核心思想： 复制到数组中采用双指针
     */
    class Solution {
        public boolean isPalindrome(ListNode head) {
            List<Integer> vals = new ArrayList<Integer>();

            // 将链表的值复制到数组中
            ListNode currentNode = head;
            while (currentNode != null) {
                vals.add(currentNode.val);
                currentNode = currentNode.next;
            }

            // 使用双指针判断是否回文
            int front = 0;
            int back = vals.size() - 1;
            while (front < back) {
                if (!vals.get(front).equals(vals.get(back))) {
                    return false;
                }
                front++;
                back--;
            }
            return true;
        }
    }

}
