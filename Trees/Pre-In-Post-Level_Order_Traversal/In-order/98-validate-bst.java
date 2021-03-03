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
    // 1. In-order traversal
    // 2. Recursion with backtrack
    // 3. use 3 stacks
    // 4. 可以recursion放入array，然后从左到右变比较是否升序 O(n),O(n)

    // 1
    public boolean isValidBSTIterative(TreeNode root) {
        Stack<TreeNode> sk = new Stack<>();
        TreeNode cur = root;
        Integer pre = null;
        while (!sk.isEmpty() || cur != null) {
            if (cur != null) {
                sk.push(cur);
                cur = cur.left;
            } else {
                cur = sk.pop();
                if (pre != null && pre >= cur.val) {
                    return false;
                }
                pre = cur.val;
                cur = cur.right;
            }
        }
        return true;
    }

    // 2
    // -2^32, change to long/double then dont need null, as they support 64 bits
    // if using integer, use null to represent +- infinite, to compare with
    public boolean isValidBST(TreeNode root) {
        return backtrack(root, null, null);
    }
    private boolean backtrack(TreeNode root, Integer low, Integer high) {
        if (root == null) return true;
        // if null, return true; if node val equal, we return false as well.
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) return false; 
        return backtrack(root.left, low, root.val) && backtrack(root.right, root.val, high);
    }

    // 3
    Stack<TreeNode> sk = new Stack<>();
    Stack<Integer> highSk = new Stack<>();
    Stack<Integer> lowSk = new Stack<>();

    private void update(TreeNode root, Integer low, Integer high) {
        sk.push(root);
        highSk.push(high);
        lowSk.push(low);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Integer high = null, low = null, val = null;
        update(root, high, low);
        while (!sk.isEmpty()) {
            root = sk.pop();
            high = highSk.pop();
            low = lowSk.pop();
            if (root == null) continue; // ow. null pointer exception
            if ((low != null && root.val <= low) || (high != null && high <= root.val)) return false;
            update(root.left, low, root.val);
            update(root.right, root.val, high);
        }
        return true;
    }

    // 4
    public boolean isValidBST(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        for (int i = 1; i <= res.size() - 1; i++) {
            if (res.get(i) <= res.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    private void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }


}