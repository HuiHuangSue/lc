public class LRU146 {
    /* Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

    Implement the LRUCache class:
    
    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    int get(int key) Return the value of the key if the key exists, otherwise return -1.
    void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
    The functions get and put must each run in O(1) average time complexity.
    
    Input: ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
    [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    Output:[null, null, null, 1, null, -1, null, -1, 3, 4]
    
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    lRUCache.get(1);    // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    lRUCache.get(2);    // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    lRUCache.get(1);    // return -1 (not found)
    lRUCache.get(3);    // return 3
    lRUCache.get(4);    // return 4   
    */

    class LRUCacheLinkedHashMap {
        private final int CAPACITY; // use final
        private LinkedHashMap<Integer, Integer> map;
    
        public LRUCache(int capacity) {
            // default 0.75 load factor
            //   offers good tradeoff btw lookup time and space cost
            //   when 3/4 full, rehash. 越高越慢
            // true is access order; false is insertion order;
            map = new LinkedHashMap<>(capacity, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
                    return map.size() > CAPACITY;
                }
            };
            CAPACITY = capacity;
        }
        
        public int get(int key) {
            return map.getOrDefault(key, -1); // if cannot find, return -1;
        }
        
        public void put(int key, int value) {
            map.put(key, value);
        }
    }

    

}