public class TwoSumStructure170 {
    class TwoSum {
        // ask which function is used more frequently, add or find
        // address corner case [3,4,5], t=8, then not sure if there's ;4,4. Use map not set.

        // Add O(1), find O(n)
        Map<Integer, Integer> map;
        public TwoSum() {
            map = new HashMap<>();
        }
        public void add(int number) { 
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
        public boolean find(int value) {
            for(int key : map.keySet()) { // {3,1;4,1;5,1}
                int target = value - key;
                int wantedCount = (target == key) ? 2 : 1;
                if (map.getOrDefault(target, 0) >= wantedCount) {
                    //use getOrDefault o.w error if target not exist in key
                    return true;
                }
            }
            return false;
        }
    }

    // 一边add 一边保持数组sorted
    // A: Binary search + ArrayInsert --> O(n) as array insert takes O(n) 腾位置
    // B: Binary Search + linkedList --> O(n) 
    //                  as binary search index access for linkedlist takes O(n) as well
    // C: heap priority queue --> O(nlogn) 
    //                  PQ knows max/min, but not 2nd, 3rd etc. need to deleteMax/deleteMin first to find 
    //                  to get queue[2/n], need to delete 1...2/n-1 first; 
    //                  need to sort in nlogn first, then find the num
    // D: TreeMap (Red/Black tree)
    //                  sorted index, left right height diff <= 1
    //                  in order traversal; add logn; find n
}

/*
 * 背向
 *      longest palindromic substring
 *      find k closest elements
 * 相向
 *      reverse
 *      2 sum
 *      partition
 * 同向
 *      sliding window
 *      fast & slow pointers
 */
