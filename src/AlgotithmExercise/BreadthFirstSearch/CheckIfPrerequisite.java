package AlgotithmExercise.BreadthFirstSearch;

import java.util.*;

public class CheckIfPrerequisite {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, Set<Integer>> edges = new HashMap<>();

        for (int[] prereq :
                prerequisites) {
            Set<Integer> set = edges.getOrDefault(prereq[0] , new HashSet<>());
            set.add(prereq[1]);
            edges.put(prereq[0], set);
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] query :
                queries) {
            int cur = query[0];
            Set<Integer> set = new HashSet<>();
            getSubs(edges, cur, set);
            res.add(set.contains(query[1]));
        }
        return res;
    }


    public void getSubs(Map<Integer, Set<Integer>> edges, int cur, Set<Integer> set) {
        Set<Integer> subs = edges.get(cur);
        if(subs == null) return;
        for (int sub :
                subs) {
            if(!set.contains(sub)) {
                set.add(sub);
                getSubs(edges, sub, set);
            }
        }
    }
}

/**
 *
 * https://leetcode.cn/problems/course-schedule-iv/solutions/2417905/ke-cheng-biao-iv-by-leetcode-solution-mpc3/?envType=daily-question&envId=2023-09-12
 *
 */
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] g = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new ArrayList<Integer>();
        }
        int[] indgree = new int[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int[] p : prerequisites) {
            ++indgree[p[1]];
            g[p[0]].add(p[1]);
        }
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indgree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int ne : g[cur]) {
                isPre[cur][ne] = true;
                for (int i = 0; i < numCourses; ++i) {
                    isPre[i][ne] = isPre[i][ne] | isPre[i][cur];
                }
                --indgree[ne];
                if (indgree[ne] == 0) {
                    queue.offer(ne);
                }
            }
        }
        List<Boolean> res = new ArrayList<Boolean>();
        for (int[] query : queries) {
            res.add(isPre[query[0]][query[1]]);
        }
        return res;
    }
}
