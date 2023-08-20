package AlgotithmExercise.SegTree;

import java.util.ArrayList;
import java.util.List;

public class HandleQuery {

    /*
    todo: 理解线段树的思想
        核心思想： 线段树
        https://leetcode.cn/problems/handling-sum-queries-after-update/solutions/2356392/geng-xin-shu-zu-hou-chu-li-qiu-he-cha-xu-kv6u/
     */
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        List<Long> res = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            if(queries[i][0] == 1) {
                operation1(nums1, queries[i][1], queries[i][2]);
            } else if(queries[i][0] == 2){
                operation2(nums1, nums2, queries[i][1]);
            } else {
                res.add(operation3(nums2));
            }
        }

        int resLen = res.size();
        long[] resArr = new long[res.size()];
        for (int i = 0; i < resLen; i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    public void operation1(int[] nums1, int l, int r){
        for (int i = l; i <= r; i++) {
            nums1[i] = nums1[i]^1;
        }
    }

    public void operation2(int[] nums1, int[] nums2, int p) {
        if(p==0) return;
        for (int i = 0; i < nums2.length; i++) {
            nums2[i] += nums1[i]*p;
        }
    }

    public long operation3(int[] nums2) {
        long res = 0;
        for (int i = 0; i < nums2.length; i++) {
            res += nums2[i];
        }
        return res;
    }


    /*
        官方解答
     */
    class Solution {
        public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
            int n = nums1.length;
            int m = queries.length;
            SegTree tree = new SegTree(nums1);

            long sum = 0;
            for (int num : nums2) {
                sum += num;
            }
            List<Long> list = new ArrayList<Long>();
            for (int i = 0; i < m; i++) {
                if (queries[i][0] == 1) {
                    int l = queries[i][1];
                    int r = queries[i][2];
                    tree.reverseRange(l, r);
                } else if (queries[i][0] == 2) {
                    sum += (long) tree.sumRange(0, n - 1) * queries[i][1];
                } else if (queries[i][0] == 3) {
                    list.add(sum);
                }
            }
            long[] ans = new long[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }
    }

    class SegTree {
        private SegNode[] arr;

        public SegTree(int[] nums) {
            int n = nums.length;
            arr = new SegNode[n * 4 + 1];
            build(1, 0, n - 1, nums);
        }

        public int sumRange(int left, int right) {
            return query(1, left, right);
        }

        public void reverseRange(int left, int right) {
            modify(1, left, right);
        }

        public void build(int id, int l, int r, int[] nums) {
            arr[id] = new SegNode();
            arr[id].l = l;
            arr[id].r = r;
            arr[id].lazytag = false;
            if(l == r) {
                arr[id].sum = nums[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(2 * id, l, mid, nums);
            build(2 * id + 1, mid + 1, r, nums);
            arr[id].sum = arr[2 * id].sum + arr[2 * id + 1].sum;
        }

        /* pushdown函数：下传懒标记，即将当前区间的修改情况下传到其左右孩子结点 */
        public void pushdown(int x) {
            if(arr[x].lazytag) {
                arr[2 * x].lazytag = !arr[2 * x].lazytag;
                arr[2 * x].sum = arr[2 * x].r - arr[2 * x].l + 1 - arr[2 * x].sum;
                arr[2 * x + 1].lazytag = !arr[2 * x + 1].lazytag;
                arr[2 * x + 1].sum = arr[2 * x + 1].r - arr[2 * x + 1].l + 1 - arr[2 * x + 1].sum;
                arr[x].lazytag = false;
            }
        }

        /* 区间修改 */
        public void modify(int id, int l, int r) {
            if (arr[id].l >= l && arr[id].r <= r) {
                arr[id].sum = (arr[id].r - arr[id].l + 1) - arr[id].sum;
                arr[id].lazytag = !arr[id].lazytag;
                return;
            }
            pushdown(id);
            int mid = (arr[id].l + arr[id].r) >> 1;
            if (arr[2 * id].r >= l) {
                modify(2 * id, l, r);
            }
            if(arr[2 * id + 1].l <= r) {
                modify(2 * id + 1, l, r);
            }
            arr[id].sum = arr[2 * id].sum + arr[2 * id + 1].sum;
        }

        /* 区间查询 */
        public int query(int id, int l, int r) {
            if (arr[id].l >= l && arr[id].r <= r) {
                return arr[id].sum;
            }
            if (arr[id].r < l || arr[id].l > r) {
                return 0;
            }
            pushdown(id);
            int res = 0;
            if (arr[2 * id].r >= l) {
                res += query(2 * id, l, r);
            }
            if (arr[2 * id + 1].l <= r) {
                res += query(2 * id + 1, l, r);
            }
            return res;
        }
    }

    class SegNode {
        public int l, r, sum;
        public boolean lazytag;

        public SegNode() {
            this.l = 0;
            this.r = 0;
            this.sum = 0;
            this.lazytag = false;
        }
    }
//
//    作者：力扣官方题解
//    链接：https://leetcode.cn/problems/handling-sum-queries-after-update/solutions/2356392/geng-xin-shu-zu-hou-chu-li-qiu-he-cha-xu-kv6u/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}
