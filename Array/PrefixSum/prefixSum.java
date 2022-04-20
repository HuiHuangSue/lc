public class prefixSum {
private int[] getPrefixSum (int[] nums) {
    int[] prefixSum = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
        prefixSum[i + 1] = prefixSum[i] + nums[i];
    }
    return prefixSum;
}
/* 多少种subarrayO(n^2):nsize1,n-1size2,...subsequence O(2^n)每个char选或者不选 */
/*
MaxSubarray, Top K Largest number II
    MAx subarray: to get max of prefixSum[i+1] - prefixSum[i], 
            for given j, find minimum prefixSum[i] of i from 0 to j*/
public int maxSubArray53(int[] nums) {
    // 法1：subarr必须连续，对于每个数,要不继续带上现在的，要不restart单干开始
    int cursum = 0, maxsum = Integer.MIN_VALUE;//需为最小值，cover全负数arr情况
    for(int i = 0; i < nums.length; i++) {
        cursum = Math.max(nums[i], cursum + nums[i]);
        maxsum = Math.max(maxsum, cursum);
    }
    return maxsum;
    
    int maxsum = Integer.MIN_VALUE, sum = 0;//maxsum需是minvalue因为可能为某负数
    for (int n : nums) {
        sum += n;
        maxsum = Math.max(maxsum, sum);
        sum = Math.max(sum, 0); //需要后面更新，否则sum初始是0会覆盖全负数的数组
    }
    
    maxSum = prefixSum - minPrefixSum
    int maxsum = Integer.MIN_VALUE, minSum = 0, prefixSum = 0;
    for (int n : nums) {
        prefixSum += n;
        maxsum = Math.max(maxsum, prefixSum - minSum);
        minSum = Math.min(minSum, prefixSum);
    }
    return maxsum;
}
public int maxAbsoluteSum1749(int[] nums) { //[1,-3,2,3,-4]-->[2,3]=5
    // maxAbsSum = maxPrefixSumSub - minPrefixSumSub
    int culsum = 0, maxSum = 0, minSum = 0;
    for (int n : nums) {
        culsum += n;
        maxSum = Math.max(maxSum, culsum);
        minSum = Math.min(minSum, culsum);
    }
    return maxSum - minSum;
}

