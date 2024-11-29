package AlgotithmExercise.BinaryTree;

public class PathSum {

    /**
     *
     * 链接：https://leetcode.cn/problems/path-sum-iii/solutions/1021296/lu-jing-zong-he-iii-by-leetcode-solution-z9td/
     * 核心思路：这道题我没做出来，采用深度优先遍历去做
     *
     */
    class Solution {

        /**
         * 这里可以枚举路径的根节点
         *
         * @param root
         * @param targetSum
         * @return
         */
        public int pathSum(TreeNode root, long targetSum) {
            if (root == null) {
                return 0;
            }

            int ret = rootSum(root, targetSum);
            ret += pathSum(root.left, targetSum);
            ret += pathSum(root.right, targetSum);
            return ret;
        }

        /**
         * 这个函数表示从当前根节点往下找和胃targetSum的路径，路径包括当前根节点
         * @param root
         * @param targetSum
         * @return
         */
        public int rootSum(TreeNode root, long targetSum) {
            int ret = 0;

            if (root == null) {
                return 0;
            }
            int val = root.val;
            if (val == targetSum) {
                ret++;
            }

            ret += rootSum(root.left, targetSum - val);
            ret += rootSum(root.right, targetSum - val);
            return ret;
        }
    }

}
