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


class Solution {
    // 3 <= arr.length <= 3000; 0 <= arr[i] <= 100; 0 <= target <= 300
    // 0 <= arr[i] <= 100; can use int[] instead of map
     public int threeSumMulti923(int[] arr, int target) {
        int MOD = (int)(1e9 + 7);
        long[] countMap = new long[101];//101, as 0...100
        for (int a : arr) countMap[a]++;
        long res = 0; //if not declare as long,
        for (int i = 0; i <= 100; i++) // i is number, not index 是数字，不是index
            for (int j = i; j <= 100; j++) { // can be reused like 3,3,3, so i, not i+1
                int k = target - i - j;
                if (k > 100 || k < 0) continue;
                if (i == j && j == k) //C(n,3)=n!/((n-3)!(3!))=n!/(n-3)!/3!=n(n-1)(n-2)/(3*2)
                    res += countMap[i] * (countMap[i] - 1) * (countMap[i] - 2) / 6;
                else if (i == j && j != k)//C(i,2)*k-->i*(i-1)/2 *k
                    res += countMap[i] * (countMap[i] - 1) / 2 * countMap[k];
                else if (j < k)
                    res += countMap[i] * countMap[j] * countMap[k];
            }
        return (int)(res % MOD);
     }
    public int threeSumMulti923(int[] arr, int target) {
        int MOD = 1000000007;  // int MOD = 1_000_000_007;
        // A: based on 3Sum, but build a map for counting different sums of two numbers.
        // 自己之前的每一个数都和自己加一下，把sum存在map里；
        // 在挪动到下一个数之前，把已有要找的数字（target-i)=sum 在map里找一下
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = (res + map.getOrDefault(target - arr[i], 0)) % MOD; //add first加成大的数字, then mod
            for (int j = 0; j < i; j++) {
                int temp = arr[i] + arr[j];
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        return res;
    }
    /* A trace through
            1,1,2,2,3,3,4,4,5,5 t=8
            index=0,n=1;
            index=1,n=1:找8-1=7;
                1+1
                (2,1)
            index=2,n=2:找8-2=6;
                1+2,1+2
                (2,1)(3,2)
            index=3,n=2;找8-2=6;
                1+2,1+2,2+2
                (2,1)(3,4)(4,1)
            index=4,n=3;找8-3=5;
                1+3,1+3,2+3,2+3
                (2,1)(3,4)(4,3)(5,2)
            index=5,n=3;找8-3=5; +++++2
                1+3,1+3,2+3,2+3,3+3
                (2,1)(3,4)(4,5)(5,4)(6,1)
            index=6,n=4;找8-4=4;  +++5
                1+4,1+4,2+4,2+4,3+4,3+4
                (2,1)(3,4)(4,5)(5,6)(6,2)(7,2)
            index=7,n=4;找8-4=4; +++5
                1+4,1+4,2+4,2+4,3+4,3+4,4+4
                (2,1)(3,4)(4,5)(5,8)(6,4)(7,4),(8,1)
            index=8,n=5;找8-5=3; +++4
                1+5,1+5,2+5,2+5,3+5,3+5,4+5,4+5
                (2,1)(3,4)(4,5)(5,8)(6,6)(7,6),(8,3),(9,2)
            index=9,n=5;找8-5=3; ++++4
                1+5,1+5,2+5,2+5,3+5,3+5,4+5,4+5,5+5
                (2,1)(3,4)(4,5)(5,8)(6,8)(7,8),(8,5),(9,4),(10,1)
            */
}