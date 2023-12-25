package AlgotithmExercise.Recursive;

import AlgotithmExercise.BinaryTree.TreeNode;

public class DFS_bstToGst {

    public TreeNode bstToGst(TreeNode root) {
        DFS_bstToGst(root, 0);
        return root;
    }

    public int DFS_bstToGst(TreeNode root, int val) {
        if(root == null) return 0;
        int right_sum = DFS_bstToGst(root.right, val);
        int origin_val = root.val;
        root.val = val + root.val + right_sum;
        int left_sum = DFS_bstToGst(root.left, root.val);
        return origin_val + left_sum + right_sum;
    }

    /**
     *
     * 链接：https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree/
     *
     */
    class Solution {
        int sum = 0;

        public TreeNode bstToGst(TreeNode root) {
            if (root != null) {
                bstToGst(root.right);
                sum += root.val;
                root.val = sum;
                bstToGst(root.left);
            }
            return root;
        }
    }

}
