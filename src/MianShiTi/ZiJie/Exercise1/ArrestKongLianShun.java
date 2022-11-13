package MianShiTi.ZiJie.Exercise1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 基本思路：滑动窗口，双指针
 *
 * 隐藏大坑在于如果n过大 n*(n-1) 会超过整型数值存储范围，不能用int来进行计算
 */
public class ArrestKongLianShun {

    public static long C(long n){
        return n*(n-1)/2;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.valueOf(in.next());
        int D = Integer.valueOf(in.next());

        int[] positions = new int[n];
        for (int i = 0; i < n; i++) {
            positions[i] = Integer.valueOf(in.next());
        }

        long count = 0;
        for (int start = 0, end = 0; end < n; end++) {
            while (end >= 2 && positions[end] - positions[start] > D) {
                start++;
            }
            count += C(end-start);
        }
        System.out.println(count % 99997867);
    }
}

/**
 * long long C(long long n) {
 * return (n-1) * n / 2;
 * }
 * <p>
 * int main()
 * {
 * int N, D; cin>> N >> D;
 * long long count = 0;
 * vector<int> res(N);
 * for (int end = 0, begin = 0; end < N; end++) {
 * cin>> res[end];
 * while (end >= 2 && (res[end] - res[begin]) > D) {
 * begin++;//不满足则begin往前移动
 * }
 * count += C(end - begin);//由于判断一次往前移动（for循环中的end++），即可以采用每次固定首位的组合。
 * }
 * cout << count % 99997867;
 * }
 */
