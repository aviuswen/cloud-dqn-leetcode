package cloud.dqn.leetcode

import org.junit.Test

class CombinationSumKtTest {
    @Test
    fun foo() {
        val t = intArrayOf(2, 3, 6, 7)

        val b = CombinationSumKt().combinationSum(t, 7)

        val t1 = intArrayOf(2,3,4,6,7,13)
        val b0 = CombinationSumKt().combinationSum(t1, 26)
        println("")

    }
}