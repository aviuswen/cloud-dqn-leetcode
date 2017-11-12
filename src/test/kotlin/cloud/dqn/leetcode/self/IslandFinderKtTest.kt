package cloud.dqn.leetcode.self

import org.junit.Assert
import org.junit.Test

class IslandFinderKtTest {
    companion object {
        val i0 = IslandFinder(arrayOf(
                intArrayOf(0,0,0,0,0,0,0,0,0),
                intArrayOf(0,0,0,0,0,0,0,0,0),
                intArrayOf(0,0,0,0,0,0,0,0,0),
                intArrayOf(0,0,0,0,0,0,0,0,0),
                intArrayOf(0,0,0,0,0,0,0,0,0),
                intArrayOf(0,0,0,0,0,0,0,0,0),
                intArrayOf(0,0,0,0,0,0,0,0,0)
        ))
        val i1 = IslandFinder(arrayOf(
                intArrayOf(0,0,0,0),
                intArrayOf(0,1,0,1),
                intArrayOf(0,0,0,0)
        ))

        val i2 =    IslandFinder(
                        map = arrayOf(
                            intArrayOf(0,0,0,0,0),
                            intArrayOf(0,2,0,1,0),
                            intArrayOf(0,0,0,0,0)
                        )
                    )
    }

    @Test
    fun mapToStringTest() {
        println("i0:\n${i0.mapToString()}")
        println("i1:\n${i1.mapToString()}")
    }

    @Test
    fun findAllIslandsTest() {
        val a0 = i0.findAllIslands()
        Assert.assertTrue(a0.pairs.isEmpty())

        val a1 = i1.findAllIslands()
        Assert.assertTrue(a1.pairs.isNotEmpty())
        Assert.assertTrue(a1.pairs.contains(IslandFinder.PointValue(1,1, 1)))
        Assert.assertFalse(a1.pairs.contains(IslandFinder.PointValue(1,3, 1)))

        i1.countPeninsulas = true
        val a1_noPeninsulas = i1.findAllIslands()
        Assert.assertTrue(a1_noPeninsulas.pairs.isNotEmpty())
        Assert.assertTrue(a1_noPeninsulas.pairs.contains(IslandFinder.PointValue(1,1, 1)))
        Assert.assertTrue(a1_noPeninsulas.pairs.contains(IslandFinder.PointValue(1,3, 1)))

        val validValue = 2
        i2.islandValueIsValid = { it == validValue }
        val a2 = i2.findAllIslands()
        Assert.assertTrue(a2.pairs.size == 1)
        Assert.assertTrue(a2.pairs.contains(IslandFinder.PointValue(1, 1, validValue)))

    }
}