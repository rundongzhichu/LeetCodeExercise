package AlgotithmExercise.Array;

import java.util.ArrayList;
import java.util.List;

public class FindClosestElement {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int sum = 0;
        List<Integer> res = new ArrayList<>();

        for(int i=0; i<k;i++){
            sum += Math.abs(x-arr[i]);
            res.add(arr[i]);
        }

        for(int i=0, j=k; j<arr.length; i++,j++){
            int tempSum = sum;
            tempSum = tempSum - Math.abs(arr[i]-x) + Math.abs(arr[j]-x);
            if(tempSum<sum){
                sum = tempSum;
                res.remove(0);
                res.add(arr[j]);

            }else{
                if(arr[i]==arr[j]){
                    //System.out.println(""+arr[j]);
                    continue;
                }
                break;
            }
        }

        return res;
    }
}
