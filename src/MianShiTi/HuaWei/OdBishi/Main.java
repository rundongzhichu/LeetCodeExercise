package MianShiTi.HuaWei.OdBishi;

import java.util.*;

public class Main {

    public static void main(String[] args) {


    }

    public List<List<Integer>> subArraySumEqualZero(int[] nums){
        Arrays.sort(nums);
        int start = 0, end = nums.length-1;

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i < nums.length -1; i++) {
            start = 0;
            end = nums.length-1;
            while (start < i && end > i) {
                int sum = nums[start] + nums[i] + nums[end];
                if(sum == 0) {
                    res.add(Arrays.asList(nums[start], nums[i], nums[end]));
                    break;
                }
                if(sum<0){
                    while (start < i && nums[start] == nums[start+1]) start++;
                    start++;
                } else {
                    while (end > i && nums[end] == nums[end-1]) end--;
                    end--;
                }
            }

            while (i+1<nums.length -1 && nums[i+1] == nums[i]){
                i++;
            }
        }
        return res;
    }

}





/**
 *
 *

 public class Main {

 public static void main(String[] args) {
 Scanner in = new Scanner(System.in);
 int M = Integer.valueOf(in.next());
 int N = Integer.valueOf(in.next());

 Map<Integer, Integer> mMaps = new HashMap<>();
 Map<Integer, Integer> nMaps = new HashMap<>();
 int[] marr = new int[M];
 int[] narr = new int[N];
 for (int i = 0; i < M; i++) {
 int m = Integer.valueOf(in.next());
 mMaps.put(m, mMaps.getOrDefault(m,0)+1);
 }

 for (int i = 0; i < N; i++) {
 int n = Integer.valueOf(in.next());
 nMaps.put(n, nMaps.getOrDefault(n, 0)+1);
 }

 int count = 0;

 for (int mKey:mMaps.keySet()){
 for (int nKey :
 nMaps.keySet()) {
 if(mKey == nKey) count += mMaps.get(mKey)*nMaps.get(nKey);
 }
 }

 System.out.println(count);
 }

 }


public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String text1 = in.nextLine();
        String text2 = in.nextLine();
        System.out.println(longestChildString(text1, text2));
    }

    public static String longestChildString(String text1, String text2) {

        if (text1 == null && text2 == null) {
            return "";
        }

        int lastIndex = 0;
        int len = 0;

        char[] t1chars = text1.toCharArray();
        char[] t2chars = text2.toCharArray();

        int[][] dp = new int[t1chars.length + 1][t2chars.length + 1];

        for (int i = 1; i <= t1chars.length; i++) {
            for (int j = 1; j <= t2chars.length; j++) {
                if (t1chars[i - 1] == t2chars[j - 1]) {
                    System.out.println("t1chars[i-1] = " + t1chars[i - 1]);
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (len < dp[i][j]) {
                        len = dp[i][j];
                        lastIndex = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        if (len == 0) return "";
        System.out.println("len = " + len);
        System.out.println("lastIndex = " + lastIndex);
        StringBuilder sb = new StringBuilder();
        for (int i = lastIndex - len; i < lastIndex; i++) {
            sb.append(t1chars[i]);
        }
        return sb.toString();
        //        return text1.substring(lastIndex - len, len);
    }

}


 */



/**
public class Main {

    static class Pair{
        private String s;
        private int count;

        public Pair(String s, int count) {
            this.s = s;
            this.count = count;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();


        String[] strs = str.split(" ");
        for (int i = 0; i < strs.length; i++) {
            strs[i] = sortByDict(strs[i]);
        }

        Map<String, Integer> stringCount = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            stringCount.put(strs[i], stringCount.getOrDefault(strs[i], 0)+1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.count != o2.count) return o2.count - o1.count;
                if(o1.s.length() != o2.s.length()) return o1.s.length() - o2.s.length();

                int len =  o1.s.length();
                char c1 = 0, c2=0;
                for (int i = 0; i < len; i++) {
                    c1 = o1.s.charAt(i);
                    c2 = o2.s.charAt(i);
                    if(c1 != c2) break;
                }
                return c1 - c2;
            }
        });

        for (String key:stringCount.keySet()) {
            Pair pair = new Pair(key, stringCount.get(key));
            pq.offer(pair);
        }

        int size = pq.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            Pair p = pq.poll();
            for (int j = 0; j < p.count; j++) {
                sb.append(p.s);
                sb.append(" ");
            }
        }
        Pair p = pq.poll();
        for (int j = 0; j < p.count; j++) {
            sb.append(p.s);
            sb.append(" ");
        }

        System.out.println(sb.toString());
    }


    public static String sortByDict(String s) {

        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1 - o2;
            }
        });

        char[] schars = s.toCharArray();
        for (int i = 0; i < schars.length; i++) {
            pq.offer(schars[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < schars.length; i++) {
            sb.append(pq.poll());
        }
        return sb.toString();
    }

}
*/
