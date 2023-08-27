package AlgotithmExercise.tanxin;

public class TransferSegmentTogetTargetString {

    public boolean canChange(String start, String target) {
        int slen = start.length();

        char[] sCharArr = start.toCharArray();
        char[] tCharArr = target.toCharArray();

        for (int i = 0; i < slen; i++) {
            if(tCharArr[i] =='L' && tCharArr[i] != sCharArr[i]) {
                int j = i;
                while (j<slen && sCharArr[j] != 'R' && sCharArr[j] != 'L')  j++;
                if(j>=slen || sCharArr[j] == 'R') return false;
                swap(sCharArr, i, j);
            }
        }

        for (int i = slen -1; i >=0; i--) {
            if(tCharArr[i] =='R' && tCharArr[i] != sCharArr[i]) {
                int j = i;
                while (j>=0 && sCharArr[j] != 'R' && sCharArr[j] != 'L')  j--;
                if(j<0 ||  sCharArr[j] == 'L') return false;
                swap(sCharArr, i, j);
            }
        }

        return String.valueOf(sCharArr).equals(String.valueOf(tCharArr));
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     *
     * 由于每次移动操作只是交换两个相邻字符，不会增加或删除字符，
     * 因此如果可以经过一系列移动操作将 start 转换成 target，则 start 和 target
     * 满足每一种字符的数量分别相同，字符 ‘L’ 和 ‘R’ 的相对顺序相同，且每个 ‘L’在 target 中的下标小于等于对应的 ‘L’ 在 start
     * 中的下标，以及每个 ‘R’在 target 中的下标大于等于对应的 ‘R’在 start 中的下标。
     *
     * https://leetcode.cn/problems/move-pieces-to-obtain-a-string/solutions/1855012/yi-dong-pian-duan-de-dao-zi-fu-chuan-by-0j7py/
     *
     */
    class Solution {
        public boolean canChange(String start, String target) {
            int n = start.length();
            int i = 0, j = 0;
            while (i < n && j < n) {
                while (i < n && start.charAt(i) == '_') {
                    i++;
                }
                while (j < n && target.charAt(j) == '_') {
                    j++;
                }
                if (i < n && j < n) {
                    if (start.charAt(i) != target.charAt(j)) {
                        return false;
                    }
                    char c = start.charAt(i);
                    if ((c == 'L' && i < j) || (c == 'R' && i > j)) {
                        return false;
                    }
                    i++;
                    j++;
                }
            }
            while (i < n) {
                if (start.charAt(i) != '_') {
                    return false;
                }
                i++;
            }
            while (j < n) {
                if (target.charAt(j) != '_') {
                    return false;
                }
                j++;
            }
            return true;
        }
    }

}
