package cloud.dqn.leetcode

import org.junit.Assert
import org.junit.Test

class PalindromeNumberKtTest {
    @Test
    fun testSolution() {
        val p0 = 2147483647
        val p1 = 9876789
        val neg0 = -101
        val neg1 = -123456767
        val single = 3
        Assert.assertFalse(PalindromeNumberKt.Solution().isPalindrome(p0))
        Assert.assertTrue(PalindromeNumberKt.Solution().isPalindrome(p1))
        Assert.assertFalse(PalindromeNumberKt.Solution().isPalindrome(neg0))
        Assert.assertFalse(PalindromeNumberKt.Solution().isPalindrome(neg1))
        Assert.assertTrue(PalindromeNumberKt.Solution().isPalindrome(single))
    }
}