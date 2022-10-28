public class minimumSubArraySum209 {
    /* 
    Given an array of positive integers nums and a positive integer target, 
    return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr]
     of which the sum is greater than or equal to target. 
     If there is no such subarray, return 0 instead.

    Example 1:
    Input: target = 7, nums = [2,3,1,2,4,3] Output: 2
    Explanation: The subarray [4,3] has the minimal length under the problem constraint.
    
    Example 2: Input: target = 4, nums = [1,4,4] Output: 1
    Example 3: Input: target = 11, nums = [1,1,1,1,1,1,1,1] Output: 0
    */

    public int minSubArrayLen(int target, int[] nums) { // O(n)
        int res = Integer.MAX_VALUE, sum = 0, left = 0; // left记录最左边的，待删
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) { // sum值够，就一直减去最左边的，减到最短为止
                res =  Math.min(i - left + 1, res); 
                sum -= nums[left]; //先更新sum再更新指针
                left++;  //sum缩短的时候，更新最左边的
            }  //  sum值不够target了，继续找下家 
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // 暴力解法
    // https://programmercarl.com/0209.%E9%95%BF%E5%BA%A6%E6%9C%80%E5%B0%8F%E7%9A%84%E5%AD%90%E6%95%B0%E7%BB%84.html#%E7%9B%B8%E5%85%B3%E9%A2%98%E7%9B%AE%E6%8E%A8%E8%8D%90

}