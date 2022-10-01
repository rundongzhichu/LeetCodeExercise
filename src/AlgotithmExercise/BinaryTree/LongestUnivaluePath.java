package AlgotithmExercise.BinaryTree;

public class LongestUnivaluePath {

    private int maxLen=0;

    public int longestUnivaluePath(TreeNode root) {
        if(root==null) return 0;
        longestUnivaluePath1(root, Integer.MIN_VALUE);
        return maxLen;
    }

    public void longestUnivaluePath1(TreeNode root, int rootFatherVal) {
        if(root.val != rootFatherVal) {
            int leftPath = 0;
            int rightPath =0;
            if(root.left!=null){
                leftPath = longestUnivaluePath(root.left, root.val);
            }
            if(root.right!=null)
                rightPath = longestUnivaluePath(root.right, root.val);
            maxLen = Math.max(maxLen, leftPath+rightPath);
        }
        if(root.left!=null){
            longestUnivaluePath1(root.left, root.val);
        }
        if(root.right!=null)
            longestUnivaluePath1(root.right,root.val);
    }

    public int longestUnivaluePath(TreeNode root, int rootFatherVal) {
        int leftPath = 0;
        int rightPath =0;
        if(root.val == rootFatherVal){
            leftPath++;
            rightPath++;
            if(root.left!=null){
                leftPath += longestUnivaluePath(root.left, root.val);
            }
            if(root.right!=null)
                rightPath += longestUnivaluePath(root.right, root.val);
        }
        maxLen = Math.max(maxLen, leftPath+rightPath - 2);
        return Math.max(leftPath, rightPath);
    }
}
