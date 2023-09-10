package AlgotithmExercise.tanxin;

public class RepairCars {

    private long res = Long.MAX_VALUE;


    public long repairCars(int[] ranks, int cars) {
        repairCars(ranks, cars, 0, 0);
        return res;
    }

    public void repairCars(int[] ranks, int cars, int index, long maxTime) {
        if(cars == 0) {
            res = Math.min(res, maxTime);
            return;
        }
        if(index >= ranks.length) return;
        for (int i = 0; i <= cars; i++) {
            repairCars(ranks, cars - i, index+1, Math.max(maxTime, ranks[index]*i*i));
        }
    }
}


/**
 * 这里的核心思想采用的是贪心
 *
 * 所有的修车方案中，每种方案最后消费的时间一定是该方案师傅修车的最长时间，
 * 我们只要假定一个时间T他们可以修完车，然后对T/rank[0]开方求得0对应的师傅修车的数目，将每个师傅在t时间能够修出的车的数目cnt，
 * 最后cnt一定满足cnt》=cars，才是可行的一个时间，最后通过二分降低时间，直到找到最小的时间
 *
 */
class Solution {
    public long repairCars(int[] ranks, int cars) {
        long l = 1, r = 1l * ranks[0] * cars * cars;
        while (l < r) {
            long m = l + r >> 1;
            if (check(ranks, cars, m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public boolean check(int[] ranks, int cars, long m) {
        long cnt = 0;
        for (int x : ranks) {
            cnt += (long) Math.sqrt(m / x);
        }
        return cnt >= cars;
    }
}
