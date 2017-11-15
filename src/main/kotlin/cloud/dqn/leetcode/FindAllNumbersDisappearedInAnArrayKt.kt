package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
    Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array),
    some elements appear twice and others appear once.

    Find all the elements of [1, n] inclusive that do not appear in this array.

    Could you do it without extra space and in O(n) runtime? You may assume
    the returned list does not count as extra space.

    Example:

        Input:
        [4,3,2,7,8,2,3,1]

        Output:
        [5,6]
 */
class FindAllNumbersDisappearedInAnArrayKt {
    class Solution {
        /**
            Move the value of the element to
            nums[value - 1].  On swapping, if new value
            does not match index, continue until value matches

            0 marks that a node is missing

            index = 0
             i
            [5,1,2,3,4]

            [1,2,3,4,5]
        */
        companion object {
            val VALUE_REMOVED = 0
        }
        private fun swapUntilMatchingOrMissing(nums: IntArray, index: Int): Int {
            var valueAtIndex = 0
            var valueFromIndex = 0
            var timesLoopsRuns = 0
            val whatTheIndexShouldBe = index + 1
            while (true) {
                valueAtIndex = nums[index]
                if (valueAtIndex == VALUE_REMOVED || valueAtIndex == whatTheIndexShouldBe) {
                    break
                }
                valueFromIndex = nums[valueAtIndex - 1]
                if (valueFromIndex == valueAtIndex) {
                    nums[index] = 0
                    break
                }
                nums[index] = valueFromIndex
                nums[valueAtIndex - 1] = valueAtIndex
                timesLoopsRuns++
            }
            return timesLoopsRuns
        }

        fun findDisappearedNumbers(nums: IntArray): List<Int> {
            var index = 0
            val timesLoopRuns = ArrayList<Int>()
            while (index < nums.size) {
                timesLoopRuns.add(swapUntilMatchingOrMissing(nums, index))
                index++
            }
            index = 0
            var size = 0
            while (index < nums.size) {
                if (nums[index] == 0) {
                    size++
                }
                index++
            }
            index = 0
            val result = ArrayList<Int>(size)
            while (index < nums.size) {
                if (nums[index] == 0) {
                    result.add(index + 1)
                }
                index++
            }
            var total = 0
            timesLoopRuns.forEach { total += it}
            return result
        }
    }
}