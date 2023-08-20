package AlgotithmExercise.DynamicProgramming;

public class MaxSlicesPizza {

    private int maxSum = 0;
    public int maxSizeSlices(int[] slices) {
        maxSizeSlices(slices, 0, 0, slices.length);
        return maxSum;
    }

    public void maxSizeSlices(int[] slices, int sum, int count, int len) {
        if(count == len) {
            maxSum = Math.max(sum, maxSum);
            return ;
        }
        for (int i = 0; i < len; i++) {
            if(slices[i] != 0) {
                int a = slices[i];
                slices[i] = 0;
                int k1=i;
                while (slices[k1] == 0) {
                    k1 = (k1-1 +len)%len;
                }
                int b = slices[k1];
                slices[k1] =0;
                int k2 = i;
                while (slices[k2] ==0) {
                    k2 = (k2+1)%len;
                }
                int c = slices[k2];
                slices[k2] = 0;
//               System.out.println("a = " + a);
                maxSizeSlices(slices, sum +a, count +3, len);
                slices[i] = a;
                slices[k1] = b;
                slices[k2] = c;
            }
        }
    }


    /**
     * 最优的解法还是动态规划
     *
     *
     *
     */

}
