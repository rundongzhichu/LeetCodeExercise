package AlgotithmExercise.BinaryTree;

import java.util.*;

public class FindMinHeightTrees {

    public static void main(String[] args) {

    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, Set<Integer>> edgeMap = new HashMap<>();
        List<Integer> roots = new ArrayList<>();

        for (int i = 0; i < edges[0].length; i++) {
            putToEdgeMap(edgeMap, edges[i][0], edges[i][1]);
            putToEdgeMap(edgeMap, edges[i][1], edges[i][0]);
        }

        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> rootHeight = new HashMap<>();
        int height = Integer.MAX_VALUE;

        System.out.println(edgeMap.get(3));

        for(int i=0; i<n; i++){
            visited.clear();
            visited.add(i);
            int curTreeHeight = calculateHeight(i, edgeMap, visited);
            height = Math.min(curTreeHeight, height);
            rootHeight.put(i, curTreeHeight);
        }

        List<Integer> res = new ArrayList<>();
        for(int root: rootHeight.keySet()){
            System.out.println("root: " + root + " height:" + rootHeight.get(root));
            if(rootHeight.get(root) == height){
                res.add(root);
            }
        }
        return res;
    }

    public void putToEdgeMap(Map<Integer, Set<Integer>> edgeMap, int k, int v){
        if(edgeMap.containsKey(k)){
            edgeMap.get(k).add(v);
            return;
        }
        HashSet<Integer> node = new HashSet<>();
        node.add(v);
        edgeMap.put(k, node);
    }

    public int calculateHeight(int root, Map<Integer, Set<Integer>> edgeMap, Set<Integer> visited){
        Set<Integer> childNodes = edgeMap.get(root);
        int height = 0;
        if(childNodes == null) return height;
        for(int child: childNodes){
            if (!visited.contains(child)) {
                visited.add(child);
                height = Math.max(1 + calculateHeight(child, edgeMap, visited), height);
            }
        }
        return height;
    }

}
