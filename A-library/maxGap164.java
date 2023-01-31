class maxGap164 {
    /*
        // M1: Naive, sort, then get maxGap nlogn
        if (nums == null || nums.length < 2) return 0;
        Arrays.sort(nums);
        int res = nums[1] - nums[0];
        for(int i = 2; i < nums.length; i++) {
            res = Math.max(res, nums[i] - nums[i-1]); 
        }
        return res;
    */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        // Integer max = lists.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);

        // Get minValue and maxValue
        int maxVal = nums[0], minVal = nums[0];
        for( int i = 1; i < nums.length; i++) {
            maxVal = Math.max(maxVal, nums[i]);
            minVal = Math.min(minVal, nums[i]);
        }
        if (maxVal - minVal == 0) return 0; // all numbers are same

        // initialize buckets. len numbers, n-1 segments
        // 每个桶能装bucketSize的范围
        // the smallest possible maximum gap is (max-min)/(len-1)
        int bucketSize = Math.max(1, (maxVal - minVal) / (nums.length - 1)); // could be just 1, or (32760-0)/9722
        List<List<Integer>> buckets = new ArrayList<>();
        int bucketCount = (maxVal - minVal) / bucketSize;
        for(int i = 0; i <= bucketCount; i++) { // 区间/大小=桶的数量
            buckets.add(new ArrayList<Integer>());
        }

        // Go through array, place each number in the corresponding buckets
        // x = nums[i] = minVal + bucketSize * index
        for(int n : nums) {
            int index = (n - minVal) / bucketSize;
            buckets.get(index).add(n);
        }

        // go through all buckets, compare NEIGHBORS [min1,max1][min2,max2][min3,max3]...
        int maxDistance = 0; // maxDistance = curMin - prevMax; 
        // need curMax to track for prevMax, prevMax and curMax 是来自不同桶的
        int curMax = 0; // first bucket can be empty, so initializing in for loop.
        for(List<Integer> b : buckets) {
            if(b.size() == 0) continue; // empty buckets
            int prevMax = curMax > 0 ? curMax : b.get(0); // initialize to first non-empty bucket value
            // curMax 保留了之前桶的max，所以prevMax用它来更新
            int curLow = b.get(0);
            for(int n : b){
                curLow = Math.min(curLow, n);
                curMax = Math.max(curMax, n); // 目前筒的max，下个桶会存成prevmax的值
            }
            maxDistance = Math.max(maxDistance, curLow - prevMax);
        }
        return maxDistance;
    }
}