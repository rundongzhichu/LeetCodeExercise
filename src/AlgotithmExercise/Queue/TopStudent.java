package AlgotithmExercise.Queue;

import java.util.*;

public class TopStudent {

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        Set<String> positiveSet = new HashSet<>();
        Set<String> negativrSet = new HashSet<>();

        PriorityQueue<int[]> resQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] != o2[1]) return o2[1] - o1[1];
                return o1[0] - o2[0];
            }
        });

        for (String positive :
                positive_feedback) {
            positiveSet.add(positive);
        }

        for (String negative :
                negative_feedback) {
            negativrSet.add(negative);
        }

        for (int i = 0; i < report.length; i++) {
            int score = 0;
            int id = student_id[i];

            String review = report[i];
            String[] words = review.split(" ");
            for (String word :
                    words) {
                score += positiveSet.contains(word) ? 3 : 0;
                score += negativrSet.contains(word) ? -1 :0;
            }
            resQueue.offer(new int[]{id, score});
        }
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            idList.add(resQueue.poll()[0]);
        }
        return idList;
    }


    /**
     *
     *     链接：https://leetcode.cn/problems/reward-top-k-students/solutions/2470649/jiang-li-zui-ding-jian-de-k-ming-xue-she-epe6/

     */
    class Solution {
        public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
            Map<String, Integer> words = new HashMap<>();
            for (String word : positive_feedback) {
                words.put(word, 3);
            }
            for (String word : negative_feedback) {
                words.put(word, -1);
            }
            int n = report.length;
            int[] scores = new int[n];
            int[][] A = new int[n][2];
            for (int i = 0; i < n; i++) {
                int score = 0;
                for (String word : report[i].split(" ")) {
                    score += words.getOrDefault(word, 0);
                }
                A[i] = new int[]{-score, student_id[i]};
            }
            Arrays.sort(A, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            List<Integer> topK = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                topK.add(A[i][1]);
            }
            return topK;
        }
    }

}
