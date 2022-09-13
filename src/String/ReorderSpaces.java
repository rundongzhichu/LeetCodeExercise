package String;

import java.util.Deque;
import java.util.LinkedList;

public class ReorderSpaces {
    public String reorderSpaces(String text) {
        Deque<String> stack = new LinkedList<>();
        int len = text.length();

        int spaceCount = 0;
        for (int i = 0; i < len; ) {
            while (i < len && text.charAt(i) == ' ') {
                spaceCount++;
                i++;
            }

            StringBuilder sb = new StringBuilder();
            while (i < len && text.charAt(i) != ' ') {
                sb.append(text.charAt(i));
                i++;
            }

            if(sb.length()!=0)
                stack.push(sb.toString());
        }

        int ssize = stack.size();
        int intervalSpaceCount = 0;
        String res= "";
        if(ssize!=1) {
            intervalSpaceCount = spaceCount / (ssize - 1);
            while (!stack.isEmpty()) {
                res = stack.pop() + res;
                if (!stack.isEmpty()) {
                    String temp = "";
                    for (int i = 0; i < intervalSpaceCount; i++) {
                        temp += " ";
                    }
                    res = temp + res;
                }
            }
        }

        int tailCount = spaceCount - intervalSpaceCount*(ssize - 1);
        String temp = "";
        for (int i = 0; i < tailCount; i++) {
            temp += " ";
        }

        return res+temp;
    }
}
