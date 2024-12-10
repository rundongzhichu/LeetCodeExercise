package JavaBaseExercise.threadLocal;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalDemo {

    private List<String> messages = new ArrayList<>();

    public static final ThreadLocal<ThreadLocalDemo> holder = new ThreadLocal<>();

    public static void add(String message) {
        ThreadLocalDemo tld = holder.get();
        if(tld == null) {
            tld = new ThreadLocalDemo();
            holder.set(tld);
        }
        tld.messages.add(message);
    }

    public static ThreadLocalDemo get() {
        return holder.get();
    }

    public static List<String> clear() {
        List<String> messages = holder.get().messages;
        System.out.println("size: " + holder.get().messages.size());
        holder.remove();
        return messages;
    }

    public static void main(String[] args) {
        add("一枝花算不算浪漫");
        System.out.println(get().messages);
        clear();
    }


}
