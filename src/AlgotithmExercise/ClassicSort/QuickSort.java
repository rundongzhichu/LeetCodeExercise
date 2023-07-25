package AlgotithmExercise.ClassicSort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{ 19, 13, 21,24,22,56,12, 32,46 ,54,1,77,7,85};
        new QuickSort().quickSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public void quickSort(int[] array) {
        quickSort(array, 0, array.length-1);
    }


    public void quickSort(int[] array, int left, int right) {
        if(left>=right) return;
        int pval = array[right];
        int l = left;
        int r = right;
        while (l < r) {
            while (array[l] < pval) {
                l ++;
            }
            while ((array[r] >= pval)){
                r --;
            }
            if(l<r)
                swap(array, l, r);
        }

        swap(array, l, right);
        quickSort(array, left, l-1);
        quickSort(array, l, right);
    }

    public void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
