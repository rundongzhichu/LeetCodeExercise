package AlgotithmExercise;


import java.math.BigDecimal;
import java.util.*;
import java.math.RoundingMode;
import java.util.concurrent.Semaphore;

/**
 *
 *服务器连接方式包括直接相连，间接连接。A 和 B 直接连接，B 和 C 直接连接，则 A 和 C 间接连接。直接连接和间接连接都可以发送广播。
 *
 * 给出一个 N*N 数组，代表 N 个服务器，matrix[i][j]==1，则代表 i 和 j 直接连接；
 * 不等于 1 时，代表 i 和 j 不直接连接。
 * matrix[i][i]==1，即自己和自己直接连接。matrix[i][j]==matrix[j][i]。
 * 计算初始需要给几台服务器广播，才可以使每个服务器都收到广播。
 *
 */
class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] strArr = str.split(" ");
        int n = strArr.length;

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[0][i] = Integer.valueOf(strArr[i]);
        }

        for (int i = 1; i < n; i++) {
            str = in.nextLine();
            strArr = str.split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.valueOf(strArr[j]);
            }
        }

        int broadcastCount = calculateBroadcastServers(matrix);
        System.out.println(broadcastCount);
    }

    public static int calculateBroadcastServers(int[][] matrix) {
        int n = matrix.length;
        boolean[] visited = new boolean[n];
        int broadcastCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(matrix, i, visited);
                broadcastCount++;
            }
        }

        return broadcastCount;
    }

    private static void dfs(int[][] matrix, int server, boolean[] visited) {
        visited[server] = true;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[server][i] == 1 && !visited[i]) {
                dfs(matrix, i, visited);
            }
        }
    }
}

/**
 *
 * 给定一组不等式，需要判断这些不等式是否全部成立，并计算不等式的最大差值。最大差值定义为所有不等式左边减去右边的最大值，输出时取整数部分（注意：不是向下取整）。
 *
 * 具体要求如下：
 *
 * 不等式系数为 double 类型，以二维数组形式给出。
 * 不等式变量为 int 类型，以一维数组形式给出。
 * 不等式右侧常数项为 double 类型，以一维数组形式给出。
 * 不等式约束符号为字符串数组，只能是 ">", ">=", "<", "<=", "="。
 *
 *
 */
class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String row = scanner.nextLine();

//        2.3,3,5.6,7.6,11;11,3,8.6,25,1;0.3,9,5.3,66,7.8;1,3,2,7,5;340,670,80.6;<=,<=,<=
        // 2.36,3,6,7.1,6;1,30,8.6,2.5,21;0.3,69,5.3,6.6,7.8;1,13,2,17,5;340,67,300.6;<=,>=,<=
        String[] strArr = row.split(";");
        int strArrLen = strArr.length;
        String[] varStrArr = strArr[strArrLen - 3].split(",");
        String[] tarStrArr = strArr[strArrLen - 2].split(",");
        String[] consStrArr = strArr[strArrLen - 1].split(",");

        int rows = strArrLen - 3;
        int cols = varStrArr.length;

        String[][] coefficientsStrArr = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            coefficientsStrArr[i] = strArr[i].split(",");
        }

        double[][] coefficients = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                coefficients[i][j] = Double.valueOf(coefficientsStrArr[i][j]);
            }
        }

        int[] variables = new int[varStrArr.length];
        for (int i = 0; i < varStrArr.length; i++) {
            variables[i] = Integer.valueOf(varStrArr[i]);
        }

        double[] targets = new double[tarStrArr.length];
        for (int i = 0; i < targets.length; i++) {
            targets[i] = Double.valueOf(tarStrArr[i]);
        }

        BigDecimal maxDifference = checkInequalities(coefficients, variables, targets, consStrArr);

        System.out.println("" + maxDifference.setScale(0, RoundingMode.DOWN));
    }

    public static BigDecimal checkInequalities(double[][] coefficients, int[] variables, double[] targets, String[] constraints) {
        BigDecimal maxDifference = new BigDecimal(0);

        boolean isSatisfied = false;
        for (int i = 0; i < coefficients.length; i++) {
            BigDecimal leftValue = new BigDecimal(0);
            for (int j = 0; j < coefficients[i].length; j++) {
                leftValue = leftValue.add(new BigDecimal(coefficients[i][j] * variables[j]));
            }
            BigDecimal tarBigdecimal = new BigDecimal(targets[i]);
            BigDecimal difference = leftValue.subtract(tarBigdecimal);

            switch (constraints[i]) {
                case "<":
                    isSatisfied = isSatisfied && leftValue.compareTo(tarBigdecimal) < 0;
                    break;
                case "<=":
                    isSatisfied = isSatisfied && leftValue.compareTo(tarBigdecimal) <= 0;
                    break;
                case ">":
                    isSatisfied = isSatisfied && leftValue.compareTo(tarBigdecimal) > 0;
                    break;
                case ">=":
                    isSatisfied = isSatisfied && leftValue.compareTo(tarBigdecimal) >= 0;
                    break;
                case "==":
                    isSatisfied = isSatisfied && leftValue.compareTo(tarBigdecimal) == 0;
                    break;
                default:
                    throw new IllegalArgumentException("不支持的约束类型: " + constraints[i]);
            }


            if (difference.abs().compareTo(maxDifference.abs()) > 0) {
                maxDifference = difference.abs();
            }
        }
        System.out.print(isSatisfied + " ");
        return maxDifference;
    }
}


