package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/subsets/description/
    Given a set of distinct integers, nums, return all possible
    subsets (the power set).

    Note: The solution set must not contain duplicate subsets.

    For example,
    If nums = [1,2,3], a solution is:

    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]
 */
class SubsetsKt {
    class Solution {
        fun backtrack(results: ArrayList<List<Int>>, tempList: ArrayList<Int>, possible: ArrayList<Int>) {
            results.add(tempList)
            possible.forEachIndexed { index, value ->
                val copyTempList = ArrayList(tempList)
                copyTempList.add(value)

                val copyPossible = ArrayList<Int>()
                possible.forEachIndexed { i, v ->
                    if (i > index) {
                        copyPossible.add(v)
                    }
                }

                backtrack(results, copyTempList, copyPossible)
            }
        }
        fun subsets(nums: IntArray): List<List<Int>> {
            nums.sort()
            val results = ArrayList<List<Int>>()
            val possible = ArrayList<Int>()
            nums.forEach { possible.add(it) }
            backtrack(results, ArrayList(), possible)
            return results
        }
    }
}