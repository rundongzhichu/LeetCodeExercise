package JavaBaseExercise.classLoader;

public class Test {

    public void classLoaderTest() {
        System.out.println("classLoader is: " + this.getClass().getClassLoader().getClass().getName());
    }

}
