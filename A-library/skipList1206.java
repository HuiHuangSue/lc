// code len shorter than treap and red-black tree w/ same function & performance
// Avg O(logn), space O(n)
// Duplicates may exist.
class Skiplist_1206 {
    class Node {
        int val;
        Node next, down;
        public Node(int val, Node next, Node down) {
            this.val = val;
            this.next = next;
            this.down = down;
        }
    }
    Random rand = new Random();
    Node head = new Node(-1, null, null);
    public Skiplist() {}

    public void add(int num) {
        // Stack 用来存每一层往下走前的node，好标记coinflip时要不要在那层加node
        Stack<Node> sk = new Stack<>();
        Node cur = head;
        while(cur != null) { //最后结束是底层node的down，会是null
            while(cur.next != null && cur.next.val < num) { //找到要加位置的前一个
                cur = cur.next; // 往右边移
            }
            sk.push(cur); //往下转前存到stack里，等下回头看要不要在那层加node
            cur = cur.down; // 往下走
        }
        Node down = null;
        boolean insert = true; //coinflip
        while(insert && !sk.isEmpty()) { //coinflip为正，并且sk一直还在
            cur = sk.pop(); //从底层往高层pop，往后加node
            cur.next = new Node(num, cur.next, down); //cur.next一开始是之前的cur.next连上
            down = cur.next; //设置好往上一层的down，连接新加的节点.  连在了新加的节点!!!
            insert = rand.nextDouble() < 0.5; // 决定下一层加不加
        }
        if(insert) head = new Node(-1, null, head);//顶上加一层，往下接好原来的head
    }

    public boolean search(int target) {
        Node cur = head;
        while(cur != null) { //最后结束是底层node的down，会是null
            while(cur.next != null && cur.next.val < target) { //找到要加位置的前一个
                cur = cur.next; // 往右边移
            }
            if(cur.next != null && cur.next.val == target) { //找到要加位置的前一个
                return true;
            }
            cur = cur.down; // 往下一层继续找
        }
        return false;

    }

    public boolean erase(int num) {
        Node cur = head;
        boolean found = false;
        while(cur != null) { //最后结束是底层node的down，会是null; 一层一层删
            while(cur.next != null && cur.next.val < num) { //找到要加位置的前一个
                cur = cur.next; // 往右边移
            }
            if(cur.next != null && cur.next.val == num){ //找到要加位置的前一个
                found = true;
                cur.next = cur.next.next;
            }
            cur = cur.down;
        }
        return found;
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */