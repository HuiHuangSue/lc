public class LRU146 {
/*
    Phone app display on screen. Max 3. When 4th come, oldest pop out.
    1. Requirements: O(1) on search, delete; Needs to track order --> HashMap + LinkedList --> Built-in LinkedHashMap
        Main:
        int capacity
        int put(key, val) // O(1)
        int get(key) // O(1)
*/
    class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache(int capacity) {
            // default 0.75 load factor
            //   offers good tradeoff btw lookup time and space cost
            //   when 3/4 full, rehash. 越高越慢
            // true is access order; false is insertion order;
            super(capacity, 0.75f, true);// call to super must be the first statement in class
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

/*
    2. Double linked list. Need to remove at O(1). 。
    删除⼀个节点不光要得到该节点本⾝的指针，也需要操作其前驱节点的指针，⽽双向链表才能⽀持直接查找前驱，保证 O(1)
    declare DLinkedNode class, addNode/removeNode/moveToHead(DLinkedNode), and popTail return to supply cache key for removal
*/
    class LRUCache {
        class DLinkedNode {
            int key, value;
            DLinkedNode prev, next;
            // default constructors, did not initialize key,val otherwise required
        }
        private DLinkedNode head, tail;

        private void addNode(DLinkedNode node){ // always at after head
            // head <--> 1 <--> 2 <--> tail. insert 3 after head.
            node.prev = head;
            node.next = head.next;
            // point to head
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node){ // remove existing node from ll
            DLinkedNode prevNode = node.prev, nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        private void moveToHead(DLinkedNode node){
            removeNode(node);
            addNode(node);
        }

        private DLinkedNode popTail(){
            DLinkedNode popped = tail.prev;
            removeNode(popped);
            return popped;
        }

        private int size;
        private int capacity;
        private HashMap<Integer, DLinkedNode> cache; // key, <key-value>
        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            head = new DLinkedNode(); // this initialization must be the head/tail that are already defined
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
            cache = new HashMap<>();
        }


        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) return -1;
            moveToHead(node); // must move to head
            return node.value;
        }

        public void put(int key, int value) {
            // add or update
            DLinkedNode node = cache.get(key);
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode();
                newNode.key = key; newNode.value = value;
                cache.put(key, newNode);
                addNode(newNode);
                size++;
                // If full, popTail
                if (size > capacity) {
                    DLinkedNode tail = popTail();
                    cache.remove(tail.key); // UPDATE CACHE!!!
                    size--;
                }

            } else {
                node.value = value;
                moveToHead(node);
            }
        }
    }
    /***************************************************************************************** */
    /* Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

    Implement the LRUCache class:
    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    int get(int key) Return the value of the key if the key exists, otherwise return -1.
    void put(int key, int value) Update the value of the key if the key exists.
    Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
    The functions get and put must each run in O(1) average time complexity.

    Input: ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
    [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    Output:[null, null, null, 1, null, -1, null, -1, 3, 4]

    Left head, Right right; head 1 -> 2 -> 3 tail
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // cache is {(1,1)}
    lRUCache.put(2, 2); // cache is {(2,2),(1,1)}
    lRUCache.get(1);    // (1,1) more recent, cache is {(1,1), (2,2)} put to head; return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {(3,3), (1,1)}
    lRUCache.get(2);    // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {(4,4), (3,3)}
    lRUCache.get(1);    // return -1 (not found)
    lRUCache.get(3);    // return 3
    lRUCache.get(4);    // return 4
*/
    /***************************************************************************************** */
    class LRUCacheLinkedHashMap {
        private final int CAPACITY; // use final, and upper case for constants
        private LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
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