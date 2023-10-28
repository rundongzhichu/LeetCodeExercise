package AlgotithmExercise.Recursive;

public class PunishmentNumber {

    public int punishmentNumber(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if(punishmentNumber(Integer.toString(i*i).toCharArray(), 0, i)) {
                res += i*i;
                System.out.println("i = " + i);
                System.out.println("i*i = " + i*i);
            }
        }
        return res;
    }

    public char[] getChars(int n) {
        n = n*n;
        StringBuilder sb = new StringBuilder("");
        while (n/10 != 0) {
            sb.append(n%10);
            n/=10;
        }
        sb.append(n%10);
        return sb.reverse().toString().toCharArray();
    }

    public boolean punishmentNumber(char[] array, int start, int target) {
        if(start >= array.length) {
            if(target == 0) {
                return true;
            }
            return false;
        }

        boolean res = false;
        int substract = 0;
        for (int end = start ; end < array.length; end ++) {
            substract = substract*10 + array[end] - '0';
            if(target-substract < 0) break;
            System.out.println("substract = " + substract);
            res = res || punishmentNumber(array, end, target - substract);
            if(res) return true;
        }
        return res;
    }

    public int getInteger(char[] array, int start, int end) {
        StringBuilder sb = new StringBuilder("");
        for (int i = start; i < end && i < array.length; i++) {
            sb.append(array[i]);
        }
        return Integer.valueOf(sb.toString());
    }

}
