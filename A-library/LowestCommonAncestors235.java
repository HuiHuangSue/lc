public class LowestCommonAncestors235 {
    // https://www.youtube.com/watch?v=gs2LMfuOR9k&ab_channel=NeetCode
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // max is tree's height O(logn); 
        TreeNode cur = root;
        while (cur != null) {
            if (p.val > cur.val && q.val > cur.val) { //7,9
                cur = cur.right; // go to the right tree
            } else if (p.val < cur.val && q.val < cur.val) {// 0,3
                cur = cur.left; // go to the left branch
            } else { // one left, one right --> itself
                return cur;
            }
        }
        return cur;
    }
}
