package MianShiTi.ZhaoYingWangNuo.Bishi1;

public class CountNum {

    public static int countNum (int L, int R, int x) {
        // write code here
        int count = 0;
        for (int i = L; i <= R; i++) {
            int temp = i;
            if(temp/10 == 0) {
                if (temp == x) {
                    count++;
                }
                continue;
            }
            while (temp/10 != 0) {
                if(temp%10 == x){
                    count++;
                }
                temp /=10;
            }
            if (temp == x) {
                count++;
            }
        }
        return count;
    }
}
