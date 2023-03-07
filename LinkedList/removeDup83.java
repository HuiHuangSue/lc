package LinkedList;

public class removeDup83 {
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
    public ListNode deleteDuplicates83(ListNode head) {
        ListNode cur = head;
        while(cur != null) {
            if(cur.next == null) break;
            if(cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
        // if(head == null || head.next == null) return head;

        // ListNode dummy = new ListNode(-1);
        // dummy.next = head;

        // while(head.next != null) {
        //     if(head.val == head.next.val) {
        //         head.next = head.next.next;
        //     } else {
        //         head = head.next;
        //     }
        // }
        // return dummy.next;
    }
}
}
