public class fourSum18 {
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums); // remembers to sort
            List<List<Integer>> res = new ArrayList<>();
            for(int i = 0; i < nums.length - 3; i++) { // first index stops at len-2
                if (nums[i] > 0 && nums[i] > t) return res; // otherwise [-5,-4,-3,-2] can go -11 but stopped
                if (i > 0 && nums[i] == nums[i - 1]) continue; // i > 0
    
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                    
                    int left = j + 1, right = nums.length - 1;
                    while (left < right) {
                        if (left > j + 1 && nums[left] == nums[left - 1]) { // left > j + 1
                            left++;
                            continue;
                        }
                        long longSum = (long)nums[i] + nums[left] + nums[right] + nums[j];
                        int sum = nums[i] + nums[left] + nums[right] + nums[j];
                        if (longSum != sum) {
                            left++; // remember keep moving o.w loop
                            continue;
                        }
                        if (sum == target) {
                            res.add(Arrays.asList(nums[i], nums[left], nums[right], nums[j]));
                            left++; // keep on checking
                        } else if (sum < target) {
                            left++;
                        } else {
                            right--;
                        }
                    }
                }
                
            }
            return res;
        }
    }
}
