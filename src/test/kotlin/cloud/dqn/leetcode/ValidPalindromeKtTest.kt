package cloud.dqn.leetcode

import org.junit.Assert
import org.junit.Test

class ValidPalindromeKtTest {
    @Test
    fun isPalindromeTest() {
        val s = "A man, a plan, a canal: Panama"
        val empty = ""
        val single = "a"
        //             0123456789
        val invalid = "race a car"

        val solution = ValidPalindromeKt.Solution()

        val lowerChars = 'a'..'z'
        val upperChars = 'A'..'Z'
        val digit = '0'..'9'

        val r = ValidPalindromeKt.Solution.VALID_CHAR

        arrayOf(lowerChars, upperChars, digit).forEach {
            it.forEach {
                Assert.assertTrue("No match: $it", r.matches(it.toString()))
            }
        }

        Assert.assertTrue(solution.isPalindrome(s))
        Assert.assertTrue(solution.isPalindrome(empty))
        Assert.assertTrue(solution.isPalindrome(single))
        Assert.assertFalse(solution.isPalindrome(invalid))
    }

}