package MianShiTi.JiuKun;

import java.util.*;

public class ExcuteTask {

/*
3
[[1, 2], [0, 1]]
 */


    public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别
            int taskNum = Integer.parseInt(in.nextLine());
            List<int[]> preRequistes = getPrequsite(in.nextLine());

            Map<Integer, Set<Integer>> preququiMap = new HashMap<>();
            int size = preRequistes.size();
            for (int i = 0; i < size; i++) {
                int pre = preRequistes.get(i)[0];
                int sub = preRequistes.get(i)[1];
                Set<Integer> pres = preququiMap.getOrDefault(sub, new HashSet<>());
                pres.add(pre);
                preququiMap.put(sub, pres);
            }

            Set<Integer> removed = new HashSet<>();
            Set<Integer> keys = preququiMap.keySet();
            Deque<Integer> queue = new LinkedList<>();
            List<Integer> res = new ArrayList<>();
            while (res.size() < taskNum) {
                for (int i = 0; i < taskNum; i++) {
                    if(!removed.contains(i)) {
                        if(preququiMap.containsKey(i)) {
                            if(preququiMap.get(i).size() == 0) queue.offer(i);
                        } else {
                            queue.offer(i);
                        }
                    }
                }

                if(queue.isEmpty()){
                    res.clear();
                    break;
                }

                while (!queue.isEmpty()) {
                    int exetask = queue.poll();
                    removed.add(exetask);
                    res.add(exetask);
                    for (int key :
                            keys) {
                        preququiMap.get(key).remove(exetask);
                    }
                }
            }
            printRes(res);
        }

        public static List<int[]> getPrequsite(String s) {
            int size = s.length();
            s = s.substring(1, size -1);
            String[] subArr = s.split(",");
            List<int[]> res = new ArrayList<>();
            for (int i = 0; i < subArr.length; ) {
                int[] relation = new int[]{
                        Integer.valueOf(subArr[i+1].trim().split("]")[0]),
                        Integer.valueOf(subArr[i].trim().split("\\[")[1])};
                i += 2;
                res.add(relation);
            }
            return res;
        }

        public static void printRes(List<Integer> res) {
            if(res.isEmpty()) System.out.print("[]");
            int size = res.size();
            System.out.print("[" + res.get(0) + ",");
            for (int i = 1; i < size -1; i++) {
                System.out.print(" " + res.get(i) + ",");
            }
            System.out.print(" " +res.get(size - 1) + "]");
        }

}
