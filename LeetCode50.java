public class LeetCode50 {

    // ========== 快速幂（递归）==========
    // x^n = x^(n/2) * x^(n/2)；n 为奇数时再多乘一个 x
    // 时间复杂度 O(log n) | 空间复杂度 O(log n)（递归栈深度）
    public double myPow(double x, int n) {
        long N = n;                                        // 用 long 接住，避免 n = Integer.MIN_VALUE 时 -n 溢出
        return N >= 0 ? power(x, N) : 1.0 / power(x, -N);  // 负指数转正：x^(-N) = 1 / x^N
    }

    private double power(double x, long n) {
        if (n == 0) return 1.0;                            // base case：任何数的 0 次幂都是 1
        double half = power(x, n / 2);                     // 递归调用：只算一次"一半"，结果复用两次，所以是 O(log n)
        return n % 2 == 0 ? half * half                    // 返回值组合：n 为偶数 → x^(n/2) * x^(n/2)
                          : half * half * x;               //             n 为奇数 → 再乘一个 x
    }

    public static void main(String[] args) {
        LeetCode50 s = new LeetCode50();

        System.out.println("2.0^10          = " + s.myPow(2.0, 10) + "  (期望 1024.0)");
        System.out.println("2.1^3           = " + s.myPow(2.1, 3) + "  (期望 9.261)");
        System.out.println("2.0^-2          = " + s.myPow(2.0, -2) + "  (期望 0.25)");
        System.out.println("1.0^-2147483648 = " + s.myPow(1.0, Integer.MIN_VALUE) + "  (期望 1.0，验证溢出处理)");
        System.out.println("0.0^0           = " + s.myPow(0.0, 0) + "  (期望 1.0)");
    }
}
