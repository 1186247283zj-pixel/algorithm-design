import java.util.Arrays;

public class LeetCode70 {

    // ========== 方法一：朴素递归 ==========
    // 到达第 n 阶 = 到达第 n-1 阶的走法 + 到达第 n-2 阶的走法
    // 时间复杂度 O(2^n)（有大量重复计算）| 空间复杂度 O(n)
    public int climbStairs(int n) {
        if (n == 1) return 1;                              // base case：到第 1 阶只有 1 种走法
        if (n == 2) return 2;                              // base case：到第 2 阶有 2 种走法（1+1 或 直接2）
        return climbStairs(n - 1) + climbStairs(n - 2);    // 递归调用 + 返回值组合
    }

    // ========== 方法二：记忆化搜索（备忘录）==========
    // 时间复杂度 O(n)（每个子问题只算一次）| 空间复杂度 O(n)
    private int[] memo;

    public int climbStairsMemo(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);                             // -1 表示尚未计算
        return f(n);
    }

    private int f(int n) {
        if (n == 1) return 1;                              // base case
        if (n == 2) return 2;                              // base case
        if (memo[n] != -1) return memo[n];                 // 备忘录：算过就直接返回，不再重复递归
        memo[n] = f(n - 1) + f(n - 2);                     // 递归调用 + 返回值组合，并存入备忘录
        return memo[n];
    }

    // ========== 方法三：递推（自底向上）==========
    // 时间复杂度 O(n) | 空间复杂度 O(1)（只用两个滚动变量）
    public int climbStairsIterative(int n) {
        if (n <= 2) return n;                              // base case：f(1)=1, f(2)=2
        int prev2 = 1;                                     // f(1)
        int prev1 = 2;                                     // f(2)
        int cur = 0;
        for (int i = 3; i <= n; i++) {
            cur = prev1 + prev2;                           // 递推：f(i) = f(i-1) + f(i-2)
            prev2 = prev1;                                 // 滚动前移
            prev1 = cur;
        }
        return cur;
    }

    public static void main(String[] args) {
        LeetCode70 s = new LeetCode70();

        for (int n = 1; n <= 10; n++) {
            System.out.printf("n=%2d -> 朴素递归=%d, 备忘录=%d, 递推=%d%n",
                    n, s.climbStairs(n), s.climbStairsMemo(n), s.climbStairsIterative(n));
        }
        // 期望：n=3 输出 3；n=10 输出 89

        System.out.println("\nn=45 备忘录 = " + s.climbStairsMemo(45));
        System.out.println("n=45 递推   = " + s.climbStairsIterative(45));
    }
}
