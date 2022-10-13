public class containsDupII219 {
    /* Given an integer array nums and an integer k, 
    return true if there are two distinct indices i and j in the array 
        such that nums[i] == nums[j] and abs(i - j) <= k.

    Example 1: Input: nums = [1,2,3,1], k = 3 Output: true
    Example 2: Input: nums = [1,0,1,1], k = 1 Output: true
    Example 3: Input: nums = [1,2,3,1,2,3], k = 2 Output: false
    */

    public boolean containsNearbyDuplicate219(int[] nums, int k) {
        // keep size k set. if size > k, remove oldest element by remove(nums[i-k])
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true; // as within k size;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
}
