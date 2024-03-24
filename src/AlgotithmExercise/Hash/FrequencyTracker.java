package AlgotithmExercise.Hash;

public class FrequencyTracker {

    private int[] numberArr = null;
    private int[] frequencyArr = null;

    public FrequencyTracker() {
        frequencyArr = new int[100001];
        numberArr = new int[100001];
    }

    public void add(int number) {
        int preFreq = numberArr[number];
        numberArr[number]++;
        if(frequencyArr[preFreq]!=0) {
            frequencyArr[preFreq]--;
        }
        frequencyArr[numberArr[number]]++;
    }

    public void deleteOne(int number) {
        int preFreq = numberArr[number];
        if(numberArr[number] != 0) {
            numberArr[number] --;
        }
        if(frequencyArr[preFreq] != 0) {
            frequencyArr[preFreq]--;
        }
        frequencyArr[numberArr[number]]++;
    }

    public boolean hasFrequency(int frequency) {
        return frequencyArr[frequency] != 0;
    }


    /**
     *     链接：https://leetcode.cn/problems/frequency-tracker/solutions/2679864/pin-lu-gen-zong-qi-by-leetcode-solution-3d04/
     *
     */
    class FrequencyTracker1 {
        private static final int N = 100001;
        private int[] freq;
        private int[] freqCnt;

        public FrequencyTracker1() {
            freq = new int[N];
            freqCnt = new int[N];
        }

        public void add(int number) {
            --freqCnt[freq[number]];
            ++freq[number];
            ++freqCnt[freq[number]];
        }

        public void deleteOne(int number) {
            if (freq[number] == 0) {
                return;
            }
            --freqCnt[freq[number]];
            --freq[number];
            ++freqCnt[freq[number]];
        }

        public boolean hasFrequency(int frequency) {
            return freqCnt[frequency] > 0;
        }
    }

}
