public class missingNum268 {
    /* Given an array nums containing n distinct numbers in the range [0, n],
    return the only number in the range that is missing from the array.

 

Input: nums = [3,0,1] Output: 2
Input: nums = [0,1] Output: 2 
    n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing
Input: nums = [9,6,4,2,3,5,7,0,1] Output: 8
    n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing
    */

    public int missingNumberXOR(int[] nums) {
        int res = nums.length; // because this is the default last element to return
        for (int i = 0; i < nums.length; i++) {
            res ^= (nums[i] ^ i); // (0^0) ^ (1^1) ^ (3^3) ^ 2 = 2
        }
        return res;
    }

    public int missingNumberSum(int[] nums) {
        int sum = 0, len = nums.length, totalSum = len * (len + 1) / 2;
        for(int n : nums) {
            sum += n;
        }
        return totalSum - sum;
    }


    public int missingNumberSort(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length; // return len, not -1!!!
    }

    // Build a set of [0,n], then add 1 by 1. If suceeds, that's the one!
}
