package AlgotithmExercise.String;

import java.util.HashMap;
import java.util.Map;

public class DecodeMessage {

    public String decodeMessage(String key, String message) {
        Map<Character, Integer> transferTable = new HashMap<>();

        char[] keyChars = key.toCharArray();
        int offset = 0;
        for(int i=0; i<keyChars.length; i++){
            if(keyChars[i]!=' ' && !transferTable.containsKey(keyChars[i])){
                transferTable.put(keyChars[i], offset);
                offset++;
            }
        }

        char[] messageChars = message.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messageChars.length; i++) {
            if(messageChars[i] != ' '){
                System.out.println(transferTable.get(messageChars[i]));
                sb.append((char) (transferTable.get(messageChars[i]) + 'a'));
            }else {
                sb.append(' ');
            }
        }

        return sb.toString();
    }

}
