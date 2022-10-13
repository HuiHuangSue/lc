public class SquaresOfSortedArray977 {
    /* Given an integer array nums sorted in non-decreasing order, 
    return an array of the squares of each number sorted in non-decreasing order.
    Input: nums = [-4,-1,0,3,10], Output: [0,1,9,16,100]
        Explanation: After squaring, the array becomes [16,1,0,9,100].After sorting, it becomes [0,1,9,16,100].
    Example 2: Input: nums = [-7,-3,2,3,11], Output: [4,9,9,49,121]
    */

    public int[] sortedSquares双指针(int[] nums) { // O(n), space O(n)
        // already sorted, so just compare 2 ends. 
        int left = 0, right = nums.length - 1, index = right;
        int[] res = new int[nums.length]; // must initiate new array to not override and mess up...
        while (left <= right) {
            if (left < right && nums[left] * nums[left] < nums[right] * nums[right]) {
                res[index] = nums[right] * nums[right];
                right--;
            } else {
                res[index] = nums[left] * nums[left];
                left++;
            }
            index--;
        }
        return res;
    }

    public int[] sortedSquares排序(int[] nums) { // O(N + nlogn)
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

}
