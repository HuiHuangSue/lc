public class BinarySearch704 {
    /* Given an array of integers nums which is sorted in ascending order, and an integer target, 
    write a function to search target in nums. 
    If target exists, then return its index. Otherwise, return -1.

    You must write an algorithm with O(log n) runtime complexity.
    */
    
    /*
    Example 1:
        Input: nums = [-1,0,3,5,9,12], target = 9, Output: 4
        Explanation: 9 exists in nums and its index is 4

    Example 2:
        Input: nums = [-1,0,3,5,9,12], target = 2 Output: -1
        Explanation: 2 does not exist in nums so return -1 */

    // sorted in ascending; logn --> binary search; all numbers unique. 

    // [704 Binary Search](https://leetcode.com/problems/binary-search/)
    public int search704I(int[] nums, int t) { //[Left,Right]
        // [-1,0,3,5,9,12] t=9
        int left = 0, right = nums.length - 1;
        while (left <= right) { // [left, right]
            int mid = left + (right - left) / 2;
            if (nums[mid] == t) {
                return mid;
            } else if (nums[mid] < t) { // t = 9, search right interval
                left = mid + 1; // mid is not answer, skip
            } else { // t = 0, search left interval
                right = mid - 1;
            }
        }
        return -1;
    }
    public int search704II(int[] nums, int t) { //[Left, right)
        int left = 0, right = nums.length; // right), so not include right
        while(left < right) { //[1,1) invalid, left=right is invalid range.
            int mid = left + (right - left) / 2;
            if (nums[mid] == t) {
                return mid;
            } else if (nums[mid] < t) { // updating left bound
                left = mid + 1; // mid cannot be answer, so can skip
            } else { // updating right bound, right not included, can be next answer
                right = mid;
            }
        }
        return -1;
    }
    
}
