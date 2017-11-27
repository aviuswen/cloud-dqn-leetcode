package cloud.dqn.leetcode

import org.junit.Assert
import org.junit.Test

class TrappingRainWater2KtTest {
    @Test fun leetcode() {
        val heightMap = arrayOf(
                intArrayOf(1,4,3,1,3,2),
                intArrayOf(3,2,1,3,2,4),
                intArrayOf(2,3,3,2,3,1)
        )
        val s = TrappingRainWater2Kt.Solution()
        Assert.assertTrue(s.trapRainWater(heightMap) == 4)
    }



}