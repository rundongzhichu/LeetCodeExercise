package AlgotithmExercise.Graph;

import java.util.*;

public class CountPairs {

    /**
     * 超时了
     *
     * @param n
     * @param edges
     * @return
     */
    public long countPairs(int n, int[][] edges) {
        Set<Integer>[] regions = new HashSet[n];

        for (int i = 0; i < n; i++) {
            regions[i] = new HashSet<>();
            regions[i].add(i);
        }

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            Set<Integer> regiona = regions[a];
            regiona.addAll(regions[b]);

            Set<Integer> regionb = regions[b];
            for (int j = 0; j < n; j++) {
                if(regions[j] == regionb) {
                    regions[j] = regiona;
                }
            }
        }
        Set<Set<Integer>> uniqueReg = new HashSet<>();
        uniqueReg.addAll(Arrays.asList(regions));
        List<Integer> elementCounts = new ArrayList<>();
        for (Set<Integer> reg : uniqueReg) {
            elementCounts.add(reg.size());
            // System.out.println("reg.size() = " + reg.size());
            if (reg.size() == n) return 0;
        }

        int res = 0;
        int size = elementCounts.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i +1; j < size; j++) {
                res += elementCounts.get(i) * elementCounts.get(j);
            }
        }
        return res;
    }


    /**
     *
     * 基本思路： 利用并查集根据图的边求联通分量
     *
     * 计算未联通的结果的时候， 就是每个联通分量的集合（假设大小为size）中每个节点不联通的数目都是n-size,
     * 我们把它加起来就得到了当前联通分量的不联通节点数。 之后我们还会遍历到和当前联通分量不联通的节点，然后计算和他们不联通的节点数目。
     * 联通的和不联通的都分别计算了各自不联通的节点，加起来以后相当于重复算了一遍，所以要除2
     *
     *
     *
     * https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/solutions/2487673/tong-ji-wu-xiang-tu-zhong-wu-fa-hu-xiang-q5eh/
     */
    class Solution {
        public long countPairs(int n, int[][] edges) {
            UnionFind uf = new UnionFind(n);
            for (int[] edge : edges) {
                int x = edge[0], y = edge[1];
                uf.union(x, y);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                res += n - uf.getSize(uf.find(i));
            }
            return res / 2;
        }
    }

    class UnionFind {
        int[] parents;
        int[] sizes;

        public UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            sizes = new int[n];
            Arrays.fill(sizes, 1);
        }

        public int find(int x) {
            if (parents[x] == x) {
                return x;
            } else {
                parents[x] = find(parents[x]);
                return parents[x];
            }
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx != ry) {
                if (sizes[rx] > sizes[ry]) {
                    parents[ry] = rx;
                    sizes[rx] += sizes[ry];
                } else {
                    parents[rx] = ry;
                    sizes[ry] += sizes[rx];
                }
            }
        }

        public int getSize(int x) {
            return sizes[x];
        }
    }


}
