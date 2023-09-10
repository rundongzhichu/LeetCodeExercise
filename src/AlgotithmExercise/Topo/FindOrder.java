package AlgotithmExercise.Topo;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class FindOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        List<Integer>[] edges = new List[numCourses];

        for(int i=0;i<numCourses;i++) edges[i]=new ArrayList<>();
        for (int[] pre :
                prerequisites) {
            edges[pre[0]].add(pre[1]);
            in[pre[1]]++;
        }

        List<Integer> res = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(in[i]==0){    //将入度为0的点入队
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.add(cur);
            List<Integer> eg = edges[cur];
            for (int e :
                    eg) {
                in[e]--;
                if(in[e]==0){    //将入度为0的点入队
                    queue.add(e);
                }
            }
        }

        boolean flag = true;
        for (int i = 0; i < numCourses; i++) {
            flag = flag && in[i] ==0;
        }

        int size = res.size();
        int[] resArr = new int[size];
        for (int i = 0; i < size; i++) {
            resArr[i] = res.get(size-1-i);
        }

        if(flag) return resArr;
        return new int[0];
    }
}
