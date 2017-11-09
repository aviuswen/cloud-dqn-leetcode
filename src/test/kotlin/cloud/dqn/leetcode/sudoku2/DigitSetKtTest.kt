package cloud.dqn.leetcode.sudoku2

import org.junit.Assert
import org.junit.Test

class DigitSetKtTest {
    @Test
    fun constructorFullTest() {
        var digitSet = DigitSet(setFull = true)
        (1..9).forEach {
            Assert.assertTrue("Missing value $it", digitSet.contains(it))
        }
        Assert.assertTrue(digitSet.getSize() == 9)
        Assert.assertFalse(digitSet.isEmpty())
        Assert.assertNotNull(digitSet.getRandom())

        println("hi")
    }

    @Test
    fun constructorEmptyTest() {
        val emptySet = DigitSet.emptyDigitSet()
        (1..9).forEach {
            Assert.assertTrue("Value found: $it", !emptySet.contains(it))
        }
        Assert.assertTrue(emptySet.getSize() == 0)
        Assert.assertTrue(emptySet.isEmpty())
        Assert.assertNull(emptySet.getRandom())
    }

    @Test
    fun addTest() {

    }

    @Test
    fun removeTest() {

    }
}