package AlgotithmExercise.DoublePointer;

public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        for(int i=0; i<numbers.length-1;i++){
            int remain = target - numbers[i];

            int start = i+1;
            int end = numbers.length-1;
            int mid = (start+end)/2;
            res[0]=i+1;

            while (start<=end){
                if(numbers[mid]==remain){
                    res[1]=mid +1;
                    return res;
                }else if(numbers[mid]<remain){
                    start = mid +1;
                    mid = (start+end)/2;
                }else if(numbers[mid]>remain){
                    end = mid-1;
                    mid = (start+end)/2;
                }
            }
        }
        return res;
    }
/** 双指针解题，复杂度最高位O(n)
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }
   */
}
