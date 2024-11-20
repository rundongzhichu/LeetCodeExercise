package JavaBaseExercise.classLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class SelfDefineClassLoaderTest extends ClassLoader {

    private String classPath;

    public SelfDefineClassLoaderTest(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadBytes(name);
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                /**
                 * 直接执行findClass()...什么意思呢? 首先会使用自定义类加载器加载类, 不在向上委托, 直接由
                 * 自己执行
                 *
                 * jvm自带的类还是需要由引导类加载器自动加载
                 */
                if (!name.startsWith("JavaBaseExercise.classLoader")) {
                    c = this.getParent().loadClass(name);
                } else {
                    c = findClass(name);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    private byte[] loadBytes(String name) throws IOException {
        String path = name.replace('.', '\\').concat(".class");
        FileInputStream fileInputStream = new FileInputStream(classPath + "/" + path);
        int len = fileInputStream.available();

        byte[] data = new byte[len];
        fileInputStream.read(data);
        fileInputStream.close();
        return data;
    }

    public static void main(String[] args) throws Exception {
        SelfDefineClassLoaderTest classLoaderTest =
                new SelfDefineClassLoaderTest("E:\\IdeaProjects\\LeetCodeExercise\\out\\production\\LeetCodeExercise\\");
        Class<?> clazz = classLoaderTest.loadClass("JavaBaseExercise.classLoader.Test");
        Object obj = clazz.newInstance();
        Method classLoaderTestMethod = clazz.getDeclaredMethod("classLoaderTest", null);
        classLoaderTestMethod.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());
    }

}
