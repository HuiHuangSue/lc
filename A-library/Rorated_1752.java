public class Rorated_1752 {
    // Check if array is sorted and rotated
    public boolean checkIfArrSortedRotated_1752(int[] nums) {
        // allow once only for not ascending scenario
        // use (index%n) to connect head and tail
        int limit = 0, len = nums.length;
        for(int i = 0; i < len; i++) {
            if(nums[i] > nums[(i+1)% len]) {
                limit++;
            }
            if(limit > 1) return false;
        }
        return true;
    }
}
