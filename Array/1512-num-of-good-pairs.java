/*
Given an array of integers nums.

A pair (i,j) is called good if nums[i] == nums[j] and i < j.

Return the number of good pairs.

 

Example 1:

Input: nums = [1,2,3,1,1,3]
Output: 4
Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
Example 2:

Input: nums = [1,1,1,1]
Output: 6
Explanation: Each pair in the array are good.
Example 3:

Input: nums = [1,2,3]
Output: 0
 

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
*/
class Solution {
    public int numIdenticalPairs(int[] nums) {
        /*
        1,2,1,1 => 1 + 2
        1,2,1,1,1 => 1 + 2 + 3, like combination(4,2)
        */
        Map<Integer, Integer> hm = new HashMap<>();
        int count = 0;
        for (int n : nums) {
            if (hm.containsKey(n)) {
                hm.put(n, hm.get(n) + 1);
                count += hm.get(n);
            } else {
                hm.put(n, 0); // starts with 0 to indicate exist
            }
        }
        return count;
    }

    public int numIdenticalPairs(int[] nums) {
        int res = 0, count[] = new int[101];
        for (int a: nums) {
            // not ++count[a] so its ok. res will add 0 on the first occurance.
            res += (count[a]++);
        }
        return res;
    }
}