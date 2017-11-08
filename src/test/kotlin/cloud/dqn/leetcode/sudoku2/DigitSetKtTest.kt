package cloud.dqn.leetcode.sudoku2

import org.junit.Assert
import org.junit.Test

class DigitSetKtTest {

    @Test
    fun constructorTest() {
        var d0 = DigitSet()
        Assert.assertTrue(d0.getSize() == 9)


    }
}