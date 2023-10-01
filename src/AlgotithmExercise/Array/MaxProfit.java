package AlgotithmExercise.Array;

public class MaxProfit {

    public int maxProfit(int[] prices) {
        int minVal = prices[0];
        int maxRes = 0;
        for (int i = 1; i < prices.length; i++) {
            maxRes = Math.max(maxRes, prices[i] - minVal);
            minVal = Math.min(prices[i], minVal);
        }
        return  maxRes;
    }

}
