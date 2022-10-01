package AlgotithmExercise.BinaryTree;


import java.util.Stack;

public class ConstructMaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();

        int index = 0;
        stack.push(new TreeNode(nums[index]));
        index++;

        while (index<nums.length){
            TreeNode root = new TreeNode(nums[index]);
            while (!stack.isEmpty() && nums[index] > stack.peek().val) root.left = stack.pop();

            if(!stack.isEmpty())
                stack.peek().right = root;

            stack.push(root);
            index++;
        }

        TreeNode root = null;
        while (!stack.isEmpty()) root = stack.pop();
        return root;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        int max = -1;
        int maxIndex = start;

        for(int i=start; i<end; i++){
            if(nums[i]>max){
                max= nums[i];
                maxIndex = i;
            }
        }

        if(max>=0){
            TreeNode node = new TreeNode(max);
            node.left = constructMaximumBinaryTree(nums, start, maxIndex);
            node.right = constructMaximumBinaryTree(nums,maxIndex+1, end);
            return node;
        }else return null;
    }



    public static void main(String[] args) {
        int[] nums = {3,2,1,6,0,5};
        
        new ConstructMaximumBinaryTree().constructMaximumBinaryTree(nums);
    }

}
