public class reverseLL206 {
    /*
    Given the head of a singly linked list, reverse the list, and return the reversed list.
    Input: head = [1,2,3,4,5] --> [5,4,3,2,1]
    [] --> []
    */    
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;// hold otherwise lost
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }
}
