package AlgotithmExercise.BinarySearch;

public class VersionControl {

    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        // 核心关键: 不断缩小第一个错误版本的寻找区间，最后区间缩小到只有错误版本
        int mid = start + (end - start)/2;

        while (start < end){
            if(isBadVersion(mid)){
                end = mid;
            }else{
                start = mid + 1;
            }
            mid = start + (end - start)/2;
        }
        return mid;
    }

    boolean isBadVersion(int version){
        return true;
    }

}
