package AlgotithmExercise.String;

public class IsPrefixOfWord {
    public int isPrefixOfWord(String sentence, String searchWord) {
        int slen = sentence.length();
        int swlen = searchWord.length();
        int wordIdex = 1;

        for(int i=0; i<slen; i++){
            wordIdex++;
            boolean isPrefix = false;
            for(int j=0; j<swlen; j++){
                isPrefix = sentence.charAt(i+j) == searchWord.charAt(j);
                if(!isPrefix) break;
            }
            if(isPrefix) return wordIdex;
            else
                while(sentence.charAt(i)!=' ' && i<slen){
                    i++;
                }
        }
        return -1;
    }
}
