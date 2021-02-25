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
    // Method to derive N-ary iterative
    public List<Integer> preorderTraversalPushAndOnlyCheckStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> sk = new Stack<>();
        if (root == null) return res;
        sk.push(root);
        
        while (!sk.empty()) {
            TreeNode cur = sk.pop();
            res.add(cur.val);
            // Right first, then Left. 
            if (cur.right != null) {
                sk.push(cur.right);
            }
            if (cur.left != null) {
                sk.push(cur.left);
            }
        }
        return res;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> sk = new ArrayDeque<>();
        TreeNode cur = root;
        sk.push(cur);
        while (!sk.isEmpty()) {
            while (cur != null) {
                sk.push(cur);
                res.add(cur.val);
                cur = cur.left;
            }
            cur = sk.pop();
            cur = cur.right;
        }
        return res;
    }

    // Templated Stack/Deque
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> sk = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !sk.isEmpty()) {
            if (cur != null) {
                sk.push(cur);
                res.add(cur.val);
                cur = cur.left;
            } else {
                cur = sk.pop();
                cur = cur.right;
            }
        }
        return res;
    }

    // Recursive
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root);
        return res;
    }
    public void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        res.add(root.val);
        helper(res, root.left);
        helper(res, root.right);
    }
}

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class preOrder-N-ary {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Node> sk = new ArrayDeque<>();
        sk.push(root);
        while (!sk.isEmpty()) {
            Node cur = sk.pop();
            res.add(cur.val);
            if (cur.children != null) {
                // size() - 1
                for (int i = cur.children.size() - 1; i >= 0; i--) {
                    sk.push(cur.children.get(i));
                }
            }
        }
        return res;
    }

    // N-ary recursive preorder
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root);
        return res;
    }
    public void helper(List<Integer> res, Node root) {
        if (root == null) return;
        res.add(root.val);
        for (Node c : root.children) {
            helper(res, c);
        }
    }
}