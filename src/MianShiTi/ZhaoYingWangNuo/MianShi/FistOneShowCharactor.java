package MianShiTi.ZhaoYingWangNuo.MianShi;

import java.util.HashMap;
import java.util.Map;

public class FistOneShowCharactor {

    public int firstOneShowCharacter(String s) {
        if(s.length() ==1) return 0;
        Map<Character, Integer> emergencMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            emergencMap.put(c, emergencMap.getOrDefault(c, 0) +1);
        }

        for (int i = 0; i < s.length(); i++) {
            if(emergencMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return 0;
    }

}
