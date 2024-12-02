package AlgotithmExercise.BinaryTree;

public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = sortedArrayToBST(nums, 0, nums.length - 1);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if(start > end) {
            return null;
        }
        int ridx = (start + end) / 2;
        TreeNode root = new TreeNode(nums[ridx]);
        root.left = sortedArrayToBST(nums, start, ridx - 1);
        root.right = sortedArrayToBST(nums, ridx + 1, end);
        return root;
    }

    /**
     *
     *  链接：https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/solutions/312607/jiang-you-xu-shu-zu-zhuan-huan-wei-er-cha-sou-s-33/
     *  核心思路： 中序遍历， 每一次选取中间的数值作为根节点
     */
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }

        public TreeNode helper(int[] nums, int left, int right) {
            if (left > right) {
                return null;
            }

            // 总是选择中间位置左边的数字作为根节点
            int mid = (left + right) / 2;

            TreeNode root = new TreeNode(nums[mid]);
            root.left = helper(nums, left, mid - 1);
            root.right = helper(nums, mid + 1, right);
            return root;
        }
    }

}
