package MianShiTi.HuaWei.JiShi;

import java.util.*;

public class LetterCombinations {

    Map<Character, char[]> digitToLettersMap = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if(digits== null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        digitToLettersMap.put('2', "abc".toCharArray());
        digitToLettersMap.put('3', "def".toCharArray());
        digitToLettersMap.put('4', "ghi".toCharArray());
        digitToLettersMap.put('5', "jkl".toCharArray());
        digitToLettersMap.put('6', "mno".toCharArray());
        digitToLettersMap.put('7', "pqrs".toCharArray());
        digitToLettersMap.put('8', "tuv".toCharArray());
        digitToLettersMap.put('9', "wxyz".toCharArray());

        char[] digitsArray = digits.toCharArray();
        List<String> res = new ArrayList<>();
        combineLetters(digitsArray, 0, "", res);
        return res;
    }

    public void combineLetters(char[] digitsArray, int index, String prefix,List<String> res){
        if(index>=digitsArray.length) {
            res.add(prefix);
            return;
        }

        char[] letters = digitToLettersMap.get(digitsArray[index]);
        for (char c : letters){
            combineLetters(digitsArray, index+1, prefix+c, res);
        }
    }

}
