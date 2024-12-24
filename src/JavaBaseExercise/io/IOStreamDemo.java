package JavaBaseExercise.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class IOStreamDemo {

    public static void main(String[] args) throws FileNotFoundException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("test.txt")));
    }

}
