package AlgotithmExercise.BinaryTree;

import AlgotithmExercise.Array.RobotSim;
import AlgotithmExercise.BinaryTree.TreeNode;

public class LcaDeepestLeaves {

    private int deepest = 0;
    private TreeNode deepestNode = null;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        lcaDeepestLeaves(root, 0);
        return deepestNode;
    }

    public int lcaDeepestLeaves(TreeNode root, int deepth) {
        if(root == null) return deepth - 1;

        int leftDeep = deepth, rightDeep = deepth;
        leftDeep = Math.max(leftDeep, lcaDeepestLeaves(root.left, deepth + 1));
        rightDeep = Math.max(rightDeep, lcaDeepestLeaves(root.right, deepth + 1));

        deepest = Math.max(leftDeep, deepest);
        deepest = Math.max(rightDeep, deepest);
        if(leftDeep == rightDeep && deepest == leftDeep) {
            deepestNode = root;
            System.out.println("deepestNode = " + deepestNode.val);
        }
        return Math.max(leftDeep, rightDeep);
    }
}


/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves/solutions/2421007/zui-shen-xie-jie-dian-de-zui-jin-gong-go-cjzv/?envType=daily-question&envId=2023-09-06
 *
 * 从下往上算深度
 */
class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return f(root).getKey();
    }

    private RobotSim.Pair<TreeNode, Integer> f(TreeNode root) {
        if (root == null) {
            return new Pair<>(root, 0);
        }

        Pair<TreeNode, Integer> left = f(root.left);
        Pair<TreeNode, Integer> right = f(root.right);

        if (left.getValue() > right.getValue()) {
            return new Pair<>(left.getKey(), left.getValue() + 1);
        }
        if (left.getValue() < right.getValue()) {
            return new Pair<>(right.getKey(), right.getValue() + 1);
        }
        return new Pair<>(root, left.getValue() + 1);
    }
}


