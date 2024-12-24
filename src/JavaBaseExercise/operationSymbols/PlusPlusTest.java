package JavaBaseExercise.operationSymbols;

public class PlusPlusTest {

    public static void main(String[] args) {
        plusPlus();
    }

    /**
     * 这里用来测验j=j++的效果
     */
    public static void plusPlus() {
        int j=0;
        for (int i = 0; i < 99; i++) {
            j=j++;
        }
        System.out.println("j = " + j);

        j=0;
        for (int i = 0; i < 99; i++) {
            j=++j;
        }
        System.out.println("j = " + j);

        j=0;
        for (int i = 0; i < 99; i++) {
            int temp = j++;
            j = temp;
        }
        System.out.println("j = " + j);
    }

}
