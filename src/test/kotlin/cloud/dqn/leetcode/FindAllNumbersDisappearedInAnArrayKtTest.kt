package cloud.dqn.leetcode

import org.junit.Assert
import org.junit.Test
import java.util.*

class FindAllNumbersDisappearedInAnArrayKtTest {
    @Test
    fun emptyArray() {
        val s = FindAllNumbersDisappearedInAnArrayKt.Solution()
        Assert.assertTrue(s.findDisappearedNumbers(IntArray(0)).isEmpty())
    }

    @Test
    fun leetExample() {
        val s = FindAllNumbersDisappearedInAnArrayKt.Solution()
        val missing = s.findDisappearedNumbers(intArrayOf(4,3,2,7,8,2,3,1))
        Assert.assertTrue(missing.isNotEmpty())
        Assert.assertTrue(missing[0] == 5)
        Assert.assertTrue(missing[1] == 6)
    }

    @Test
    fun specialTests() {
        val s = FindAllNumbersDisappearedInAnArrayKt.Solution()
        val allThere = s.findDisappearedNumbers(intArrayOf(1,2,3,4,5))
        val allThereOutOfOrder = s.findDisappearedNumbers(intArrayOf(1,3,2,5,4))
        Assert.assertTrue(allThere.isEmpty())
        Assert.assertTrue(allThereOutOfOrder.isEmpty())
    }

    @Test
    fun timer() {
        val s = FindAllNumbersDisappearedInAnArrayKt.Solution()
        val rand = Random()
        val size = Int.MAX_VALUE shr 8
        val arr = ArrayList<Int>(size + 8)
        for (i in 0 until size) {
            arr.add(rand.nextInt(size) + 1)
        }
        ArrayList<Int>().clear()
        s.findDisappearedNumbers(arr.toIntArray())

    }

}