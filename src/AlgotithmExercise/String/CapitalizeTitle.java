package AlgotithmExercise.String;

public class CapitalizeTitle {

    /*
    https://leetcode.cn/problems/capitalize-the-title/solutions/?envType=daily-question&envId=2024-03-11
     */
    public String capitalizeTitle(String title) {
        String[] words = title.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word :
                words) {
            int len = word.length();
            if(len == 1 || len == 2) {
                sb.append(" ").append(word.toLowerCase());
            } else {
                sb.append(" ").append(Character.toUpperCase(word.charAt(0))).append(word.substring(1, len).toLowerCase());
            }
        }
        return sb.toString().trim();
    }

}
