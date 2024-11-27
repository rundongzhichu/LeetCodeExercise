package AlgotithmExercise.BinaryTree;

import java.util.*;

public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> levelValMap = new HashMap<>();
        rightSideView(root, 0, levelValMap);
        int size = levelValMap.size();
        List<Integer> res = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            res.add(i, levelValMap.get(i));
        }
        return res;
    }

    public void rightSideView(TreeNode root, int level, Map<Integer, Integer> levelValMap) {
        if(root == null) {
            return;
        }
        levelValMap.put(level, root.val);
        rightSideView(root.left, level + 1, levelValMap);
        rightSideView(root.right, level + 1, levelValMap);
    }


    /**
     *
     * 链接：https://leetcode.cn/problems/binary-tree-right-side-view/solutions/213494/er-cha-shu-de-you-shi-tu-by-leetcode-solution/
     * 核心思想：深度优先遍历，记录每一层节点深度和值之间的映射关系，然后获取到最终结果，先遍历右边的节点，那么每一层只需要放一次就可以
     *
     */
    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
            int max_depth = -1;

            Deque<TreeNode> nodeStack = new LinkedList<TreeNode>();
            Deque<Integer> depthStack = new LinkedList<Integer>();
            nodeStack.push(root);
            depthStack.push(0);

            while (!nodeStack.isEmpty()) {
                TreeNode node = nodeStack.pop();
                int depth = depthStack.pop();

                if (node != null) {
                    // 维护二叉树的最大深度
                    max_depth = Math.max(max_depth, depth);

                    // 如果不存在对应深度的节点我们才插入
                    if (!rightmostValueAtDepth.containsKey(depth)) {
                        rightmostValueAtDepth.put(depth, node.val);
                    }

                    // 把右边的节点压在上边，然后保证先处理右边的节点
                    nodeStack.push(node.left);
                    nodeStack.push(node.right);
                    depthStack.push(depth + 1);
                    depthStack.push(depth + 1);
                }
            }

            List<Integer> rightView = new ArrayList<Integer>();
            for (int depth = 0; depth <= max_depth; depth++) {
                rightView.add(rightmostValueAtDepth.get(depth));
            }

            return rightView;
        }
    }

}
