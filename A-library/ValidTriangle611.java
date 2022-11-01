public class validTriangle611 {
    class Solution {
        public int triangleNumber(int[] nums) {
            // from n^3 --> 
            // 求总数，用批量处理 
            // a + b > c
            int count = 0;
            if (nums.length < 3) return count;
            
            Arrays.sort(nums);
            for (int i = nums.length - 1; i >= 2; i--) {
                int left = 0, right = i - 1;
                while (left < right) {
                    if (nums[left] + nums[right] > nums[i]) {
                        count += (right - left);
                        right--; // required. 2,2,3,5,7,8; after [2,7,8][2.7,8][3,7,8][5,7,8]
                        // right-- to keep checking until find [3,5,8]
                    } else {
                        left++;
                    }
                }
                
            }
            return count;
        }
        // if ask dedup, can only find every solution 
    }
}
