package cloud.dqn.leetcode

import org.junit.Assert
import org.junit.Test

class TwoSumKtTest {

    @Test
    fun fooTest() {
        Assert.assertTrue(true)
    }

    @Test
    fun exampleTest() {
        val nums = intArrayOf(1, 0, 2, 7, 11, 15)
        val target = 9

        val firstValue = 0
        val secondValue = 1

        val firstIndexSolution = 2
        val secondIndexSolution = 3

        val solution = TwoSumKt.Companion.Solution().twoSums(nums, target)
        Assert.assertTrue(solution.size == 2)

        val solutionFirstValue = solution.getOrNull(firstValue)
        val solutionSecondValue = solution.getOrNull(secondValue)

        Assert.assertNotNull(solutionFirstValue)
        Assert.assertNotNull(solutionSecondValue)
        Assert.assertTrue(solutionFirstValue == firstIndexSolution)
        Assert.assertTrue(solutionSecondValue == secondIndexSolution)
    }
}