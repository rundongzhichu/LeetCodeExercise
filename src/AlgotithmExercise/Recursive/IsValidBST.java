package AlgotithmExercise.Recursive;

import AlgotithmExercise.BinaryTree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class IsValidBST {

    /**
     * 找到左树的最大值和右树的最小值一层层返回往上进行比较
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST1(root) != null;
    }

    public int[] isValidBST1(TreeNode root) {
        if(root.left == null && root.right == null) return new int[]{root.val,root.val};
        int[] left = null;
        int[] right = null;
        if(root.left != null) {
            left = isValidBST1(root.left);
        }

        if(root.right != null) {
            right = isValidBST1(root.right);
        }

        if(left == null && root.left == null && right != null && root.val < right[0] && root.val < root.right.val) return right;
        if(left != null && root.right ==null && right == null && root.val > left[1] && root.val > root.left.val) return left;
        if(left != null && right != null && root.val > left[1] && root.val < right[0]
                && root.val > root.left.val && root.val < root.right.val) {
            return new int[]{Math.min(left[0], right[0]),
                    Math.max(left[1], right[1])};
        }
        return null;
    }


    /**
     * 中序遍历的结果如果是升序的，那么就是一个二叉搜索树
     *
     * https://leetcode.cn/problems/validate-binary-search-tree/solutions/230256/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
     *
     */
    class Solution {
        public boolean isValidBST(TreeNode root) {
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            double inorder = -Double.MAX_VALUE;

            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
                if (root.val <= inorder) {
                    return false;
                }
                inorder = root.val;
                root = root.right;
            }
            return true;
        }
    }

    /**
     *
     * 链接：https://leetcode.cn/problems/validate-binary-search-tree/solutions/230256/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
     *
     * 核心思路： 这启示我们设计一个递归函数 helper(root, lower, upper) 来递归判断，函数表示考虑以 root 为根的子树，
     * 判断子树中所有节点的值是否都在 (l,r) 的范围内（注意是开区间）。如果 root 节点的值 val 不在 (l,r) 的范围内说明不满足条件直接返回，
     * 否则我们要继续递归调用检查它的左右子树是否满足，如果都满足才说明这是一棵二叉搜索树。
     *
     */
    class Solution1 {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean isValidBST(TreeNode node, long lower, long upper) {
            if (node == null) {
                return true;
            }
            if (node.val <= lower || node.val >= upper) {
                return false;
            }
            return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
        }
    }


    /**
     *
     *  链接：https://leetcode.cn/problems/validate-binary-search-tree/solutions/230256/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
     *  核心思路：利用二叉搜索树的中序遍历是有序的特性
     *
     */
    class Solution2 {
        public boolean isValidBST(TreeNode root) {
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            double inorder = -Double.MAX_VALUE;

            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
                if (root.val <= inorder) {
                    return false;
                }
                inorder = root.val;
                root = root.right;
            }
            return true;
        }
    }

}
