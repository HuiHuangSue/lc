public class slidingWindowMax239 {
    /*
    You are given an array of integers nums, 
    there is a sliding window of size k which is moving from the very left of the array to the very right. 
    You can only see the k numbers in the window. Each time the sliding window moves right by one position.
    Return the max sliding window.
    
    Example 1:
        Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
        Output: [3,3,5,5,6,7]
        Explanation: 
        Window position                Max
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       3
        1 [3  -1  -3] 5  3  6  7       3
        1  3 [-1  -3  5] 3  6  7       5
        1  3  -1 [-3  5  3] 6  7       5
        1  3  -1  -3 [5  3  6] 7       6
        1  3  -1  -3  5 [3  6  7]      7
        
    Example 2:
        Input: nums = [1], k = 1
        Output: [1]
    */

    // Bruce Force
    // n-k+1 window, size k. --> O((n-k+1)k)
    // Space is linear
    // k=n/2 is worst case to O(n^2). k=n not the worst case but linear

    // BST/multiset
    // space O(k), time logk * (n-k+1). Search is O(1), but maintain (pop/add) is logk

    // Monotonic Queue
    /* [1,3,-1,-3,5,3,6,7], k = 3
     * [1]
     * [3] // kicked 1 out
     * [3, -1]                                         [3]
     * [3, -1, -3]                                     [3]
     * [5] // kicked all smaller than it out           [5]
     * [5, 3]                                          [5]
     * [6] // kicked all smaller out                   [6]
     * [7] // kicked all smaller out                   [7]
     */ 
    
    public class myQueue {
        Deque<Integer> deque = new LinkedList<>(); // mostly add/remove, no much search. 

        void push(int val){ // remove all smaller than val numbers
            while (!deque.isEmpty() && val > deque.getLast()) { // deque.getLast()
                deque.removeLast();
            }
            deque.add(val);
        }

        void poll(int val) { // pop掉滑动窗口最左边的值，也就是窗口之外的值
            if (!deque.isEmpty() && val == deque.peek()) {
                deque.poll();
            }
        }
        int peek() {
            return deque.peek();
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        myQueue qq = new myQueue();
        int index = 0;
        for(int i = 0; i < k; i++) {
            qq.push(nums[i]);
        }
        res[index++] = qq.peek();
        for (int i = k; i < nums.length; i++) {
            qq.poll(nums[i - k]); // pop掉滑动窗口最左边的值，也就是窗口之外的值
            qq.push(nums[i]);
            res[index++] = qq.peek();
        }
        return res;
    }

}
