# 算法设计与实现 —— 递归专题作业

## 题目清单

| 文件 | 题目 | 实现 | 复杂度 |
|---|---|---|---|
| `LeetCode104.java` | 二叉树的最大深度 | ① 分解思维 ② 递归思维 | 时间 O(n) / 空间 O(h) |
| `LeetCode144.java` | 二叉树的前序遍历 | ① 递归 ② 迭代（手动栈） | 时间 O(n) / 空间 O(h) |
| `LeetCode206.java` | 反转链表 | ① 递归 ② 三指针迭代 | O(n)/O(n) · O(n)/O(1) |
| `LeetCode226.java` | 翻转二叉树 | 分解思维 | 时间 O(n) / 空间 O(h) |
| `LeetCode50.java` | Pow(x, n) | 快速幂（递归） | 时间 O(log n) / 空间 O(log n) |
| `LeetCode70.java` | 爬楼梯 | ① 朴素递归 ② 记忆化搜索 ③ 递推 | O(2^n)/O(n) · O(n)/O(n) · O(n)/O(1) |

> h 为树高，最坏 O(n)，平衡树 O(log n)。

## 代码规范

- **文件名 = 题号**，每道题都是完整可运行的 Java 代码，类中带 `main` 可直接运行
- **注释写在代码行后面**，标注三要素：
  - `base case` —— 递归出口 / 递推起点
  - `递归调用` —— 子问题是什么
  - `返回值组合` —— 子问题的结果如何拼成最终答案
- 每个方法上方标注**时间复杂度与空间复杂度**

## 编译与运行

```bash
javac -encoding UTF-8 -d bin *.java
java  -cp bin LeetCode104
java  -cp bin LeetCode144
java  -cp bin LeetCode206
java  -cp bin LeetCode226
java  -cp bin LeetCode50
java  -cp bin LeetCode70
```

环境：JDK 8 及以上（本项目用 JDK 21 验证通过）。

每个 `main` 内置 LeetCode 官方示例用例，输出与期望值对照。

## Eclipse 导入

`File` → `Import…` → `General` → `Existing Projects into Workspace` → 选择本目录 → `Finish`

## 说明

LeetCode 111（二叉树的最小深度）为选做题，本次未提交。
