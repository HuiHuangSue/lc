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
    // Double push
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> sk = new ArrayDeque<>();
        sk.push(root);
        sk.push(root);
        while (!sk.isEmpty()) {
            TreeNode cur = sk.pop();
            if (cur != null && sk.peek() == cur) {
                if (cur.right != null) {
                    sk.push(cur.right);
                    sk.push(cur.right);
                }
                if (cur.left != null) {
                    sk.push(cur.left);
                    sk.push(cur.left);
                }
            } else {
                res.add(cur.val);
            }
        }
        return res;
    }
    // Templated
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> sk = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !sk.isEmpty()) {
            if (cur != null) {
                sk.push(cur);
                res.add(0, cur.val);
                cur = cur.right;
            } else {
                cur = sk.pop();
                cur = cur.left;
            }
        }
        return res;
    }

    // Recursive
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root);
        return res;
    }
    public void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        helper(res, root.left);
        helper(res, root.right);
        res.add(root.val);
    }
}