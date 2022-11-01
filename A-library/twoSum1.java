package TwoPointers;

public class twoSum1 {
    /*
    Example 1:

    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
    Example 2:

    Input: nums = [3,2,4], target = 6
    Output: [1,2]
    Example 3:

    Input: nums = [3,3], target = 6
    Output: [0,1]
    */
    public int[] twoSum1(int[] nums, int target) {
        // ex: [2,11,7,9], t = 9
        // map store diff, pos
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) { // check the val 
                return new int[]{map.get(nums[i]), i};//new int[]{e1, e2}
            }
            map.put(target - nums[i], i);
        }
        return null;
    }
}
