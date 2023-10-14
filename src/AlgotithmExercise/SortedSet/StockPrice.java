package AlgotithmExercise.SortedSet;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class StockPrice {

    private PriorityQueue<Integer> max;

    private PriorityQueue<Integer> min;

    private TreeMap<Integer, Integer> priceMap;

    // todo 我这个解法漏掉了一些考虑

    /**
     * 这个做法的问题在于，如果两个timesatmp 价格一样的时候， max和min只会记录一次，就会漏掉另一个，导致计算max 和min的时候出现错误结果
     */
    public StockPrice() {
        max = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        min = new PriorityQueue<>();
        priceMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public void update(int timestamp, int price) {
        if(priceMap.containsKey(timestamp)) {
            if(max.peek().equals(priceMap.get(timestamp))) {
                max.poll();
            }

            if(min.peek().equals(priceMap.get(timestamp))) {
                min.poll();
            }
        }
        max.offer(price);
        min.offer(price);
        priceMap.put(timestamp, price);
    }

    public int current() {
        return priceMap.firstEntry().getValue();
    }

    public int maximum() {
        return max.peek();
    }

    public int minimum() {
        return min.peek();
    }


    /**
     *
     * https://leetcode.cn/problems/stock-price-fluctuation/solutions/1225718/gu-piao-jie-ge-bo-dong-by-leetcode-solut-rwrb/
     *
     */
    class StockPrice1 {
        int maxTimestamp;
        HashMap<Integer, Integer> timePriceMap;
        TreeMap<Integer, Integer> prices;

        public StockPrice1() {
            maxTimestamp = 0;
            timePriceMap = new HashMap<Integer, Integer>();
            prices = new TreeMap<Integer, Integer>();
        }

        public void update(int timestamp, int price) {
            maxTimestamp = Math.max(maxTimestamp, timestamp);
            int prevPrice = timePriceMap.getOrDefault(timestamp, 0);
            timePriceMap.put(timestamp, price);
            if (prevPrice > 0) {
                prices.put(prevPrice, prices.get(prevPrice) - 1);
                if (prices.get(prevPrice) == 0) {
                    prices.remove(prevPrice);
                }
            }
            prices.put(price, prices.getOrDefault(price, 0) + 1);
        }

        public int current() {
            return timePriceMap.get(maxTimestamp);
        }

        public int maximum() {
            return prices.lastKey();
        }

        public int minimum() {
            return prices.firstKey();
        }
    }

}
