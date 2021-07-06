// 435

/*Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals 
you need to remove to make the rest of the intervals non-overlapping.

Example 1:
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1

Example 2:
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.*/

/*罗志祥要和 N人约会，怎么miss掉最少的小姐姐 --> 先排序，查是否overlap，如果重叠，就选择结束早的，这样能继续后面的可能性越大.  */
public int eraseOverlapIntervals(int[][] intervals) {
    // validations
    // 按interval 开头排序
    Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));
    
    int res = 0;
    int end = intervals[0][1];
    for (int i = 1; i < intervals.length; i++) {
        int curstart = intervals[i][0];
        int curend = intervals[i][1];
        if (end <= curstart) { // no overlap, just move to next one. update end
            end = curend;
        } else {
            res++; // drop the overlapped one
            // [[1,100],[1,11],[11,22],[2,12]]
            end = Math.min(end, curend); // 选结束早的的
        }
    }
    return res;
}

/****************************************************************************************************************************/