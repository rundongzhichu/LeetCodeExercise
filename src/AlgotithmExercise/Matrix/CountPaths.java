package AlgotithmExercise.Matrix;

import java.util.*;

public class CountPaths {

    private TreeMap<Integer, Integer> timePathes = new TreeMap<>();

    private double remainNum = 10E9 + 7;

    /**
     *
     * 经典的Dijkstra 算法
     * 基本思路：邻接矩阵+记忆化搜索 深度优先遍历
     * 这个做法超出时间限制了
     *
     * @param n
     * @param roads
     * @return
     */
    public int countPaths(int n, int[][] roads) {
        int[][] adjacencyMatrix = new int[n][n];
        for (int[] road :
                roads) {
            adjacencyMatrix[road[0]][road[1]] = road[2];
            adjacencyMatrix[road[1]][road[0]] = road[2];
        }

        countPaths(0, adjacencyMatrix, n,0, new HashSet<>());
        return timePathes.pollFirstEntry().getValue();
    }

    public void countPaths(int curPlace, int[][] adjacencyMatrix, int n, int timeConsume, Set<Integer> markedPlaces) {
        if(curPlace == n-1) {
            int count = (int) ((timePathes.getOrDefault(timeConsume, 0) + 1) % remainNum);
            timePathes.put(timeConsume, count);
            return;
        }
        if(markedPlaces.size() >= n) return;
        int[] toPlaces = adjacencyMatrix[curPlace];
        for (int i = 0; i < n; i++) {
            if(!markedPlaces.contains(i) && toPlaces[i] != 0) {
                markedPlaces.add(i);
                countPaths(i, adjacencyMatrix, n, timeConsume + adjacencyMatrix[curPlace][i], markedPlaces);
                markedPlaces.remove(i);
            }
        }
    }


    /*
    链接：https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination/solutions/951921/dao-da-mu-de-di-de-fang-an-shu-by-leetco-5ptp/



     */
    class Solution {
        public int countPaths(int n, int[][] roads) {
            int mod = 1000000007;

            // e[i]记录的是当前index i，也就是i对应的place，相邻的节点以及和他们之间的距离
            List<int[]>[] e = new List[n];
            for (int i = 0; i < n; i++) {
                e[i] = new ArrayList<int[]>();
            }
            for (int[] road : roads) {
                int x = road[0], y = road[1], t = road[2];
                e[x].add(new int[]{y, t});
                e[y].add(new int[]{x, t});
            }

            // dis[i] 用来记录源节点也就是0位置的节点到i对应的节点的最短路径的距离和，也就是边的权重和
            long[] dis = new long[n];
            Arrays.fill(dis, Long.MAX_VALUE);

            // ways[i]记录的是源节点到i节点的最短路径的数目
            int[] ways = new int[n];

            // pq 每个元素记录的是源节点到当前节点的最短距离和对应的节点编号，由于从小到大排列所以，第一个元素是源节点到当前节点的最小距离
            PriorityQueue<long[]> pq = new PriorityQueue<long[]>((a, b) -> Long.compare(a[0], b[0]));
            pq.offer(new long[]{0, 0});
            dis[0] = 0;
            //0->0至少一条路径，赋初值
            ways[0] = 1;

            // 这里通过广度优先遍历的方式完成对于图的每个节点的遍历
            while (!pq.isEmpty()) {
                long[] arr = pq.poll();
                long t = arr[0];
                int u = (int) arr[1];
                if (t > dis[u]) {
                    continue;
                }
                for (int[] next : e[u]) {
                    int v = next[0], w = next[1];
                    if (t + w < dis[v]) {
                        dis[v] = t + w;
                        // 如果从源节点通过u节点到v路径更短，那么更新dis[v]的同时更新ways[v]
                        ways[v] = ways[u];
                        pq.offer(new long[]{t + w, v});
                    } else if (t + w == dis[v]) {
                        // 累加得到最短路径数目
                        ways[v] = (ways[u] + ways[v]) % mod;
                    }
                }
            }
            return ways[n - 1];
        }
    }

}
