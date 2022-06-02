public int[][] mergeIntervals56(int[][] intervals) {
    // https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution
    if (intervals.length <= 1) { // return original if self
        return intervals;
    }
    Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));// sort by starting position
    // intervalList.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
    List<int[]> merged = new LinkedList<>();
    int[] prevInterval = intervals[0];
    for(int i = 1; i < intervals.length; i++) {
        int curStart = intervals[i][0], curEnd = intervals[i][1];
        if(curStart > prevInterval[1]) {
            merged.add(prevInterval);
            prevInterval = intervals[i];
        } else {
            prevInterval[1] = Math.max(prevInterval[1], curEnd);
            
        } 
    }
    merged.add(prevInterval); // add the last one
    return merged.toArray(new int[merged.size()][]);
}