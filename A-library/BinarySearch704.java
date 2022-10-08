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


    public int search704(int[] nums, int t) { //[left,right]
        int l = 0, r = nums.length - 1;
        while(l <= r) { // left=right is valid, so so not skip
            int mid = l + (r - l) / 2;
            if (nums[mid] == t) {
                return mid;
            } else if (nums[mid] < t) {
                l = mid + 1; //mid is already checked not t
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
    
}
