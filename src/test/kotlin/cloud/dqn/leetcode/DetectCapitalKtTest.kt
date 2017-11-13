package cloud.dqn.leetcode

import org.junit.Assert
import org.junit.Test

class DetectCapitalKtTest {
    @Test
    fun regexTesting() {
        Assert.assertTrue(
                DetectCapitalKt.Solution.allUpperCaseRegex.matches("ABSC")
        )
        Assert.assertFalse(
                DetectCapitalKt.Solution.allUpperCaseRegex.matches("abc")
        )
        Assert.assertTrue(
                DetectCapitalKt.Solution.allLowerCaseRegex.matches("abc")
        )
        Assert.assertFalse(
                DetectCapitalKt.Solution.allLowerCaseRegex.matches("ABC")
        )

    }
}