public class LeetCode206 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // ========== 方法一：递归思维 ==========
    // reverseList(head) 的含义：把以 head 开头的链表反转，并返回新的头节点
    // 时间复杂度 O(n) | 空间复杂度 O(n)（递归栈深度为 n）
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;   // base case：空链表或单节点，本身就是反转后的结果
        ListNode newHead = reverseList(head.next);            // 递归调用：先把 head.next 之后的整条链表反转好
        head.next.next = head;                                // 返回值组合：让后一个节点回过头来指向 head
        head.next = null;                                     //             head 成为尾节点，断开原 next 防止成环
        return newHead;                                       //             反转后的头始终是原链表的最后一个节点
    }

    // ========== 方法二：三指针迭代 ==========
    // prev —— 已反转部分的头（前一个节点），初始为 null
    // curr —— 当前正在处理的节点，初始为头节点
    // next —— 提前保存 curr 的下一个节点，防止掉转指针后"断链找不到路"
    // 时间复杂度 O(n) | 空间复杂度 O(1)
    public ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;                                 // 已反转部分的头
        ListNode curr = head;                                 // 当前待处理节点
        while (curr != null) {
            ListNode next = curr.next;                        // ① 先保存下一个，别丢
            curr.next = prev;                                 // ② 让当前节点的 next 指向前一个节点（掉头）
            prev = curr;                                      // ③ 三个指针一起前移：prev 走到 curr
            curr = next;                                      //                        curr 走到 next
        }
        return prev;                                          // 循环结束时 prev 就是新链表的头
    }

    public static void main(String[] args) {
        LeetCode206 s = new LeetCode206();

        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println("递归  : " + print(s.reverseList(head1)));

        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println("三指针: " + print(s.reverseListIterative(head2)));

        System.out.println("空链表: " + print(s.reverseList(null)));

        // 期望输出：
        // 递归  : [5,4,3,2,1]
        // 三指针: [5,4,3,2,1]
        // 空链表: []
    }

    static String print(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        for (ListNode p = head; p != null; p = p.next) {
            sb.append(p.val);
            if (p.next != null) sb.append(",");
        }
        return sb.append("]").toString();
    }
}
