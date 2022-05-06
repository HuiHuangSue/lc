public List<List<Integer>> levelOrder102(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    List<TreeNode> qq = new ArrayList<>();
    qq.add(root);//can also Queue<>...=new LinkedList<>();then qq.poll()
    while(!qq.isEmpty()) {
        List<Integer> curLevel = new ArrayList<>();
        int curLevelSize = qq.size();
        for (int i = 0; i < curLevelSize; i++) {
            TreeNode cur = qq.remove(0);
            curLevel.add(cur.val);
            if (cur.left != null) qq.add(cur.left);
            if (cur.right != null) qq.add(cur.right);
        }
        res.add(curLevel);
    }
    return res;
}
public List<Double> averageOfLevels637(TreeNode root) {
    List<Double> res = new ArrayList<>();
    if (root == null) return res;
    List<TreeNode> qq = new ArrayList<>();
    qq.add(root); //can also Queue<>...=new LinkedList<>();then qq.poll()
    while(!qq.isEmpty()) {
        List<Integer> curLevel = new LinkedList<>();
        int curLevelSize = qq.size();
        double sum = 0; //declare as double, otherwise auto-truncate
        for (int i = 0; i < curLevelSize; i++) {
            TreeNode cur = qq.remove(0);
            sum += cur.val;
            if (cur.left != null) qq.add(cur.left);
            if (cur.right != null) qq.add(cur.right);
        }
        res.add(sum/curLevelSize);
    }
    return res;
}
public List<List<Integer>> zigzagLevelOrder103(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    List<TreeNode> qq = new ArrayList<>();
    qq.add(root);//can also Queue<>...=new LinkedList<>();then qq.poll()
    boolean left2Right = true;
    while(!qq.isEmpty()) {
        List<Integer> curLevel = new LinkedList<>();
        int curLevelSize = qq.size();
        for (int i = 0; i < curLevelSize; i++) {
            TreeNode cur = qq.remove(0);
            if (left2Right) {
                curLevel.add(cur.val);
            } else {
                curLevel.add(0, cur.val);
            }
            if (cur.left != null) qq.add(cur.left);
            if (cur.right != null) qq.add(cur.right);
        }
        left2Right = !left2Right;//alternate after every level row processed
        res.add(curLevel);
    }
    return res;
}
public List<List<Integer>> levelOrderBottom107(TreeNode root) {
    List<List<Integer>> res = new LinkedList<>();
    if (root == null) return res;
    List<TreeNode> qq = new ArrayList<>();
    qq.add(root);//can also Queue<>...=new LinkedList<>();then qq.poll()
    while(!qq.isEmpty()) {
        List<Integer> curLevel = new LinkedList<>();
        int curLevelSize = qq.size();
        for (int i = 0; i < curLevelSize; i++) {
            TreeNode cur = qq.remove(0);
            curLevel.add(cur.val);
            if (cur.left != null) qq.add(cur.left);
            if (cur.right != null) qq.add(cur.right);
        }
        res.add(0, curLevel);
    }
    return res;
}
public List<List<Integer>> levelOrder429(Node root) {
    List<List<Integer>> res = new LinkedList<>();
    if (root == null) return res;
    List<Node> qq = new ArrayList<>();
    qq.add(root); //can also Queue<>...=new LinkedList<>();then qq.poll()
    while(!qq.isEmpty()) {
        List<Integer> curLevel = new LinkedList<>();
        int curLevelSize = qq.size();
        for (int i = 0; i < curLevelSize; i++) {
            Node cur = qq.remove(0);
            curLevel.add(cur.val);
            for(Node child : cur.children) {
                if (child != null) qq.add(child);
            }
            
        }
        res.add(curLevel);
    }
    return res;
}
