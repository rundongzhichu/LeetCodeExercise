package AlgotithmExercise.ClassicSort;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{ 19, 13, 21,24,22,56,12, 32,46 ,54,1,77,7,85};
        new MergeSort().mergeSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public void mergeSort(int[] array) {
        mergeSort(array, 0, array.length-1);
    }

    public void mergeSort(int[] array, int left, int right) {
        if(left >= right) return;
        int mid = (left + right) /2;
        mergeSort(array, left, mid);
        mergeSort(array, mid +1, right);

        int[] temp = new int[right-left+1];
        int i = left, j = mid +1;
        int tidx = 0;
        while (i <= mid && j <= right) {
            if(array[i] < array[j]) {
                temp[tidx] = array[i];
                i++;
            } else {
                temp[tidx] = array[j];
                j++;
            }
            tidx++;
        }

        if(i <= mid) {
            System.arraycopy(array, i, temp, tidx, mid - i + 1);
        }
        if(j <= right){
            System.arraycopy(array, j, temp, tidx, right - j +1);
        }
        System.arraycopy(temp, 0, array, left, temp.length);
    }

}
