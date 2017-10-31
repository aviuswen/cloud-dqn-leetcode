package cloud.dqn.leetcode

/**
 *
 * Kotlin implementation
 * Problem: https://leetcode.com/problems/two-sum/description/
    Given an array of integers, return indices of the two
    numbers such that they add up to a specific target.

    You may assume that each input would have exactly one solution,
    and you may not use the same element twice.

    Example:
    Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
 */

class TwoSumKt {
    class Solution {
        fun twoSum(nums: Array<Int>, target: Int): Array<Int>? {
            var i = 0
            while (i + 1 < nums.size) {
                var j = i + 1
                while (j < nums.size) {
                    if (nums[i] + nums[j] == target) {
                        return arrayOf(i, j)
                    }
                    j++
                }
                i++
            }
            return null
        }
    }
}