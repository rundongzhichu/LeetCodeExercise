package BinaryTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WidthOfBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        Map<Integer, Integer> levelWidth = new HashMap<>();
        return widthOfBinaryTree(root,levelWidth,0);
    }

    public int widthOfBinaryTree(TreeNode root, Map<Integer, Integer> levelWidth, int level) {
        System.out.println("Level  "+ level+ "   val"+ root.val);
        levelWidth.put(level,levelWidth.getOrDefault(level,0)+1);

        int leftMax = -1;
        int rightMax = -1;

        if(root.left ==null){
            Set<Integer> keys = levelWidth.keySet();
            for(int l: keys){
                if(l>level){
                    levelWidth.put(l,levelWidth.get(l)+(int) Math.pow(2,l-level-1));
                }
            }
            leftMax = levelWidth.get(level);
        }else
            leftMax = widthOfBinaryTree(root.left, levelWidth, level + 1);

        if(root.right ==null){
            Set<Integer> keys = levelWidth.keySet();
            for(int l: keys){
                if(l>level){
                    levelWidth.put(l,levelWidth.get(l)+(int) Math.pow(2,l-level-1));
                }
            }
            rightMax = levelWidth.get(level);
        }else
            rightMax = widthOfBinaryTree(root.right, levelWidth, level + 1);

        return Math.max(levelWidth.get(level), Math.max(leftMax,rightMax));
    }

}
