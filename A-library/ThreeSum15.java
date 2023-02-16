// [0,0,0,0,0] --> [0,0,0]
// result a+b+c=0 --> C(n,2) as c is fixed already, O(n^2), 2sum is O(n)
public List<List<Integer>> threeSum15(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums.length < 3) return res;
    Arrays.sort(nums); // remembers to sort
    for(int i = 0; i < nums.length - 2; i++) { // first index stops at len-2
        if (nums[i] > 0) return res;
        // quick check, if 1 num already >0 after sorted, latter ones must be even bigger.  if has negative target, make sure it's >0 && > target.
        if (i > 0 && nums[i] == nums[i - 1]) continue; // i > 0
        int left = i + 1, right = nums.length - 1;
        while (left < right) {
            // [0,0,0,0]
            if (left > i + 1 && nums[left] == nums[left - 1]) { // left > i + 1
                left++; // keep on moving
                continue;
            }
            int sum = nums[i] + nums[left] + nums[right];
            if (sum == 0) {
                res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                while (right > left && nums[right] == nums[right - 1]) right--;
                while (right > left && nums[left] == nums[left + 1]) left++;
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

public int threeSumClosest16(int[] nums, int t) {
    int res = 0, minDiff = Integer.MAX_VALUE;
    if(nums == null || nums.length < 3) return res;
    Arrays.sort(nums);
    for(int i = 0; i < nums.length - 2; i++) {
        if(i > 0 && nums[i] == nums[i-1])continue;
        int l = i + 1, r = nums.length - 1;
        while(l < r) {
            if(l > i + 1 && nums[l] == nums[l-1]){
                l++;
                continue;
            }
            int sum = nums[i] + nums[l] + nums[r];
            if(sum == t) {
                return sum;
            } else if(sum < t) {
                l++;
            } else{
                r--;
            }
            if (Math.abs(sum - t) < Math.abs(minDiff)) {// compare abs value; just reserve pattern of abs(sum-t) and (sum-t)
                minDiff =  sum  - t; // need to keep original minDiff, not abs minDiff
            }
        }
    }
    return minDiff + t;
}
}
