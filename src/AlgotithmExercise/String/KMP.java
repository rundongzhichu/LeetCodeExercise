package AlgotithmExercise.String;

public class KMP {

    public static int KMPStringMathch(String s, String subs) {
        char[] schars = s.toCharArray();
        char[] subChars = subs.toCharArray();

        int[] next = getNext(subChars);
        int i=0, j=0;

        while (i<schars.length) {
            if(j == subChars.length) break;
            // 只有j=0 且和schars[i]不匹配的时候，j才可能回退到-1；下一次继续判断的时候，i和j都要后移
            if(j==-1 || schars[i] == subChars[j]){
                System.out.println("subChars = " + schars[i]);
                i ++;
                j ++;
            } else {
                j = next[j];
            }
        }

        if(i == schars.length) return -1;
        return  i-j;
    }

    public static void main(String[] args) {
        System.out.println(KMPStringMathch("dcaaabc jkkd", "abc"));
    }

    public static int[] getNext(char[] subChars ) {
        int[] next = new int[subChars.length];

        int i = 0;
        int prefixLen = 0; // 记录每个时刻最长前缀长度
        next[i] = -1; // 0位置时前面没有子串，所以最长前缀长度设置为-1
        next[++i] = 0;
        i++;
        while (i < subChars.length) {
            if(prefixLen == -1 || subChars[i-1] == subChars[prefixLen]) {
                next[i] = prefixLen + 1;
                i++;
                prefixLen ++;
            } else {
                prefixLen = next[prefixLen];
            }
        }
        return next;
    }

}
