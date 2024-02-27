package AlgotithmExercise.BinaryTree;

import java.util.*;

public class ConstructFromPrePost {

    public static void main(String[] args) {
        new ConstructFromPrePost().constructFromPrePost(new int[]{1,2,4,5,3,6,7}, new int[]{4,5,2,6,7,3,1});
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        ArrayList<Integer> preorderList = Arrays.stream(preorder).collect(ArrayList::new, List::add, (left, right) -> {
            left.addAll(right);
        });
        ArrayList<Integer> postorderList = Arrays.stream(postorder).collect(ArrayList::new, List::add, (left, right) -> {
            left.addAll(right);
        });
        return constructFromPrePost(preorderList, postorderList, 0, 0, postorder.length - 1, postorder.length);
    }


    public TreeNode constructFromPrePost(List<Integer> preorderList, List<Integer> postorderList, int rIdx, int l, int r, int size) {
        TreeNode treeNode = new TreeNode(preorderList.get(rIdx));
        if(rIdx + 1 >= size || l>=r || r - 1 < 0) return treeNode;
        int subLeftIdx = postorderList.indexOf(preorderList.get(rIdx + 1));
        if(subLeftIdx - l  >= 0) {
            treeNode.left = constructFromPrePost(preorderList, postorderList, rIdx + 1, l, subLeftIdx, size);
        }
        int subRightIdx = preorderList.indexOf(postorderList.get(r-1));
        if(r - subLeftIdx  - 1 >= 0)
            treeNode.right = constructFromPrePost(preorderList, postorderList, subRightIdx, subLeftIdx + 1, r - 1, size);
        return treeNode;
    }


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        // https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/solutions/2645281/gen-ju-qian-xu-he-hou-xu-bian-li-gou-zao-6vzt/?envType=daily-question&envId=2024-02-22

        private Map<Integer, Integer> map;

        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            this.map = new HashMap<>();
            // 记录后序遍历的每个元素对应的位置
            for (int i = 0; i < postorder.length; i++) {
                map.put(postorder[i], i);
            }
            return dfs(preorder, 0, 0, postorder.length-1);
        }

        private TreeNode dfs(int[] preorder, int post_start, int pre_start, int pre_end) {
            // 判断pre_start和pre_end之间是否还有元素，以及是否在数组范围内
            if (pre_end >= preorder.length || pre_start > pre_end) {
                return null;
            }

            // 以当前值创建树节点
            int rootValue = preorder[pre_start];
            TreeNode ans = new TreeNode(rootValue);
            // 如果pre_start和pre_end之间只有一个元素，则直接返回
            if (pre_end == pre_start) {
                return ans;
            }
            //获取preorder[pre_start+1]左子树根节点的值， 这里借助后序数组用来判断子树总共有多少个节点， 后续数组中，根节点之前的元素个数是左右子树的元素个数
            int left_len = map.get(preorder[pre_start+1]) - post_start + 1;

            // 递归构建左右子树
            ans.left = dfs(preorder, post_start,  pre_start+1, pre_start+left_len);
            ans.right = dfs(preorder, post_start+left_len, pre_start+left_len+1, pre_end);

            return ans;
        }
    }

}
