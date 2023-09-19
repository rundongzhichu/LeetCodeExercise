package AlgotithmExercise.Recursive;

import AlgotithmExercise.BinaryTree.TreeNode;

public class Rob {

    /**
     * 这个思路错误在记忆化搜索返回后sum的值不会记录遍历过的子节点的和的节点的和
     *
     */
    private int res = 0;

    public int rob(TreeNode root) {
        rob(root.left, false, 0);
        rob(root.right, false, 0);
        rob(root.left, true, root.val);
        rob(root.right, true, root.val);
        return res;
    }

    public void rob(TreeNode root, boolean robbed, int sum) {
        if(root == null) {
            res = Math.max(res, sum);
            return;
        }

        rob(root.left, false, sum);
        rob(root.right, false, sum);
        if(!robbed) {
            System.out.println("root.val = " + root.val);
            rob(root.left, true, sum + root.val);
            rob(root.right, true, sum + root.val);
        }
    }

    /**
     * 官方解答：
     *
     * 基本思想： 就是将当前节点rob或者不rob的情况封装到数组里面，然后返回上一层进一步计算偷或者不偷情况下的最大值
     * 基本思想： https://leetcode.cn/problems/house-robber-iii/solutions/361038/da-jia-jie-she-iii-by-leetcode-solution/?envType=daily-question&envId=2023-09-18
     *
     */
    class Solution {
        public int rob(TreeNode root) {
            int[] rootStatus = dfs(root);
            return Math.max(rootStatus[0], rootStatus[1]);
        }

        public int[] dfs(TreeNode node) {
            if (node == null) {
                return new int[]{0, 0};
            }
            int[] l = dfs(node.left);
            int[] r = dfs(node.right);
            int selected = node.val + l[1] + r[1];
            int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
            return new int[]{selected, notSelected};
        }
    }


}


