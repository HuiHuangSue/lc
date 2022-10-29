public class removeLLNodeVal203 {
    /*
     * 
     * head = [1,2,6,3,4,5,6], val = 6 --> [1,2,3,4,5]
       head = [], val = 1 --> []
       head = [7,7,7,7], val = 7 --> []
     */

     /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            // 7->7->2->3->7->1->null
        
            // dummy, pre, cur, loop through cur
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode pre = dummy, cur = head;
            while (cur != null) {
                if (cur.val == val) {
                    pre.next = cur.next; // pre.next
                } else {
                    pre = cur; // pre itself 
                }
                cur = cur.next;
            }
            return dummy.next;


            while(head != null && head.val == val) {
                head = head.next;
            }
            if (head == null) return null;
            // found first non-val head
            ListNode pre = head, cur = head.next;
            while (cur != null) {
                if (cur.val == val) {
                    pre.next = cur.next;
                } else {
                    pre = cur;
                }
                cur = cur.next;
            }
            return head;


            // no pre-node
            while(head != null && head.val == val) {
                head = head.next;
            }
            if (head == null) return null;
            // found first non-val head
            ListNode cur = head;
            while (cur != null) { 
                while (cur.next != null && cur.next.val == val) { // keep throwing away
                    cur.next = cur.next.next;
                }
                cur = cur.next;
            }
            return head;
        }
    }
}