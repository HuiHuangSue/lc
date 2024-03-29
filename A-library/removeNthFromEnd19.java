public class removeNthFromEnd19 {
    /*
     * head = [1,2,3,4,5], n = 2 ---> [1,2,3,5]
       head = [1], n = 1 ---> []
       head = [1,2], n = 1 ---> [1]
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
    public ListNode removeNthFromEnd19(ListNode head, int n) {
        // fast=head, stop when n>0, slow=dummy as want to keep pre-slow to delete
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = head;
        while (fast != null && n > 0) {
            fast = fast.next;
            n--;
        }
        ListNode slow = dummy;
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy; // OR fast starts at head, slow start at dummy
        while(n > 0 && fast != null) {
            fast = fast.next;
            n--;
        }
        while(fast.next != null) { // when fast starts from dummy, make sure stop 1 step early to allow SLOW stop before launching the node, to do deletion
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
}
