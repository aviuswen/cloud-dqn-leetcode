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
    companion object {
        val bar = "a"
        @JvmStatic fun main(args: Array<String>) {
            val arr: IntArray = intArrayOf(2, 0, 7, 11, 15)
            var index = 0
            val bar = Solution().twoSum(arr, 9)
            println("hi")

        }

        class Solution {
            fun twoSumsDoubleLoop(nums: IntArray, target: Int): IntArray {
                var i = 0
                val res = IntArray(2)
                while (i + 1 < nums.size) {
                    var j = i + 1
                    while (j < nums.size) {
                        if (nums[i] + nums[j] == target) {
                            res[0] = i
                            res[1] = j
                            break
                        }
                        j++
                    }
                    i++
                }
                return res
            }
            // 526ms runtime =(
            fun twoSumsForLoops(nums: IntArray, target: Int): IntArray {
                val differenceToIndex = HashMap<Int, Int>(nums.size * 2)
                nums.forEachIndexed { index, value ->
                    differenceToIndex[target - value] = index
                }
                nums.forEachIndexed { index, value ->
                    differenceToIndex[value]?.let { differenceIndex ->
                        if (differenceIndex != index) {
                            return intArrayOf(index, differenceIndex)
                        }
                    }
                }

                // leetcode compiler will timeout without return intArrau
                // throw Exception("Given parameters outside of specifications")
                return intArrayOf(-1, -1)
            }

            // Attempted optimization for time: 428ms =(
            fun twoSum(nums: IntArray, target: Int): IntArray {
                val differenceToIndex = HashMap<Int, Int>()
                var index = 0
                for (value in nums) {
                    differenceToIndex[target - value] = index++
                }
                index = 0
                for (value in nums) {
                    differenceToIndex[value]?.let {
                        if (it != index) {
                            return intArrayOf(index, it)
                        }
                    }
                    index++
                }
                return intArrayOf(-1,-1)
            }

        }
    }
}