// shortest subarray sum, equals to k 1844/560
/* O(n^3) --> O(n^2) --> O(n)



for subarray left start
    for end, find if prefixSum[end+1] - prefixSum[start] = k --> prefixSum[start] = prefixSum[end+1] - k
    Use HashTable sumToIndex[keyToValue] to store 10:4 --> the subarray sum for 4 integers is 10 

// shoprtest subarray with sum at least k 1507/862
    hashtable only good for find exactly = x; for <= or >=, 
    mono queue --> O(n)
    O(nlogn)  : n * O(logn), logn * ((n); sort + other O(n) or O(logn))
        cannot sort, otherwise subarray is lost
        Try n * O(logn):
            prefixSum[end + 1] - prefixSum[start] >= k --> prefixSum[start] <= prefixSum[end+1] - k
            --> Segment Tree 找区间最大/最小值  + 离散化 
        Try logn * )(n)
            check if exit subarray >= k and segment <=k 
*/
// O(n^3) --> O(n^2) --> O(n)
public int subarraySum560(int[] nums, int k) {//3,4,7,2,-3,1,4,2;7
    int count = 0;
    Map<Integer, Integer> map = new HashMap<>();// prefixSum[start] = prefix[end] - k
    int prefixSum = 0;// int[] prefixSum = new int[nums.length + 1];
    map.put(0, 1);// important.为了[7]一个数字的array.sum-k=0的情况
    for (int end = 1; end < nums.length+1; end++){// 
        prefixSum += nums[end-1];//prefixSum[end] = prefixSum[end-1] + nums[end-1];
        // if(map.containsKey(prefixSum[end]-k)) { count += map.get(prefixSum[end] - k);}   
        if(map.containsKey(prefixSum-k)) {
            count += map.get(prefixSum - k);
        }  
        // map.put(prefixSum[end], map.getOrDefault(prefixSum[end], 0) + 1);
        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
    }
    
    int[] prefixSum = new int[nums.length + 1];//prefix[0]+nums[0];prefix[n-1]+nums[n-1]=prefix[n]要n+1位
    for (int start = 1; start <= nums.length; start++) {//1开始，nums.length位要填上
        prefixSum[start] = prefixSum[start-1] + nums[start-1];//prefixS[5]=前4个p're'si'xu'm
    }
    for (int start = 0; start < nums.length; start++) {// for subarrauy left start, for subarray right end
        for (int end = start + 1; end < nums.length + 1; end++) {//保证prefixsum能取到最后
            if(prefixSum[end] - prefixSum[start] == k) { count++;} // check if prefixSum[end] -prefixSum[start] is k
        }
    }
    
    // timeout.`end` ends @nums.length+1 index, as [start+1,nums.length] 不cover last index [1,2,3]3for[3]
    for (int start = 0; start < nums.length; start++) { // for subarrauy left start 
        for (int end = start + 1; end <= nums.length; end++) { // for subarray right end
            //for start to end: check if sum of subarray == k
            int cursum = 0; for (int i = start; i < end; i++) {  cursum += nums[i];  }
            if (cursum == k) { count++;  }
        }
    }
    return count;
}
public double findMaxAverage643(int[] nums, int k) {
    int curSum = 0;//需要cursum来记录，不然max+下一个-前一个是旧的maxsum
    for(int i = 0; i < k; i++) { curSum += nums[i]; }
    int maxSum = curSum;//前k个可能是maxsum
    for (int i = k; i < nums.length; i++) {
        curSum = curSum + nums[i] - nums[i-k];
        maxSum = Math.max(maxSum,  curSum);
    } //1,12,-5,-6,50,3;4; 1+12-5-6=2;2+50-1是下4位数的和
    return (double)maxSum/(double)k;
    
    int[] preSum = new int[nums.length];
    int maxSum = Integer.MIN_VALUE;
    preSum[0] = nums[0];
    for(int i = 1; i < nums.length; i++) {
        preSum[i] = preSum[i-1] + nums[i];
        if (i == k - 1) {
            maxSum = preSum[i];
        }
        if (i >= k) {
            maxSum = Math.max(maxSum, preSum[i] - preSum[i-k]);
        }
    }
    return (double)maxSum / (double)k;
}

public int minLenSubArraySumGE2k209(int target, int[] nums) {//[2,3,1,2,4,3],7;
    if (nums == null || nums.length == 0) return 0;
    int res = Integer.MAX_VALUE, sum = 0, left = 0; // left记录最左边的，待删
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        while (sum >= target) { // sum值够，就一直减去最左边的，减到最短为止
            res =  Math.min(i - left + 1, res); 
            sum -= nums[left];
            left++;  //sum缩短的时候，更新最左边的
        }  //  sum值不够target了，继续找下家 
    }
    return res == Integer.MAX_VALUE ? 0 : res;
}

// Max Non-negative subarray 265
// Max Subarray 1 - 6 41,42,43,620,621,722
// Max subarray Differrence 45
// Max Sum of 3 non-overlapping subarrays 1083
// Max Sum circular subarray 1724
// Max average subarray 1-2 868 617
// Max can exchanged subarray 1567
// Max size subarray sum 406
// Max size subarray sum equals k 911
// Max product subarray 191
// Max length of repeated subarray 1073
// Binary subarray with sum 1712
// Beautiful subarrays 1258
// Bitwise ORs of subarrays 1743
// Continuous subarray sum 1-2 402 403 753
// Counting universal subarrays 264
// Largest subarray 1517
// Largest continuous subarray 1809
// Interesting subarray 1631
// Triplet subarray with absolute diff less than or equal to limit 1529
// number of subarrays with bounded maximum 1021
// the kth subarray  1139
// subarray sum equals k 838
// subarray sum closest 139
// subarray sum 1-2 138 404
// subarray product less than k 1075
// shortest duplicate subarray 1779
// shortest subarray wth sum at least k 1507
// shortest unsorted continuous subarray 1157
// sshortest subarray 1611 1616 1-2
// sum of all subarrays 1814
// sum of subarray minimums 1734
// search subarray 1457 
}