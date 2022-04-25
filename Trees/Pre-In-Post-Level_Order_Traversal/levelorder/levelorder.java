class BFS {
    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) {
            this.val = v;
        }
    }
    // Serialization: process of converting a data_structure/object --> a sequence of bits so as to store in a file or memory buffer, or transmitted across a network connection link to be reconstructed later
public class Codec297DFS {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serializehelper(root, "");
    }
    private String serializehelper(TreeNode root, String str) {//return final string
        if(root == null) str += "null,";
        else {
            str += str.valueOf(root.val) + ",";//use string.valueOf
            str = serializehelper(root.left, str); //not +=
            str = serializehelper(root.right, str);
        }
        // System.out.println("str:" + str);
        return str;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArr = data.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(dataArr));
        return deserializehelper(list);//return root Node
    }
    private TreeNode deserializehelper(List<String> list) {
        if(list.get(0).equals("null")) {//compare string .equals()
            list.remove(0);
            return null;
        }
        int val = Integer.valueOf(list.get(0));
        
        TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        root.left = deserializehelper(list);
        root.right = deserializehelper(list);
        return root;
    }
}
public class CodecBFS {
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> qq = new LinkedList<>();
        qq.offer(root);
        StringBuilder sb = new StringBuilder();

        while (!qq.isEmpty()) {
            int size = qq.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = qq.poll();
                if (cur != null) {
                    sb.append(cur.val);
                    qq.offer(cur.left);
                    qq.offer(cur.right);
                } else {
                    sb.append("null");
                }
                sb.append(',');
            }
        }
        return sb.toString();
    }
        public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] vals = data.split(",");
        if (vals == null || vals.length == 0) return null;
        
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        TreeNode cur, next;
        Deque<TreeNode> qq = new ArrayDeque<>();
        int size = 0, index = 1;
        qq.offer(root);
        
        while (!qq.isEmpty()) {
            size = qq.size();
            for (int i = 0; i < size; ++i) {
                cur = qq.poll();
                for (int j = index; j < index + 2 && j < vals.length; ++j) {
                    if (vals[j].equals("null")) {
                        if (j % 2 == 1) { cur.left = null;
                        } else { cur.right = null; }
                    } else {
                        next = new TreeNode(Integer.parseInt(vals[j]));
                        qq.offer(next);
                        if (j % 2 == 1) {  cur.left = next;
                        } else {  cur.right = next;  }
                    }
                }
                index += 2;
            }
        }
        return root;
    }
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
public int findBottomLeftValue513(TreeNode root) {
    int res = -1;
    Queue<TreeNode> qq = new ArrayDeque<>(); 
     qq.offer(root);
    while(!qq.isEmpty()) {//can also Queue<>...=new LinkedList<>();then qq.poll()
        int curLevelSize = qq.size();
        // res = qq.peek().val;
        // for (int i = 0; i < curLevelSize; i++) {
        //     TreeNode cur = qq.poll();
        //     if (cur.left != null) qq.add(cur.left);
        //     if (cur.right != null) qq.add(cur.right);}
        
        // from right to left, track & override first value of last row
        for (int i = 0; i < curLevelSize; i++) {
            TreeNode cur = qq.poll();
            res = cur.val;
            if (cur.right != null) qq.add(cur.right);
            if (cur.left != null) qq.add(cur.left);
        }
    }
    return res;
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