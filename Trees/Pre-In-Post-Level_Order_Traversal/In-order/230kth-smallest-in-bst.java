/*
Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.]
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104
 */
 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> sk = new Stack<>();
        TreeNode cur = root;
        while (!sk.isEmpty() || cur != null || k > 0) { // k>0
            if (cur != null) {
                sk.push(cur);
                cur =cur.left;
            } else {
                cur = sk.pop();
                k--;
                if (k == 0) return cur.val; // check first, then update cur. o.w can be null

                cur = cur.right;
            }
        }
        return -1;
    }
}