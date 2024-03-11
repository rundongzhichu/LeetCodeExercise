package JavaBaseExercise.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference);
    }

    /**
     * jvm配置，故意产生大对象，并分配小内存，让他内存不够用
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try {
            byte[] bytes = new byte[5 * 1024 * 1024];
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference);
        }
    }

    public static void main(String[] args) {
        //softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }

}
