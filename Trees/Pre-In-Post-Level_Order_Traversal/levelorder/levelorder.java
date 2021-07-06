class DFS {
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

    // ZigZag
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> qq = new LinkedList<>();
        qq.add(root);
        boolean leftToRight = true;
        while (!qq.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int count = qq.size();
            for (int i = 0; i < count; i++) {
                TreeNode cur = qq.poll();
                if (leftToRight) {
                    level.add(cur.val);
                } else {
                    level.add(0, cur.val);
                }
                if (cur.left != null) {
                    qq.add(cur.left);
                }
                if (cur.right != null) {
                    qq.add(cur.right);
                }
            }
            leftToRight = !leftToRight;
            res.add(level);
        }
        return res;
    }

    // N-ary
    public List<List<Integer>> levelOrder-N-nary(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> qq = new LinkedList<>();
        qq.add(root);
        while (!qq.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int count = qq.size();
            for (int i = 0; i < count; i++) {
                Node cur = qq.poll();
                level.add(cur.val);
                for (Node n : cur.children) {
                    if (n != null) {
                        qq.add(n);
                    }
                }
            }
            res.add(level);
        }
        return res;
    }
}

class BFS {
    // level order BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }
    
    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        List<Integer> list = (res.size() > height) ? res.get(height) : new LinkedList<Integer>();
        list.add(root.val);
        if (res.size() <= height) {
            res.add(list);
        }
        levelHelper(res, root.left, height+1);
        levelHelper(res, root.right, height+1);
    }

    public void levelHelperII(List<List<Integer>> res, TreeNode root, int height) {
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

    // ZigZag BFS
    // Or can level % 2 and tell
    public List<List<Integer>> zigzagLevelOrderBFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        boolean leftRight = true;
        levelHelper(res, root, 0, true);
        return res;
    }
    
    public void levelHelper(List<List<Integer>> res, TreeNode root, int height, boolean leftRight) {
        if (root == null) return;
        List<Integer> list = (res.size() > height) ? res.get(height) : new LinkedList<Integer>();
        if (leftRight) {
            list.add(root.val);
        } else {
            list.add(0, root.val);
        }
        leftRight = !leftRight;
        if (res.size() <= height) {
            res.add(list);
        }
        levelHelper(res, root.left, height+1, leftRight);
        levelHelper(res, root.right, height+1, leftRight);
    }

    // N-nary-level order
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res, 0);
        return res;
    }
    public void helper(Node root, List<List<Integer>> res, int height) {
        if (root == null) return;
        List<Integer> list = (res.size() > height) ? res.get(height) : new LinkedList<Integer>();
        list.add(root.val);
        if (res.size() <= height) {
            res.add(list);
        }
        for (Node cur : root.children) {
            helper(cur, res, height + 1);
        }
    }

}