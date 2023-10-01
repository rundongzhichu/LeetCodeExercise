package AlgotithmExercise.Array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FilterRestaurants {

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        PriorityQueue<int[]> res = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o2[1] - o1[1] != 0) return o2[1] - o1[1];
                return o2[0] - o1[0];
            }
        });

        for (int[] restaurant :
                restaurants) {
            if((veganFriendly == 1 && restaurant[2] != veganFriendly) || restaurant[3] > maxPrice || restaurant[4] > maxDistance) continue;
            res.offer(restaurant);
        }


        List<Integer> integers = new ArrayList<>();
        while (!res.isEmpty()) {
            integers.add(res.poll()[0]);
        }
        return integers;
    }

}
