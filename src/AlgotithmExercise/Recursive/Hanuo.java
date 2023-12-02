package AlgotithmExercise.Recursive;

public class Hanuo {

    /**
     * https://blog.csdn.net/qq_37873310/article/details/80461767
     *
     * 基本思想：将最左边柱子的n个圆盘看作是最底下的圆盘n 和上面（n-1）个圆盘作为整体的两个圆盘，两个圆盘移动到最右边的基本思路，就是整体的基本思路
     * n个圆盘处理的时候，我们就一步步递归，直到递归到终止条件，具体
     *
     *
     * @param n
     * @param l
     * @param m
     * @param r
     */
    public void HanNuo(int n, char l, char m, char r) {
        if(n==1) {
            System.out.println(String.format("move plate %d from %c to %c", n, l, r));
        } else {
            // 将l柱子上的从上到下n-1个盘子移到m， 以r柱子为辅助
            HanNuo(n-1, l, r, m);
            System.out.println(String.format("move plate %d from %c to %c", n, l, r));
            // 将m柱子上的n-1个盘子移到r， 以l柱子为辅助
            HanNuo(n-1, m, l, r);
        }
    }


}
