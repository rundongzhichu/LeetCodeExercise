package AlgotithmExercise.BinaryTree;

import java.util.*;



public class LockingTree {


    Map<Integer, Integer> lockMap;

    int[] parent;

    public LockingTree(int[] parent) {
        this.parent = parent;
        this.lockMap = new HashMap<>();
    }

    public boolean lock(int num, int user) {
        if(lockMap.containsKey(num)) return  false;
        lockMap.put(num, user);
        return true;
    }

    public boolean unlock(int num, int user) {
        if(!lockMap.containsKey(num) || lockMap.get(num) != user) return false;
        lockMap.remove(num);
        return true;
    }

    public boolean upgrade(int num, int user) {
        int pidx = parent[num];
        while (true) {
            if(pidx == -1) break;
            if(lockMap.containsKey(parent[pidx])) {
                return false;
            }
            pidx = parent[pidx];
        }

        Set<Integer> parents = new HashSet<>();
        parents.add(num);
        int lckSubs = 0;
        for (int i = num ; i < parent.length; i++) {
            if(parents.contains(parent[i])) {
                if(lockMap.containsKey(i)) {
                    lockMap.remove(i);
                    lckSubs++;
                }
                parents.add(i);
            }
        }
        if (lckSubs >= 1) {
            lockMap.put(num, user);
            return true;
        }
        return false;
    }


    /**
     * https://leetcode.cn/problems/operations-on-tree/solutions/2451105/shu-shang-de-cao-zuo-by-leetcode-solutio-60an/?envType=daily-question&envId=2023-09-23
     *
     * 这道题的理解上就是传入的parent数组中每个元素在数组中的位置不是对应到二叉树中对应的树节点位置，而是随机放入，只是记录了当前节点的父节点是哪个
     *
     * 可以把数组看成二叉树node节点数组，每个node记录了自己的父节点位置
     *
     */
    class LockingTree1 {
        private int[] parent;
        private int[] lockNodeUser;
        private List<Integer>[] children;

        public LockingTree1(int[] parent) {
            int n = parent.length;
            this.parent = parent;
            this.lockNodeUser = new int[n];
            Arrays.fill(this.lockNodeUser, -1);
            this.children = new List[n];
            for (int i = 0; i < n; i++) {
                this.children[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < n; i++) {
                int p = parent[i];
                if (p != -1) {
                    children[p].add(i);
                }
            }
        }

        public boolean lock(int num, int user) {
            if (lockNodeUser[num] == -1) {
                lockNodeUser[num] = user;
                return true;
            }
            return false;
        }

        public boolean unlock(int num, int user) {
            if (lockNodeUser[num] == user) {
                lockNodeUser[num] = -1;
                return true;
            }
            return false;
        }

        public boolean upgrade(int num, int user) {
            boolean res = lockNodeUser[num] == -1 && !hasLockedAncestor(num) && checkAndUnlockDescendant(num);
            if (res) {
                lockNodeUser[num] = user;
            }
            return res;
        }

        private boolean hasLockedAncestor(int num) {
            num = parent[num];
            while (num != -1) {
                if (lockNodeUser[num] != -1) {
                    return true;
                }
                num = parent[num];
            }
            return false;
        }

        private boolean checkAndUnlockDescendant(int num) {
            boolean res = lockNodeUser[num] != -1;
            lockNodeUser[num] = -1;
            for (int child : children[num]) {
                res |= checkAndUnlockDescendant(child);
            }
            return res;
        }
    }

}