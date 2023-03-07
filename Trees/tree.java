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