/**
 *
 * 给定两个字符集，一个为全量字符集，一个为已占用字符集，已占用的字符集中的字符不能再使用，要求输出剩余可用字符集
 *
 */
class Main1 {
    public static void main(String[] args) {
        String str = new Scanner(System.in).nextLine();
        String[] strs = str.split("@");
        str = helper(strs);
        System.out.println(str);
    }

    public static String helper(String[] strs) {
        if (strs.length == 1) return strs[0];
        Map<String, Integer> map = new HashMap<>();
        int flag = 0;
        for (String str : strs) {
            map = putMap(map, str, flag++);
        }

        StringBuffer buf = new StringBuffer();
        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                buf.append(key + ":" + map.get(key) + ",");
            }
        }
        String str = buf.toString();
        return str.substring(0, str.length() - 1);
    }

    public static Map<String, Integer> putMap(Map<String, Integer> map, String str, int flag) {
        String[] strs = str.split(",");
        for (String s : strs) {
            String s1 = s.split(":")[0];
            int num = Integer.parseInt(s.split(":")[1]);
            if (flag == 1 && map.containsKey(s1)) {
                int temp = map.get(s1) - num;
                map.put(s1, temp);
            } else {
                map.put(s1, num);
            }
        }
        return map;
    }
}


//public class Main {
//    public static void main(String []args){
//        String str =new Scanner(System.in).nextLine();
//        String []strs = str.split("@");
//        str= helper(strs);
//        System.out.println(str);
//    }
//    public static String  helper(String[] strs) {
//        if(strs.length==1) return strs[0];
//        Map<String ,Integer> map=new HashMap<>();
//        int flag=0;
//        for(String str :strs){
//            map = putMap(map,str,flag++);
//        }
//        //把字符串重新组合
//        StringBuffer buf=new StringBuffer();
//        for(String key : map.keySet()){
//            if(map.get(key)!=0){
//                buf.append(key+":"+map.get(key)+",");
//            }
//        }
//        String str=buf.toString();
//        return str.substring(0,str.length()-1);
//    }
//    public static Map<String,Integer> putMap(Map<String,Integer>map,String str,int flag) {
//        String [] strs=str.split(",");
//        for(String s:strs){
//            String s1=s.split(":")[0];
//            int num= Integer .parseInt(s.split(":")[1]);
//            if(flag ==1 && map.containsKey(s1)){//表示的是已经占用
//                int temp=map.get(s1)-num;
//                map.put(s1,temp);
//            }else{
//                map.put(s1,num);
//            }
//        }
//        return map;
//    }
//}


public class Main {
    private Semaphore aSem = new Semaphore(1);
    private Semaphore bSem = new Semaphore(0);
    private Semaphore cSem = new Semaphore(0);

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[i] = " + args[i]);
        }
        System.out.println("args = " + args);
        System.out.println("Hello World!");
        new Main().test();
    }

    public void test() {

    }

}