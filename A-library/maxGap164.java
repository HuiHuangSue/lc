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

        // Get minValue and maxValue
        int maxVal = nums[0], minVal = nums[0];
        for( int i = 1; i < nums.length; i++) {
            maxVal = Math.max(maxVal, nums[i]);
            minVal = Math.min(minVal, nums[i]);
        }
        if (maxVal - minVal == 0) return 0; // all numbers are same

        // initialize buckets. len numbers, n-1 segments
        // the smallest possible maximum gap is (max-min)/(len-1)
        int bucketSize = Math.max(1, (maxVal - minVal) / (nums.length - 1)); // could be just 1
        List<List<Integer>> buckets = new ArrayList<>();
        for(int i = 0; i <= ((maxVal - minVal) / bucketSize); i++) { // cannot use nums.length, as could n-minVal)/bucketSize can be out of bound; (can be just 0)
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
        // need curMax to track for prevMax 
        int curMax = 0; // first bucket can be empty, so initializing in for loop.
        for(List<Integer> b : buckets) {
            if(b.size() == 0) continue; // empty buckets
            int prevMax = curMax > 0 ? curMax : b.get(0); // initialize to first non-empty bucket value
            int curLow = b.get(0);
            for(int n : b){
                curLow = Math.min(curLow, n);
                curMax = Math.max(curMax, n);
            }
            maxDistance = Math.max(maxDistance, curLow - prevMax);
        }
        return maxDistance;
    }
}