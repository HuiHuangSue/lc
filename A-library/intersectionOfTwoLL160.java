public class intersectionOfTwoLL160 {
    /*
     * Given the heads of two singly linked-lists headA and headB, 
     * return the node at which the two lists intersect. 
     * If the two linked lists have no intersection at all, return null.
     * Note that the linked lists must retain their original structure after the function returns.
     */


     /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    public class Solution {
        // 1->-2->3->4->5->6->7->null   8->9->4 
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // get len of each, get gap. 尾端对齐，一组一组比较
            int lenA = 0, lenB = 0;
            ListNode curA = headA, curB = headB;
            while (curA != null) {
                lenA++;
                curA = curA.next;
            }
            while (curB != null) {
                lenB++;
                curB = curB.next;
            }
            // reset
            curA = headA;
            curB = headB;
            // let A be longer, if not, swap len and nodes
            if (lenA < lenB) {
                int tmpLen = lenA; lenA = lenB; lenB = tmpLen;
                ListNode tmpNode = curA; curA = curB; curB = tmpNode;
            }
            int gap = Math.abs(lenA - lenB);
            while(curA != null && gap > 0) { //curA head 对齐 curB's head
                curA = curA.next;
                gap--; // remember to update
            }
            while (curA != null) {
                if (curA == curB) return curA;
                curA = curA.next;
                curB = curB.next;
            }
            return null;
        }
    }
}
