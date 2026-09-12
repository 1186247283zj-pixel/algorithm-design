import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LeetCode144 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    // ========== 方法一：递归遍历 ==========
    // 前序：根 → 左 → 右。记录语句放在"刚进入节点"的位置
    // 时间复杂度 O(n) | 空间复杂度 O(h)，h 为树高（最坏 O(n)）
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;                                        // 返回值组合：最终结果由 res 累积而成
    }

    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;                          // base case：空节点，直接返回
        res.add(root.val);                                 // 【前序位置】刚进入节点就记录（根→左→右）
        preorder(root.left, res);                          // 递归调用：遍历左子树
        preorder(root.right, res);                         // 递归调用：遍历右子树
        // 把上面这行记录语句移到两行递归之间 → 中序（左→根→右）；移到之后 → 后序（左→右→根）
    }

    // ========== 方法二：迭代（手动栈）==========
    // 栈是后进先出，所以入栈时要"先压右孩子、再压左孩子"，出栈才先左后右
    // 时间复杂度 O(n) | 空间复杂度 O(h)
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;                      // base case：空树直接返回空列表
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();                    // 出栈即访问
            res.add(cur.val);                              // 【前序位置】
            if (cur.right != null) stack.push(cur.right);  // 先压右（这样后出栈）
            if (cur.left != null) stack.push(cur.left);    // 再压左（这样先出栈）
        }
        return res;                                        // 返回值组合：结果由 res 累积而成
    }

    public static void main(String[] args) {
        LeetCode144 s = new LeetCode144();

        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        System.out.println("用例1 递归: " + s.preorderTraversal(root) + "  (期望 [1,2,3])");
        System.out.println("用例1 迭代: " + s.preorderTraversalIterative(root) + "  (期望 [1,2,3])");

        TreeNode root2 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println("用例2 递归: " + s.preorderTraversal(root2) + "  (期望 [3,9,20,15,7])");
        System.out.println("用例2 迭代: " + s.preorderTraversalIterative(root2) + "  (期望 [3,9,20,15,7])");

        System.out.println("空树 递归: " + s.preorderTraversal(null) + "  (期望 [])");
    }
}
