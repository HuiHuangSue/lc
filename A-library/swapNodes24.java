public class swapNodes24 {
    /*
     * 1->2->3->4->null --->  2->1->4->3->null
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
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head; // head is recorded, can be used in iterations to move
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode tmp = head.next.next;
            // 1st iteration expect 0 -> 2 -> 1 -> 3 -> 4 -> null
            // just change direction from left to right
            prev.next = head.next;
            prev.next.next = head;
            head.next = tmp;
            // finished arrows changes. now update params 
            prev = head;
            head = tmp;
        }
        return dummy.next;
    }
}
}
