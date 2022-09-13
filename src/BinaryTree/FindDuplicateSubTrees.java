package BinaryTree;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicateSubTrees {

    Map<TreeNode, Set<TreeNode>> parents = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        parents.put(root, new HashSet<>());
        findDuplicateSubtrees(root, null);

        Object[] nodes = parents.keySet().toArray();
        List<TreeNode> res = new ArrayList<>();

        Set<TreeNode> processed = new HashSet<>();

        for(int i=0; i< nodes.length-1; i++){
            if(processed.contains(nodes[i])) continue;
            for(int j=i+1; j< nodes.length;j++){
                boolean flag = false;
                if(!processed.contains(nodes[j])
                        && !parents.get(nodes[i]).contains(nodes[j])
                        && !parents.get(nodes[j]).contains(nodes[i])
                        && compare((TreeNode) nodes[i],(TreeNode)nodes[j])){
                        flag = true;
                        processed.add((TreeNode)nodes[j]);
                }
                if(flag){
                    res.add((TreeNode)nodes[i]);
                }
            }
        }
        return res;
     }

    public boolean compare(TreeNode root1, TreeNode root2) {

        if(root1 == null && root2 != null) return false;
        if(root1 != null && root2 == null) return false;
        if (root1 == null && root2 == null) return true;

        if(root1.val == root2.val){
            return compare(root1.left, root2.left) && compare(root1.right, root2.right);
        }else{
            return false;
        }
    }


    public void findDuplicateSubtrees(TreeNode root, TreeNode parent) {
        if(root!= null){
            if(parent!= null){
                Set<TreeNode> parentsList = parents.get(parent).stream().collect(Collectors.toSet());
                parentsList.add(parent);
                parents.put(root, parentsList);
            }

            findDuplicateSubtrees(root.left, root);
            findDuplicateSubtrees(root.right, root);
        }
    }


/**

    Map<String, TreeNode> seen = new HashMap<String, TreeNode>();
    Set<TreeNode> repeat = new HashSet<TreeNode>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<TreeNode>(repeat);
    }

    public String dfs(TreeNode node) {
        if (node == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        sb.append("(");
        sb.append(dfs(node.left));
        sb.append(")(");
        sb.append(dfs(node.right));
        sb.append(")");
        String serial = sb.toString();
        if (seen.containsKey(serial)) {
            repeat.add(seen.get(serial));
        } else {
            seen.put(serial, node);
        }
        return serial;
    }
*/

}
