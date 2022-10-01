package AlgotithmExercise.DoublePointer;

public class Rotate {
    public void rotate(int[] nums, int k) {
       int n = nums.length;
       int temp = nums[0];
       boolean[] flag = new boolean[n];
       int modified = n;

       for(int i=0; modified >0; ){
           if(!flag[i]){
               flag[i]=true;
               int temp1 = nums[(i+k)%n];
               nums[(i+k)%n] = temp;
               temp = temp1;
               modified--;
               i=(i+k)%n;
           }else {
               i=(i+1)%n;
               temp = nums[i];
           }
       }
    }
}
