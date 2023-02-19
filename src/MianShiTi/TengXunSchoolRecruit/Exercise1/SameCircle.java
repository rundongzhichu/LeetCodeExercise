package MianShiTi.TengXunSchoolRecruit.Exercise1;

import java.util.*;

public class SameCircle {

    /**
     * 利用并查集思想来做
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<Integer> maxCircleNum = new ArrayList<>();
        int n = Integer.valueOf(in.next());
        for (int i = 0; i < n; i++) {
            int relations = Integer.valueOf(in.next());

            Map<Integer, Integer> relationMap = new HashMap<>();
            // 初始化所有的节点的父亲节点都为本身，
            for (int j = 1; j <= Math.pow(10, 5); j++) {
                relationMap.put(j, j);
            }

            Map<Integer, Integer> statistic = new HashMap<>();
            for (int j = 0; j < relations; j++) {
                int x = Integer.valueOf(in.next()), y = Integer.valueOf(in.next());
                unionRelations(relationMap, x, y, statistic);
            }

            int maxVal = 0;
            for (int key: statistic.keySet()) {
                maxVal = Math.max(maxVal, statistic.get(key));
            }
            maxCircleNum.add(maxVal);
        }

        int size = maxCircleNum.size();
        for (int i = 0; i < size-1; i++) {
            System.out.println(maxCircleNum.get(i));
        }
        System.out.println(maxCircleNum.get(size-1));
    }

    public static int findFather(Map<Integer, Integer> relationMap, int x) {
        int father = relationMap.get(x);
        if(father != x) {
            father = findFather(relationMap, father);
        }

        while (x!= father) {
            int direct_father = relationMap.get(x);
            relationMap.put(x, father);
            x = direct_father;
        }
        return father;
    }

    public static void unionRelations(Map<Integer, Integer> relationMap, int x, int y, Map<Integer, Integer> sizeMap){
        int xFather = findFather(relationMap, x);
        int yFather = findFather(relationMap, y);

        if(xFather != yFather) {
            relationMap.put(xFather, yFather);
            sizeMap.put(yFather, sizeMap.getOrDefault(xFather, 1) + sizeMap.getOrDefault(yFather, 1));
            sizeMap.remove(xFather);
        }
    }


/**
 * 递归处理这种算法要超时
 */
//    public class SameCircle {
//
//        public static void main(String[] args) {
//            Scanner in = new Scanner(System.in);
//
//            List<Integer> maxCircleNum = new ArrayList<>();
//            int n = Integer.valueOf(in.next());
//            for (int i = 0; i < n; i++) {
//                int relations = Integer.valueOf(in.next());
//                Map<Integer, Set<Integer>> collarMatrix = new HashMap<>();
//                Set<Integer> nums = new HashSet<>();
//                for (int j = 0; j < relations; j++) {
//                    int x = Integer.valueOf(in.next()), y = Integer.valueOf(in.next());
//                    addNewRelation(collarMatrix, x, y);
//                    addNewRelation(collarMatrix, y, x);
//                    nums.add(x);
//                    nums.add(y);
//                }
//
//                int maxCircle = 0;
//                Set<Integer> circle = new HashSet<>();
//                for (int num : nums) {
//                    if(circle.contains(num)) continue;
//                    circle.clear();
//                    generateMaxCircle(collarMatrix, circle, num);
//                    maxCircle = Math.max(circle.size(), maxCircle);
//                }
//                maxCircleNum.add(maxCircle);
//            }
//
//            int size = maxCircleNum.size();
//            for (int i = 0; i < size-1; i++) {
//                System.out.println(maxCircleNum.get(i));
//            }
//            System.out.println(maxCircleNum.get(size-1));
//        }
//
//        public static void addNewRelation(Map<Integer, Set<Integer>> collarMatrix, int x, int y) {
//            if(collarMatrix.containsKey(x)) {
//                collarMatrix.get(x).add(y);
//            } else {
//                Set<Integer> circle = new HashSet<>();
//                circle.add(y);
//                collarMatrix.put(x, circle);
//            }
//        }
//
//        public static void generateMaxCircle(Map<Integer, Set<Integer>> collarMatrix, Set<Integer> circle, int x) {
//            for (int y : collarMatrix.get(x)) {
//                if(!circle.contains(y)) {
//                    circle.add(y);
//                    generateMaxCircle(collarMatrix, circle, y);
//                }
//            }
//        }
//
//    }

}
