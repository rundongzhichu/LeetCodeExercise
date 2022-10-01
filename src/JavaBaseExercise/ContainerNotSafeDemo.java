package JavaBaseExercise;

import java.util.Arrays;
import java.util.List;

/**
 * 集合不安全问题
 * ArrayList
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(System.out::println);

    }
}
