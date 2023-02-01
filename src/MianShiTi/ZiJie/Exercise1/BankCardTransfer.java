package MianShiTi.ZiJie.Exercise1;

import java.util.*;

public class BankCardTransfer {

    private static Map<Integer, Integer> schemeCost = new TreeMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.valueOf(in.next());
        int m = Integer.valueOf(in.next());

        Map<Integer, Set<Integer>> transferMap = new HashMap<>();


        int[][] transferArray = new int[m][2];
        // generate adjacent matrix
        for(int i=0; i<m; i++){
            transferArray[i][0] = Integer.valueOf(in.next());
            transferArray[i][1] = Integer.valueOf(in.next());
        }

        calculate(transferMap, transferArray, 0, n);
        schemeCost.keySet().stream().forEach(v-> System.out.println("v = " + v));

        int key = schemeCost.keySet().stream().findFirst().get();

        System.out.println("MinCost: " + key + " schemaCount: " + schemeCost.get(key));
    }

    public static void calculate(Map<Integer, Set<Integer>> transferMap, int[][] transferArray, int index, int n){
        if(index == transferArray.length-1){
            int sumCost = 0;
            boolean canTransfer = true;
            // calculate the cost of every card
            for(int i=1; i <= n; i++){
                int temp = calculateMinCost(transferMap, i, new boolean[n+1]);
                if(temp == Integer.MAX_VALUE) {
                    canTransfer = false;
                    break;
                }
                sumCost +=  temp;

            }
            if(canTransfer)
                schemeCost.put(sumCost, schemeCost.getOrDefault(sumCost, 0)+1);
            return;
        }

        int card1 = transferArray[index][0];
        int card2 = transferArray[index][1];
        addItemToTransferMap(transferMap, card1, card2);
        calculate(transferMap, transferArray, index+1, n);
        transferMap.get(card1).remove(card2);

        addItemToTransferMap(transferMap, card2, card1);
        calculate(transferMap, transferArray, index+1, n);
        transferMap.get(card2).remove(card1);

    }

    public static void addItemToTransferMap(Map<Integer, Set<Integer>> transferMap, int card1, int card2){
        if(transferMap.containsKey(card1)){
            transferMap.get(card1).add(card2);
        }else {
            Set<Integer> set = new HashSet<>();
            set.add(card2);
            transferMap.put(card1, set);
        }
    }

    public static int calculateMinCost(Map<Integer, Set<Integer>> transferMap, int start, boolean[] visited){
        if(start == 1){
            return 0;
        }

        Set<Integer> neighbors = transferMap.get(start);
        int cost = Integer.MAX_VALUE;

        if(neighbors!= null && !neighbors.isEmpty())
            for(Integer neighbor: neighbors){
                if(!visited[neighbor]){
//                    System.out.println(neighbor);
                    visited[neighbor] = true;
                    int temp = calculateMinCost(transferMap, neighbor, visited);
//                    System.out.println(temp);
                    if( Integer.MAX_VALUE == temp) {
                        visited[neighbor] = false;
                        continue;
                    }
                    cost = Math.min(1 + temp, cost);
                    System.out.println("cost= " +cost);
                    visited[neighbor] = false;
                }
            }
        return cost;
    }

}
