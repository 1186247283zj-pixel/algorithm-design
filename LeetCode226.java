import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode226 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    // ========== 分解思维（分治）==========
    // 翻转整棵树 = 翻转左子树 + 翻转右子树 + 交换左右孩子
    // 时间复杂度 O(n) | 空间复杂度 O(h)，h 为树高（最坏 O(n)）
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;                     // base case：空树无需翻转
        TreeNode left = invertTree(root.left);             // 递归调用：先翻转左子树
        TreeNode right = invertTree(root.right);           // 递归调用：先翻转右子树
        root.left = right;                                 // 返回值组合：把两个翻转好的子树交换位置
        root.right = left;                                 //             （原来在右的放左边，原来在左的放右边）
        return root;                                       //             返回翻转后的根节点
    }

    public static void main(String[] args) {
        LeetCode226 s = new LeetCode226();

        TreeNode root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));

        System.out.println("翻转前: " + levelOrder(root));
        System.out.println("翻转后: " + levelOrder(s.invertTree(root)) + "  (期望 [4,7,2,9,6,3,1])");
        System.out.println("空树  : " + levelOrder(s.invertTree(null)) + "  (期望 [])");
    }

    static String levelOrder(TreeNode root) {
        if (root == null) return "[]";
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<String> out = new ArrayList<>();
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) {
                out.add("null");
            } else {
                out.add(String.valueOf(cur.val));
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
        int end = out.size();
        while (end > 0 && out.get(end - 1).equals("null")) end--;
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < end; i++) {
            sb.append(out.get(i));
            if (i != end - 1) sb.append(",");
        }
        return sb.append("]").toString();
    }
}
