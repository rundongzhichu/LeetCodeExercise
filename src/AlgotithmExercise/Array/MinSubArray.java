package AlgotithmExercise.Array;

public class MinSubArray {

    /**
     *  正整数x,y,z, y%p =0, 且(y-z)%p=0，则z%p = 0
     *
     *  核心思想：
     *  1.求出数组和与p的余数x，可以对i取余加上i+1位置的数，然后再与p取余，直到遍历完数组
     *  2.遍历数组，求i位置（包括i）之前的数的和与p求余数，得到数组的前缀和余数y，用hashmap保存余数值和index i
     *  3.判断当前前缀和余数y减去总和的余数x，加上p（这个主要是保证结果为正，不影响操作），再与p取余，(y-x+p)%p 判断是否在hashmap中存在，如果存在，
     *  当前index j，和hashmap中的index i形成的窗口区间就是符合条件的区间 j- i +1
     *
     *  因为(y-x+p)%p 相当于求当前index j之后数数据和与p的余数，当可以找到i j （j>i）位置之后的元素和余数相等的时候，
     *  试想：设i-j之后的区间和为m，j之后的区间和为n，(m+n)%p = n%p, 则m%p=0；
     *  i之前的区间余数为（sum-m-n）%p， j之前的为（sum-n)%p,按照上面的条件两者余数相同，按照上一步同理得到i之前的和的余数为0
     *
     * @param nums
     * @param p
     * @return
     */

    public int minSubarray(int[] nums, int p) {
        double[] sums = new double[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = (double) sums[i-1] + (double) nums[i-1];
        }

        double sum = sums[sums.length-1];
        double remain = (double)sum % p;
        System.out.println("sum = " + sum);
        System.out.println("remain = " + remain);
        if(remain == 0) return 0;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            for (int j = i+1; j < sums.length; j++) {
                if((sum - sums[j] + sums[i])%p==0) {
                    minLen = Math.min(minLen, j-i);
                    break;
                }
            }
        }
        if(minLen== nums.length|| minLen==Integer.MAX_VALUE) return -1;
        return minLen;
    }

}
/*

    public static int minSubarray(int[] nums, int p) {
        int x = 0;
        //所有元素的和，与目标取模
        for (int num : nums) {
            x = (x + num) % p;
        }
        //如果所有元素的和与p整除，不需要删除子数组
        if (x == 0) {
            return 0;
        }
        //哈希表 key保存每个前缀的和与p的取模值，value表示是第几个元素的前缀
        //例如：value = 1 ， 表示nums[1]之前的所有元素之和 ， 子数组的长度就是 i - 1 + 1
        Map<Integer, Integer> index = new HashMap<Integer, Integer>();
        int y = 0, res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            index.put(y, i); // 存放 第i个元素的前缀和与p的取模值
            y = (y + nums[i]) % p; //第i个元素的前缀之和，与p取模
            // 如果第i个元素的前缀和与p的取模值 减去 所有元素之和与p的取模值 的差（+p是保持为正数，不影响操作数） % p 的值
            if (index.containsKey((y - x + p) % p)) {
                //根据 y % p = x 那么 (y - z) % p = 0 等价于 z % p = x 定理
                //(y - z) % p = x  等价于  z % p = (y - x) % p 定理
                //(y - x + p) 相当于 剩余数组的和

                //也就是说，剩余数组和%p = 某个前缀和%p ，根据定理可知
                //取出剩余数组 与 前缀数组之间的子数组，所得到的数组和就能够被p整除（注意：子数组是连续的）
                res = Math.min(res, i - index.get((y - x + p) % p) + 1); //获取最小值
            }
        }

        return res == nums.length ? -1 : res;
    }

 */
