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

public int threeSumSmaller259(int[] nums, int t) {
    int count = 0;
    if( nums == null || nums.length < 3) return count;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length -2; i++) {
        // if (nums[i] > target) return count;//otherwise fail [0,-4,-1,1,-1,2], t=-5; [-4,-1,-1,0,1,2]
        int l  = i + 1, r = nums.length - 1;
        while (l < r) {
            int sum = nums[l] + nums[r] + nums[i];
            if (sum < t) {
                //前两个index固定，在l和 r之间，不管r取什么值 ，sum都<target
                //{-2, 0,1,2,3,3,4,7} 在-2,0,7之间，不管r=which index,sum都小于target，所以count+=r-l
                count+=  r - l;
                l++;
            } else {
                r--;
            }
        }
    }
    return count;
}

public List<List<Integer>> fourSum18(int[] nums, int t) {
    List<List<Integer>>  res = new ArrayList<>();
    if( nums == null || nums.length < 4) return res;
    Arrays.sort(nums);
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
                // long longSum = (long)nums[i] + nums[left] + nums[right] + nums[j];
                int sum = nums[i] + nums[left] + nums[right] + nums[j];
                // if (longSum != sum) { // or declare sum as long
                //     left++;
                //     continue;
                // }
                if (sum == t) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right], nums[j]));
                    while(left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while(left < right && nums[right] == nums[right-1]){
                        right--;
                    }
                    left++; // keep on checking
                    right--;
                } else if (sum < t) {
                    left++;
                } else {
                    right--;
                }
            }
        }

    }
    return res;
}
public class 4SumII454 {
    /*
    Given four integer arrays nums1, nums2, nums3, and nums4 all of length n,
    return the number of tuples (i, j, k, l) such that:

    0 <= i, j, k, l < n
    nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

    Example 1:
    Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
    Output: 2
    Explanation:
    The two tuples are:
    1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
    2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0

    Example 2:
    Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
    Output: 1
    */
    public int fourSumCountII454(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int key = nums1[i] + nums2[j];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int key = (nums3[i] + nums4[j]) * -1;
                count += map.getOrDefault(key, 0);
            }
        }
        return count;
    }
}

