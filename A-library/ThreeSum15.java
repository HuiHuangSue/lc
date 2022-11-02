public class threeSum15 {
    class Solution {
        // result a+b+c=0 --> C(n,2) as c is fixed already, O(n^2), 2sum is O(n)
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums.length <= 0) return res;
            
            Arrays.sort(nums); // remembers to sort
            for(int i = 0; i < nums.length - 2; i++) { // first index stops at len-2
                if (nums[i] > 0) return res; 
                // quick check, if 1 num already >0 after sorted, latter ones must be even bigger.  if has negative target, make sure it's >0 && > target. 
                if (i > 0 && nums[i] == nums[i - 1]) continue; // i > 0
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    if (left > i + 1 && nums[left] == nums[left - 1]) { // left > i + 1
                        left++;
                        continue;
                    }
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        left++; // keep on checking
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return res;
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;
                    
                    right--; 
                    left++;
                }
            }
        }
        return result;
    }
}
