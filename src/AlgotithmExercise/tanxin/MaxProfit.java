package AlgotithmExercise.tanxin;

public class MaxProfit {


    /**
     * 核心思想： 江fee算在买入的时候， 然后令 buy = prices[i]提供一次卖出返回操作，如果之后的股价高于现在的股价，那么重新按照之后的股价计算。
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i] ;
            }
        }
        return profit;
    }

}
