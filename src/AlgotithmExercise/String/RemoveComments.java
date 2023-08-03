package AlgotithmExercise.String;

import java.util.ArrayList;
import java.util.List;

public class RemoveComments {

    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        boolean multiAnno = false;
        boolean singleAnno = false;
        for (int i = 0; i < source.length; i++) {
            char[] siArr = source[i].toCharArray();
            if(!multiAnno) sb.delete(0, sb.length());
            for (int j = 0; j < siArr.length; j++) {
                if(!singleAnno && !multiAnno && siArr[j] == '/' && j+1 < siArr.length) {
                    if (siArr[j + 1] == '*') {
                        j+=2;
                        multiAnno =true;
                    } else if (siArr[j + 1] == '/') {
                        singleAnno = true;
                    }
                }

                if(singleAnno){
                    singleAnno = false;
                    break;
                }
                if(multiAnno) {
                    if(siArr[j] == '*' && j+1 < siArr.length) {
                        if (siArr[j + 1] == '/') {
                            j++;
                            multiAnno =false;
                        }
                    }
                    continue;
                }
                sb.append(siArr[j]);
            }
            if(multiAnno) continue;
            String clearLine = sb.toString();
            if(clearLine.length() > 0) {
                res.add(clearLine);
            }
        }
        return res;
    }

}
