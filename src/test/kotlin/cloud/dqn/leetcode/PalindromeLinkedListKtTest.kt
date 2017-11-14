package cloud.dqn.leetcode

import org.junit.Test

class PalindromeLinkedListKtTest {
    @Test
    fun twoElementTest() {
        val head = PalindromeLinkedListKt.ListNode(0)
        head.next = PalindromeLinkedListKt.ListNode(0)
        PalindromeLinkedListKt.Solution().isPalindrome(head)
    }
}