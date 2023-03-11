package Trees;

public class tree {
    public boolean insert(int data) {
        Node node = new Node(data);
        if(root == null) {
            root = node;
            return true;
        }
        Node targetNode = root;
        while(targetNode != null) {
            if(data == targetNode.data) return false; // duplicate not allowed
            else if(data > targetNode.data) {
                if(targetNode.right == null) {
                    targetNode.right = node;
                    return true;
                } else {
                    targetNode = targetNode.right;
                }
            } else {
                if(targetNode.left == null) {
                    targetNode.left = node;
                    return true;
                } else {
                    targetNode = targetNode.left;
                }
            }
            return true;
        }
    }

}

private Node leftLeftRotation(Node node) {
    // node = A; leftChild = B in graph
    Node leftChildNode = node.left;
    node.left = leftChildNode.right;
    leftChildNode.right = node;
    // Refresh heights
    node.height = Math.max(height(node.left), height(node.right)) + 1;// A 下沉一层
    leftChildNode.height = Math.max(height(leftChildNode.left), node.height) + 1; //B's height
    return leftChildNode; //返回B
}
private Node rightRightRotation(Node node) {
    Node rightChildNode = node.right; //B
    node.right = rightChildNode.left;
    rightChildNode.left = node;
    node.height = Math.max(height(node.left), height(node.right)) + 1;// A 下沉一层
    rightChildNode.height = Math.max(height(rightChildNode.right), node.height) + 1; //B's height
    return rightChildNode; //返回B
}
private Node leftRightRotation(Node node) {
    // Left rotation on left children first, rotate on B
    node.left = rightRightRotation(node.left);
    // then right rotation on node itself; rotate on C
    return leftLeftRotation(node);
}
private Node rightLeftRotation(Node node) {
    node.right = leftLeftRotation(node.right);
    return rightRightRotation(node);
}

public void insert(int data){
    root = insert(root, data);
}
private Node insert(Node node, int data) {
    if (node == null) node = new Node(data);
    else {
        if (data < node.data) {
            node.left = insert(root.left, data);
            // if AVL lost balance, adjust
            if(node.getBalance() == 2) {
                if(data < node.left.data) {
                    node = leftLeftRotation(node);
                } else {
                    node = leftRightRotation(node);
                }
            }
        } else if(data > node.data) {
            node.right = insert(root.right, data);
            if(node.getBalance() == -2) {
                if(data > node.right.data) {
                    node = rightRightRotation(node);
                } else {
                    node = rightLeftRotation(node);
                }
            }
        } else {
            System.out.println("dup");
        }
    }
    node.height = Math.max(height(node.left), height(node.right)) + 1;
    return node;
}