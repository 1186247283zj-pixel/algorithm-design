public class LeetCode104 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    // ========== 方法一：分解思维（分治）==========
    // 整棵树的深度 = max(左子树深度, 右子树深度) + 1
    // 时间复杂度 O(n) | 空间复杂度 O(h)，h 为树高（最坏 O(n)，平衡树 O(log n)）
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;                        // base case：空树深度为 0
        int left = maxDepth(root.left);                    // 递归调用：求左子树的深度
        int right = maxDepth(root.right);                  // 递归调用：求右子树的深度
        return Math.max(left, right) + 1;                  // 返回值组合：取较深的一边，加上当前节点自己
    }

    // ========== 方法二：递归思维（遍历 + 回溯）==========
    // 带着当前深度一路往下走，走到叶子节点时更新最大值（结果放在外部变量里）
    // 时间复杂度 O(n) | 空间复杂度 O(h)
    private int maxResult = 0;

    public int maxDepthByTraversal(TreeNode root) {
        maxResult = 0;                                     // 重置成员变量
        traverse(root, 1);                                 // 根节点所在深度为 1
        return maxResult;                                  // 返回值组合：结果由外部变量累积而成
    }

    private void traverse(TreeNode root, int depth) {
        if (root == null) return;                          // base case：空节点，遍历到头，返回
        if (root.left == null && root.right == null) {     // 走到叶子节点
            maxResult = Math.max(maxResult, depth);        // 用当前深度挑战最大值
        }
        traverse(root.left, depth + 1);                    // 递归调用：带着 depth+1 进入左子树
        traverse(root.right, depth + 1);                   // 递归调用：带着 depth+1 进入右子树
    }

    public static void main(String[] args) {
        LeetCode104 s = new LeetCode104();

        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        System.out.println("分解思维: " + s.maxDepth(root) + "  (期望 3)");
        System.out.println("递归思维: " + s.maxDepthByTraversal(root) + "  (期望 3)");
        System.out.println("单节点  : " + s.maxDepth(new TreeNode(1)) + "  (期望 1)");
        System.out.println("空树    : " + s.maxDepth(null) + "  (期望 0)");
    }
}
