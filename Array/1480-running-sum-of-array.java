/*
Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
Return the running sum of nums.

Example 1:
Input: nums = [1,2,3,4]
Output: [1,3,6,10]
Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
Example 2:

Input: nums = [1,1,1,1,1]
Output: [1,2,3,4,5]
Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].

Example 3:
Input: nums = [3,1,2,10,1]
Output: [3,4,6,16,17]
 
Constraints:

1 <= nums.length <= 1000
-10^6 <= nums[i] <= 10^6
*/

class Solution {
    public int[] runningSumSelf(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];
        }
        return nums;
    }

    public int[] runningSum(int[] nums) {
        if (nums == null) {return null;}
        int[] res = new int[nums.length];
        if (nums.length == 0) {return res;}
        
        int cursum = nums[0];
        res[0] = cursum;
        for (int i = 1; i < nums.length; i++) {
            res[i] = nums[i] + cursum;
            cursum = res[i];
        }
        return res;
    }
}