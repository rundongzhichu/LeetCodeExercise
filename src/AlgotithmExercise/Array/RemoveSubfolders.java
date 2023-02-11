package AlgotithmExercise.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RemoveSubfolders {

    public static List<String> removeSubfolders(String[] folder) {

        Arrays.sort(folder, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int index = 0;

                int o1len = o1.length();
                int o2len = o2.length();

                while (index < o1len && index <o2len){
                    if(o1.charAt(index) == o2.charAt(index)) {
                        index++;
                        continue;
                    } else {
                        return o1.charAt(index) - o2.charAt(index);
                    }

                }
                return o1len - o2len;
            }
        });

        List<String> res = new ArrayList<>();
        for (int i = 0; i < folder.length -1; i++) {
            if(folder[i].equals("") || folder[i].equals("/")) {
                continue;
            }
            res.add(folder[i]);
            for (int j = i+1; j < folder.length; j++) {
                if(folder[j].startsWith(folder[i] + "/")) {
                    if(j == folder.length -1) i = j;
                    continue;
                } else {
                    i = j - 1;
                    if(j == folder.length -1) res.add(folder[j]);
                    break;
                }
            }
        }
        return  res;
    }


    public static void main(String[] args) {
        String[] case1 = new String[]{"/a","/ac/b/c","/a/b/d"};
        Arrays.stream(case1).forEach(s-> System.out.println("s = " + s));
    }

}
