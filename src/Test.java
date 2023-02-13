import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<Integer> res = new ArrayList<>();
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int beverages = in.nextInt();
            if (beverages == 0) {
                break;
            }

            int bottles = 0;
            while (beverages / 3 != 0) {
                bottles += beverages / 3;
                beverages = beverages%3 + beverages/3;
                if(beverages / 3 == 0) {
                    if(beverages % 3 == 2) {
                        bottles +=1;
                    }
                    res.add(bottles);
                }
            }
        }
        res.stream().forEach(v-> System.out.println(v));
    }

}
