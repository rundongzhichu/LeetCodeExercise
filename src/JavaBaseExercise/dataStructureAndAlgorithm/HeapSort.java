package JavaBaseExercise.dataStructureAndAlgorithm;

public class HeapSort {

    public void adjustDown(int[] nums, int parent, int size) {
        int leftChildIndex = 2*parent + 1;
        if(leftChildIndex>= size) return;
        int maxValIndex = leftChildIndex;
        int rightChildIndex = 2*parent + 2;

        if(rightChildIndex< size && nums[rightChildIndex] > nums[maxValIndex]) maxValIndex = rightChildIndex;

        if(nums[parent] < nums[maxValIndex]) {
            int temp = nums[parent];
            nums[parent] = nums[maxValIndex];
            nums[maxValIndex] = temp;
            adjustDown(nums, maxValIndex, size);
        }
    }

    public void createHeap(int[] nums) {
        int lastParent  = (nums.length - 2)/2;
        for (; lastParent>=0; lastParent --) {
            adjustDown(nums, lastParent, nums.length);
        }
    }


}
