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