/*
给你一个二叉搜索树和其中的某一个结点，请你找出该结点在树中顺序后继的节点。

结点 p 的后继是值比 p.val 大的结点中键值最小的结点。
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> sk = new ArrayDeque<>();
        TreeNode cur = root;
        Boolean next = false;
        while (cur != null || !sk.isEmpty()) {
            if (cur != null) {
                sk.push(cur);
                cur = cur.left;
            } else {
                cur = sk.pop();
                if (next) {
                    return cur;
                }
                if (cur.val == p.val) {
                    next = true;
                }
                cur = cur.right;
            }
        }
        return null;
    }
}