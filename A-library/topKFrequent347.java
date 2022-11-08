public class topKFrequent347 {
    /*
    Given an integer array nums and an integer k, return the k most frequent elements. 
    You may return the answer in any order.

        Example 1:
        Input: nums = [1,1,1,2,2,3], k = 2
        Output: [1,2]

        Example 2:
        Input: nums = [1], k = 1
        Output: [1]
    */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        // int[] pair. comparator maintain MAX frequency 
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> (p2[1] - p1[1]));//max, so p2-p1
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()}); // new int[]{}
        }
        int[] res = new int[k];
        for(int i = 0; i < k; i++) {
            res[i] = pq.poll()[0]; // first element is key
        }
        return res;
    }
}
