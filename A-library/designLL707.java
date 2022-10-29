public class designLL707 {
    /*
     * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
        A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
        If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

        Implement the MyLinkedList class:
        MyLinkedList() Initializes the MyLinkedList object.
        int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
        void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
        void addAtTail(int val) Append a node of value val as the last element of the linked list.
        void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
        void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.

        Example 1:
        Input
        ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
        [[], [1], [3], [1, 2], [1], [1], [1]]
        Output
        [null, null, null, null, 2, null, 3]

        Explanation
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
        myLinkedList.get(1);              // return 2
        myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
        myLinkedList.get(1);              // return 3
      */

      class MyLinkedListSINGLE {
        ListNode head;
        int size;
    
        public MyLinkedList() {
            head = new ListNode(0);
            size = 0;
        }
        
        public int get(int index) {
            if (index < 0 || index >= size) { // 0-indexed, so index=size is invalid
                return -1;
            }
            // head is dummy, so search for index+1, which is the index-th node val
            ListNode cur = head;
            while (index >= 0) {
                cur = cur.next;
                index--;
            }
            return cur.val;
        }
        
        public void addAtHead(int val) {
            addAtIndex(0, val); // size if updated by addAtIndex
        }
        public void addAtTail(int val) {
            addAtIndex(size, val); // size if updated by addAtIndex
        }
        
        public void addAtIndex(int index, int val) {
            if (index > size) return;
            if (index < 0) index = 0;
            ListNode cur = head; // this is virtual node
            // find the previous position of the target node
            while (index > 0) { // previous one, so no =0
                cur = cur.next;
                index--;
            }
            ListNode newNode = new ListNode(val);
            newNode.next = cur.next; // connect to next first, otherwise lose
            cur.next = newNode;
            size++;
        }
        
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) return;//5 nodes, index[0..4]
            size--;
            if(index == 0) {
                head = head.next; return;
            }
            ListNode cur = head;
            while(index > 0) { // node before target to delete
                cur = cur.next;
                index--;
            }
            cur.next = cur.next.next;
    
        }
    }


    /*
     * Double linked list
     */
    class ListNode{
        int val;
        ListNode next,prev;
        ListNode(int val){
            this.val = val;
        }
    }
    class MyLinkedList {
        ListNode head, tail;
        int size;
        public MyLinkedList() {
            head = new ListNode(0);
            tail = new ListNode(0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        
        public int get(int index) {
            if(index < 0 || index >= size) return -1;
            ListNode cur = head;
            if (index >= size / 2) { // start from tail is faster
                cur = tail;
                while(size - index > 0) { // move size-index steps
                    cur = cur.prev;
                    index++;
                }
            } else {
                while(index >= 0) {
                    cur = cur.next;
                    index--;
                }
            }
            return cur.val;
        }
        
        public void addAtHead(int val) {
            addAtIndex(0, val);
        }
        
        public void addAtTail(int val) {
            addAtIndex(size, val);
        }
        
        public void addAtIndex(int index, int val) {
            if(index > size){
                return;
            }
            if(index < 0){
                index = 0;
            }
            //找到前驱
            ListNode pre = this.head;
            for(int i=0; i<index; i++){
                pre = pre.next;
            }
            //新建结点
            ListNode newNode = new ListNode(val);
            newNode.next = pre.next;
            newNode.prev = pre;
            pre.next.prev = newNode;
            pre.next = newNode;
            size++;
        }
        
        public void deleteAtIndex(int index) {
            if(index<0 || index>=size){
                return;
            }
            //删除操作
            size--;
            ListNode pre = this.head;
            while(index > 0) {
                pre = pre.next;
                index--;
            }
            pre.next = pre.next.next;
            pre.next.prev = pre;
        }
    }
}
