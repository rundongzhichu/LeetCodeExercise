package AlgotithmExercise.tanxin;

public class MinNumberOfHours {

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int exForExp = 0;
        int exForEne = 0;
        for (int i = 0; i < experience.length; i++) {
            if (initialExperience <= experience[i]) {
                exForExp += experience[i] - initialExperience + 1;
                initialExperience += experience[i] - initialExperience + 1;
            }
            if (initialEnergy <= energy[i]){
                exForEne += energy[i] - initialEnergy + 1;
                initialEnergy += energy[i] - initialEnergy + 1;
            }
            initialEnergy -= energy[i];
            initialExperience += experience[i];
        }
        return exForEne + exForExp;
    }

}
