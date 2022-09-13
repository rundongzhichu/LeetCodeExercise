package MianShiTi.TenXunYinYue.Exercise1;

public class MinCnt {
    public int minCnt (String s) {
        // write code here
        char[] schars = s.toCharArray();

        int len =schars.length;

        int cnt=0;

        for(int i=1; i < len;i--){
            if(schars[i] == '1'){
                cnt++;
            }
        }
        return cnt;
    }
}
