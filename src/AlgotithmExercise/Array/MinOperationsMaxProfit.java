package AlgotithmExercise.Array;

public class MinOperationsMaxProfit {

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int round = 0;
        int maxProfitRound = 0;
        int profit = 0;
        int maxProfit = 0;
        int remainPeople = 0;

        while (true){
            if(round<customers.length) remainPeople += customers[round];

            if(round == 0){
                profit = (remainPeople < 4 ? remainPeople:4) * boardingCost - runningCost ;
                remainPeople = remainPeople < 4 ? 0:(remainPeople-4);
                maxProfit = profit;
                maxProfitRound = round;

            } else {
                int tempProfit =  (remainPeople < 4 ? remainPeople:4) * boardingCost - runningCost;
                if(profit + tempProfit > maxProfit) {
                    maxProfit = profit + tempProfit;
                    maxProfitRound = round;
                }
                profit = profit + tempProfit;
                remainPeople = remainPeople < 4 ? 0:(remainPeople-4);
            }
            if(remainPeople == 0 && round >= customers.length) break;
            round ++;
        }

        if(maxProfit<=0) return -1;
        return maxProfitRound + 1;
    }

}
