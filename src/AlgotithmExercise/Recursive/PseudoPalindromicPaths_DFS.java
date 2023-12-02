package AlgotithmExercise.Recursive;

import AlgotithmExercise.BinaryTree.TreeNode;

public class PseudoPalindromicPaths_DFS {


    public int pseudoPalindromicPaths (TreeNode root) {
        return pseudoPalindromicPaths(root, new int[10]);
    }

    public int pseudoPalindromicPaths (TreeNode root, int[] record) {
        int count = 0;
        record[root.val] ++;
        if(root.left != null && root.right != null){
            count += pseudoPalindromicPaths(root.left, record);
            count += pseudoPalindromicPaths(root.right, record);
            record[root.val] --;
            return count;
        } else if(root.left == null && root.right != null) {
            count += pseudoPalindromicPaths(root.right, record);
            record[root.val] --;
            return count;
        } else if(root.left != null && root.right == null) {
            count += pseudoPalindromicPaths(root.left, record);
            record[root.val] --;
            return count;
        } else {
            int oddCount = 0;
            for (int i = 1; i <= 9; i++) {
                System.out.println("record = " + record[i]);
                if(record[i]%2 != 0) oddCount++;
            }
            record[root.val] --;
            if(oddCount>1) return 0;
            return 1;
        }
    }

    /**
     *
     *     链接：https://leetcode.cn/problems/pseudo-palindromic-paths-in-a-binary-tree/
     *
     */
    class Solution {
        public int pseudoPalindromicPaths (TreeNode root) {
            int[] counter = new int[10];
            return dfs(root, counter);
        }

        public int dfs(TreeNode root, int[] counter) {
            if (root == null) {
                return 0;
            }
            counter[root.val]++;
            int res = 0;
            if (root.left == null && root.right == null) {
                if (isPseudoPalindrome(counter)) {
                    res = 1;
                }
            } else {
                res = dfs(root.left, counter) + dfs(root.right, counter);
            }
            counter[root.val]--;
            return res;
        }

        public boolean isPseudoPalindrome(int[] counter) {
            int odd = 0;
            for (int value : counter) {
                if (value % 2 == 1) {
                    odd++;
                }
            }
            return odd <= 1;
        }
    }

}
