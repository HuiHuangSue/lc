/*
Input
["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
Output
[null, 3, 7, true, 9, true, 15, true, 20, false]

Explanation
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // return 3
bSTIterator.next();    // return 7
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 9
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 15
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 20
bSTIterator.hasNext(); // return False
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
0 <= Node.val <= 106
At most 105 calls will be made to hasNext, and next.
*/

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
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
 // Time/SpaceO<N>, O<1> for next/hasNext
class BSTIteratorFlat {
    List<Integer> list;
    Integer index;
    public BSTIterator(TreeNode root) {
        this.list = new ArrayList<>();
        this.index = -1;
        this.buildArray(root);
    }
    private void buildArray(TreeNode root) {
        if (root == null) return;
        buildArray(root.left);
        list.add(root.val);
        buildArray(root.right);
    }
    
    public int next() {
        return this.list.get(++index); // starts with 0
    }
    
    public boolean hasNext() {
        // 第一次call next返回root值
        // index=lastElement时，就没有next了
        return this.index < (list.size() - 1);
    }
}

// space O(N), hasNext O(1), next worstO(N), amortized/average O(1)
class BSTIteratorStack {
    
    Stack<TreeNode> sk;

    public BSTIterator(TreeNode root) {
        this.sk = new Stack<>();
        this.leftMostNode(root);
    }
    private void leftMostNode(TreeNode root) {
        while (root != null) {
            sk.push(root);
            root = root.left;
        }
    }
    
    public int next() {
        TreeNode topNode = this.sk.pop();
        if (topNode.right != null) {
            leftMostNode(topNode.right);
        }
        return topNode.val;
    }
    
    public boolean hasNext() {
        return !this.sk.isEmpty();
    }
}