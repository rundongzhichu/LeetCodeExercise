package AlgotithmExercise.SegTree;

import java.util.ArrayList;
import java.util.List;

public class HandleQuery {

    /*
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
}
