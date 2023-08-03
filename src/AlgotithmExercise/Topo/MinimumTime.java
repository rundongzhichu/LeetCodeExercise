package AlgotithmExercise.Topo;

import java.util.*;

public class MinimumTime {

    /*
    这个是我自己写的，有问题
     */
    public int minimumTime(int n, int[][] relations, int[] time) {

        Map<Integer, Set<Integer>> reachMatrix = new HashMap<>();
        Map<Integer, Set<Integer>> subMatrix = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            reachMatrix.put(i, new HashSet<>());
            subMatrix.put(i, new HashSet<>());
        }

        for (int i = 0; i < relations.length; i++) {
            Set<Integer> s = reachMatrix.get(relations[i][1]);
            s.add(relations[i][0]);
            reachMatrix.put(relations[i][1], s);

            s = subMatrix.get(relations[i][0]);
            s.add(relations[i][1]);
            subMatrix.put(relations[i][0], s);
        }

        int res = 0;
        Set<Integer> removed = new HashSet<>();
        while (removed.size() != n) {
            Set<Integer> keys = reachMatrix.keySet();
            for (int key :
                    keys) {
                clearPreCourse(removed, reachMatrix.get(key));
            }

            int maxNext = 0;
            int maxKey = 0;
            for (int key :
                    keys) {
                if(!removed.contains(key) && reachMatrix.get(key).isEmpty()) {
                    Set<Integer> s = subMatrix.get(key);
                    for (int k1 :
                            s) {
                        maxNext = Math.max(maxNext, time[k1-1]);
                    }
                }
            }

            int maxTime = 0;
            for (int key :
                    keys) {
                if(!removed.contains(key) && reachMatrix.get(key).isEmpty()) {
                    Set<Integer> s = subMatrix.get(key);
                    boolean canRemove = true;
                    for (int k1 :
                            s) {
                        if(time[key-1] + time[k1-1] >= maxTime){
                            canRemove = false;
                            break;
                        }
                    }
                    if(canRemove) {
                        maxTime = Math.max(time[key-1], maxTime);
                        removed.add(key);
                    }
                }
            }
            System.out.println("maxTime = " + maxTime);
            res += maxTime;
        }
        return res;
    }

    public void clearPreCourse(Set<Integer> removed, Set<Integer> preCour) {
        for (int cor :
                removed) {
            if(!preCour.isEmpty())
                preCour.remove(cor);
        }
    }

    class Node {
        private int val;

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return val == node.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }
    }

    /*
    这个是队列+拓补排序的算法
    https://leetcode.cn/problems/parallel-courses-iii/solutions/2357818/bing-xing-ke-cheng-iii-by-leetcode-solut-aw43/
     */
    class Solution {
        public int minimumTime(int n, int[][] relations, int[] time) {
            //拓扑排序
            List<Integer>[] g=new List[n];
            for(int i=0;i<n;i++)g[i]=new ArrayList<>();
            int[] in=new int[n];   //存储每个节点的入度
            for(int[] relation:relations){
                g[relation[0]-1].add(relation[1]-1);   //存储每个节点的nextCourse
                in[relation[1]-1]++;
            }
            Queue<Integer> queue=new ArrayDeque<>();
            int[] finishTime=new int[n];    //存储每个课程学完所花的最少月份
            int ans=0;
            for(int i=0;i<n;i++){
                if(in[i]==0){    //将入度为0的点入队
                    queue.add(i);
                    finishTime[i]=time[i];
                }
            }
            while(!queue.isEmpty()){   //拓扑排序
                int cur=queue.poll();
                ans=Math.max(ans,finishTime[cur]);
                for(int next:g[cur]){
                    in[next]--;
                    finishTime[next]=Math.max(finishTime[next],finishTime[cur]+time[next]);
                    if(in[next]==0)queue.add(next);
                }
            }
            return ans;
        }
    }

}
