public class BinaryTreeDivideConquerTemplate {
    // Binary tree related (99%); 
    // divide into subgroups and then merge handled results; Array related (10%)
    // O(n)--O(n)

    public ResultType divideConquer(TreeNode node) {
        // 递归出口. 一般处理 node == null 就够了. // 大部分情况不需要处理 node == leaf
        if (node == null) return ...;
        ResultType leftResult = divideConquer(node.left);
        ResultType rightResult = divideConquer(node.right);
        ResultType result = merge leftResult and rightResult;
        return result;
    }
}