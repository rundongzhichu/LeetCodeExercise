package AlgotithmExercise.BinaryTree;

import java.util.*;

class DFS {
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

class BFS {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> lists=new ArrayList<Integer>();
        if(root==null)
            return lists;
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.offer(root);
        while(root != null || !queue.isEmpty()){
            root = queue.poll();
            if(root.left!=null)
                queue.offer(root.left);
            if(root.right!=null)
                queue.offer(root.right);
            lists.add(root.val);
        }
        return lists;
    }
}