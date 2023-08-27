package AlgotithmExercise.BinaryTree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GoodNodes {

    public int goodNodes(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        Map<TreeNode, Integer> maxValMap = new HashMap<>();

        stack.push(root);
        int res=1;
        int maxVal = root.val;
        maxValMap.put(root, root.val);
        while (!stack.isEmpty()) {
            root = stack.peek();
            maxVal = maxValMap.get(root);
            while (root.left != null) {
                maxVal = Math.max(root.left.val, maxVal);
                if(maxVal ==  root.left.val) res++;
                stack.push(root.left);
                maxValMap.put(root.left, maxVal);
                TreeNode temp = root.left;
                root.left = null;
                root = temp;
            }

            root = stack.pop();
            System.out.println("root.val = " + root.val);
            maxVal = maxValMap.get(root);
            if(root.right != null) {
                maxVal = Math.max(root.right.val, maxVal);
                if(maxVal ==  root.right.val) res++;
                stack.push(root.right);
                maxValMap.put(root.right, maxVal);
                root.right = null;
            }
        }
        return res;
    }

}
