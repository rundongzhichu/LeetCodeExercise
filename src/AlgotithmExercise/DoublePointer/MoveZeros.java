package AlgotithmExercise.DoublePointer;

public class MoveZeros {
    public void moveZeroes(int[] nums) {
        int end = nums.length-1;

        for(int i=0; i<end-1; i++){
            if(nums[i]==0)
                for (int j = i+1; j<end;j++){
                    if(nums[j]!=0){
                        nums[i]=nums[j];
                        nums[j]=0;
                        break;
                    }
                }
        }
    }
}
