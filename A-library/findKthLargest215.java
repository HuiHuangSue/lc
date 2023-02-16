public int findKthLargest215(int[] nums, int k) {
    // A: sort O(nlogn)
    // B: maintain size K array. Doing insertion sort. O(nk), k can be very large.
    // C quickselect average O(n), worst O(n^2)
    //      The algorithm is similar to QuickSort. The difference is,
    //          instead of recurring for both sides (after finding pivot),
    //          it recurs only for the part that contains the k-th smallest element.
    //      kth largest === (n-k)th smallest
    //      partition, all left < pivot, all right > pivot

    // C minheap of size K, then top of minHeap is the kth largest.
    //  1. Build heap size k O(k); 2 traverse remaining O(n-k); 3 each traverse adjust O(logk)
    // O(k)+O((n-k)logk) --> O((n-k)logk + k) --> k<<n then O(nlogk)
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>((n1, n2) -> (n1 - n2));
    for(int n : nums) {
        pq.add(n);
        if(pq.size() > k) {
            pq.poll();
        }
    }
    return pq.poll();
}