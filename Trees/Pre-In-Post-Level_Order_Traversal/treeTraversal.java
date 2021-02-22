class TemplatedSoln {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {return res;}
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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> sk = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !sk.isEmpty()) {
            // Push until all left nodes are gone
            if (cur != null) {
                sk.push(cur);
                cur = cur.left;
            } else {
                cur = sk.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        // List<Integer> res = new ArrayList<>(); 
        // res.add(0, cur.val);
        
        LinkedList<Integer> res = new LinkedList<>();//has to be LinkedList then LinkedList
        Deque<TreeNode> sk = new ArrayDeque<>();
        if (root == null) return res;
        TreeNode cur = root;
        
        while (!sk.isEmpty() || cur != null) {
            if (cur != null) {
                sk.push(cur);
                res.addFirst(cur.val);
                cur = cur.right;
            } else {
                cur = sk.pop();
                cur = cur.left;
            }
        }
        return res;
    }

    // Each level has 2^i, which == current Queue size
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> qq = new LinkedList<>();
        qq.add(root);
        while (!qq.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int count = qq.size();
            for (int i = 0; i < count; i++) {
                TreeNode cur = qq.poll();
                level.add(cur.val);
                if (cur.left != null) {
                    qq.add(cur.left);
                }
                if (cur.right != null) {
                    qq.add(cur.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    // bottom up
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> qq = new LinkedList<>();
        qq.add(root);
        while (!qq.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int count = qq.size();
            for (int i = 0; i < count; i++) {
                TreeNode cur = qq.poll();
                level.add(cur.val);
                if (cur.left != null) {
                    qq.add(cur.left);
                }
                if (cur.right != null) {
                    qq.add(cur.right);
                }
            }
            res.add(0, level);
        }
        return res;
    }
}

class Recursive {
    // level order BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }
    
    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) {
            res.add(new LinkedList<Integer>());
        }
        res.get(height).add(root.val);
        levelHelper(res, root.left, height+1);
        levelHelper(res, root.right, height+1);
    }

    // bottom up BFS. Reverse array, or addFirst, and update size-1-height_th array item.
    public List<List<Integer>> levelOrderBottomUp(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }
    
    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) {
            res.add(0, new LinkedList<Integer>());
        }
        res.get(res.size() - 1 - height).add(root.val);
        levelHelper(res, root.left, height+1);
        levelHelper(res, root.right, height+1);
    }

    // pre-in-post order recursive
    public List<Integer> orderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {return res;}
        traverseTemplate(root, res);
        return res;
    }

    public void PreOrderTraverse(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        traverse(root.left, res);
        traverse(root.right, res);
    }

    public void inOrderTraverse(TreeNode root, List<Integer> res) {
        if (root != null) {
            traverse(root.left, res);
            res.add(root.val);
            traverse(root.right, res);
        }
    }

    public void postOrderTraverse(TreeNode root, List<Integer> res) {
        if (root != null) {
            traverse(root.left, res);
            traverse(root.right, res);
            res.add(root.val);
        }
    }
}

class UnTemplated{
    public List<Integer> preorderTraversal(TreeNode root) {
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
}