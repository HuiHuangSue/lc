class TemplatedSoln {
    // 144
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
    // N-ary-preorder
    public List<Integer> preorder-N-nary(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {return res;}
        Deque<Node> sk = new ArrayDeque<>();
        sk.push(root);
        while (!sk.isEmpty()) {
            Node cur = sk.pop();
            res.add(cur.val);
            for (int i = cur.children.size() - 1; i >= 0; i--) {
                sk.push(cur.children.get(i));
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

    public List<Integer> postorderTraversalDoublePush(TreeNode root) {
        Deque<TreeNode> stack=new LinkedList<>();
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        TreeNode cur=null;
        stack.push(root);
        stack.push(root);
        
        while(!stack.isEmpty()){
            cur=stack.pop();
            if(!stack.isEmpty() && stack.peek()==cur){
                if(cur.right!=null){
                    stack.push(cur.right);
                    stack.push(cur.right);
                }
                if(cur.left!=null){
                    stack.push(cur.left);
                    stack.push(cur.left);
                }                
                
            }else{
                res.add(cur.val);
            }         
        }        
        return res;
    }

    public List<Integer> postorder-N-ary-DoublePush(Node root) {
        Deque<Node> sk =new LinkedList<>();
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        Node cur=null;
        sk.push(root);
        sk.push(root);
        
        while(!sk.isEmpty()){
            cur = sk.pop();
            if(!sk.isEmpty() && sk.peek()==cur){
                // from right to left
                for (int i = cur.children.size() - 1; i >= 0; i--) {
                    Node c = cur.children.get(i);
                    if (c != null) {
                        sk.push(c);
                        sk.push(c);
                    }
                }       
                
            } else {
                res.add(cur.val);
            }         
        }        
        return res;
    }
}

class Recursive {
    /* Pre-order */
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
    public void Nnary-Preorder-helper(Node root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        for (Node c : root.children) {
            helper(c, res);
        }
    }

    /* Inorder */
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
    public void postOrder-N-ary(Node root, List<Integer> res) {
        if (root != null) {
            for (Node n : root.children) {
                helper(n, res);
            }
            res.add(root.val);
        }
    }
}
