package AlgotithmExercise.Mokito;

public class IsWinner {

    /**
     *
     * https://leetcode.cn/problems/determine-the-winner-of-a-bowling-game/solutions/2546593/bao-ling-qiu-you-xi-de-huo-sheng-zhe-by-vfuy8/?envType=daily-question&envId=2023-12-27
     *
     * @param player1
     * @param player2
     * @return
     */
    public int isWinner(int[] player1, int[] player2) {
        int score1 = calculateScore(player1);
        int score2 = calculateScore(player2);
        if(score1> score2) return 1;
        else if(score1 < score2) return 2;
        else return 0;
    }

    public int calculateScore(int[] plays) {
        int playScore = 0;
        for (int i = 0; i < plays.length; i++) {
            if (i >= 1 && (plays[i - 1] == 10 || plays[i - 2 < 0? 0 : i-2] == 10)) {
                playScore += 2*plays[i];
            } else {
                playScore += plays[i];
            }
        }
        return playScore;
    }


}
