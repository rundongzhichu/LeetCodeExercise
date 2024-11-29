package AlgotithmExercise.BinaryTree;

import AlgotithmExercise.BinaryTree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class KthSmallest {

    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        kthSmallest(root, pq);
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return pq.poll();
    }

    public void kthSmallest(TreeNode root, PriorityQueue<Integer> pq) {
        if(root ==  null) return ;
        pq.add(root.val);
        kthSmallest(root.left, pq);
        kthSmallest(root.right, pq);
    }


    /**
     *
     *     链接：https://leetcode.cn/problems/kth-smallest-element-in-a-bst/solutions/1050055/er-cha-sou-suo-shu-zhong-di-kxiao-de-yua-8o07/
     *
     *     核心思路：二叉搜索树中序遍历是有序的，所以按照中序遍历找到第k个元素就可以了
     *
     */
    class Solution {
        public int kthSmallest(TreeNode root, int k) {
            Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                --k;
                if (k == 0) {
                    break;
                }
                root = root.right;
            }
            return root.val;
        }
    }

}
