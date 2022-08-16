package DoublePointer;

public class ReverseWords {
    public String reverseWords(String s) {
        char[] schars = s.toCharArray();

        for(int i=0; i<schars.length-1;){
            if(schars[i]!=' ')
                for (int j=i+1; j<schars.length;){
                    if(schars[j]!=' '){
                        j++;
                    }else {
                        reverseString(schars,i,j-1);
                        i = j;
                        break;
                    }
                }
            else i++;
        }
        return String.valueOf(schars);
    }

    public void reverseString(char[] s, int start, int end) {
        while (start<=end){
            char temp = s[start];
            s[start]=s[end];
            s[end]=temp;
            start++;
            end--;
        }
    }


}
