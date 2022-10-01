package AlgotithmExercise.BinaryTree;

public class TrimBST {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root.val<low){
            if(root.right != null)
                return trimBST(root.right, low, high);
            return null;
        }

        if(root.val > high){
            if(root.left != null)
                return trimBST(root.left, low,high);
            return null;
        }
        if(root.left != null)
            root.left = trimBST(root.left, low, high);
        if(root.right != null)
            root.right = trimBST(root.right, low, high);
        return root;
    }
}
