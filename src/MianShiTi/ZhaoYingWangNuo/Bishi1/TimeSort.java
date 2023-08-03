package MianShiTi.ZhaoYingWangNuo.Bishi1;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class TimeSort {

    public ArrayList<String> timeSort (ArrayList<String> times) {
        // write code here
        PriorityQueue<String> pq = new PriorityQueue<String>((s1, s2) ->{
            String[] s1Arr = s1.split(":");
            String[] s2Arr = s2.split(":");

            if(!s1Arr[2].equals(s2Arr[2])) {
                return Integer.parseInt(s1Arr[2]) - Integer.parseInt(s2Arr[2]);
            }

            if(!s1Arr[1].equals(s2Arr[1])) {
                return Integer.parseInt(s1Arr[1]) - Integer.parseInt(s2Arr[1]);
            }
            return Integer.parseInt(s1Arr[0]) - Integer.parseInt(s2Arr[0]);
        });

        for (String time :
                times) {
            pq.offer(time);
        }

        int size = times.size();
        for (int i = 0; i < size; i++) {
            times.set(i, pq.poll());
        }
        return times;
    }


}
