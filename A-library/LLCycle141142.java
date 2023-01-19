public class LLCycle141142 {
    /*
     * 1. HashSet until finding a duplicate. Space is O(n)
     * 2. 环形跑道，快的总能再次超过慢的。fast and slow pointers. fast pointer跑完一圈会相遇slow pointers
     */
    public boolean hasCycle141(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /*
     * 起点-->--D---交点---S1--相遇点----
                   |                  |
                    ------------S2-----
       Time相同，fast速度比slow双倍。fast路程: D+S1+S2+S1; slow路程D+S1
       D+S1+S2+S1 = 2* (D+S1) --> S2=D --> 相遇后，fast放head，走一边D的路程，和slow继续走完剩下的路程，相同的地方是交点
                    */
    public ListNode detectCycle142(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = head; // fast放最前，当fast和slow再次相遇的时候是节点
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }
}
