package Trees;

public class BST_search700_270_insert701_delete450 {
    class searchBST_700 {
        public TreeNode searchBST_Recursion(TreeNode root, int val) {
            if(root == null) return null;
            if(root.val == val) return root;
            else if(root.val < val) return searchBST(root.right, val);
            else return searchBST(root.left, val);
        }

        public TreeNode searchBST_700_Iterative(TreeNode root, int val) {
            while(root != null && root.val != val){
                root = (val < root.val) ? root.left : root.right;
            }
            return root;
        }
    }
    public int closestValue270(TreeNode root, double target) {
        int res = root.val;
        while(root != null) {
            if(Math.abs(target - root.val) < Math.abs(target - res)) {
                res = root.val;
            }
            root = (target < root.val) ? root.left : root.right;
        }
        return res;
    }

    class insertIntoBST_701_ {
        public TreeNode insertIntoBST_701_Iterative(TreeNode root, int val) {
            if(root == null) return new TreeNode(val);
            TreeNode currentNode = root;
            while(currentNode != null) {
                if (currentNode.val > val) {
                    if(currentNode.left == null) {
                        currentNode.left = new TreeNode(val);
                        break;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else { // val>root.val值比root大，要在右边加
                    if(currentNode.right == null) { //右边有空位直接加
                        currentNode.right = new TreeNode(val);
                        break; //加好了
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
            return root;
        }

        public TreeNode insertIntoBST_701_Recursive(TreeNode root, int val) {
            if(root == null) return new TreeNode(val);
            if(root.val < val) {
                root.right = insertIntoBST(root.right, val); // chain up
            } else {
                root.left = insertIntoBST(root.left, val);
            }
            return root;
        }
    }

    // Return the root node reference (possibly updated) of the BST.
    public TreeNode deleteNode450(TreeNode root, int key) {
        if(root == null) return null;
        if(key < root.val) { // key在左边
            // keep recursively perform on left subtree, while staying chained
            root.left = deleteNode(root.left, key);
            return root;
        } else if(root.val < key) { // key在右边
            root.right = deleteNode(root.right, key); // 保留chain着
            return root;
        } else { // 找到了要删的node
        //这些是连接前面recursion的node，return自己的值，前面已经被chain了，不需要再chain
            // no children or 1 children
            if(root.left == null) return root.right; //来连接前面parent node, root.left=这个subtree
            if(root.right == null) return root.left;
            // 右边subtree的最小值
            // right subtree all > currentNodeVal; minimum of right subtree has no left child, as already minimum
            TreeNode min = root.right;
            while(min.left != null) {
                min = min.left;
            }
            root.val = min.val; // replace current node's value to minValue
            root.right = deleteNode(root.right, min.val); // keep deleting right duplicated value, but now 1 child case, as it's the minimum and will have no left child
            return root;

        }
    }

}
