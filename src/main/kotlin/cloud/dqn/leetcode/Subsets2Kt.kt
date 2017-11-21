package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/subsets-ii/description/
    Given a collection of integers that might contain duplicates,
    nums, return all possible subsets (the power set).

    Note: The solution set must not contain duplicate subsets.

    For example,
    If nums = [1,2,2], a solution is:

    [
      [2],
      [1],
      [1,2,2],
      [2,2],
      [1,2],
      []
    ]
 */
class Subsets2Kt {
    class Solution {
        private fun equalLists(l0: List<Int>, l1: List<Int>): Boolean {
            if (l0.size != l1.size) {
                return false
            }

            l0.forEachIndexed { index, value ->
                if (l1[index] != value) {
                    return false
                }
            }

            return true
        }

        private fun listOfListContainsList(bigList: ArrayList<List<Int>>?, other: List<Int>): Boolean {
            bigList?.forEach {
                if (equalLists(it, other)) {
                    return true
                }
            }
            return false
        }

        fun backtrack(results: HashMap<Int, ArrayList<List<Int>>>, tempList: ArrayList<Int>, possible: ArrayList<Int>) {
            if (results[tempList.size] == null) {
                results[tempList.size] = ArrayList()
                results[tempList.size]?.add(tempList)
            } else if (!listOfListContainsList(results[tempList.size], tempList)) {
                results[tempList.size]?.add(tempList)
            }

            possible.forEachIndexed { index, value ->
                val copyTempList = ArrayList(tempList)
                copyTempList.add(value)

                val copyPossible = ArrayList<Int>(possible.size)
                possible.forEachIndexed { i, v ->
                    if (i > index) {
                        copyPossible.add(v)
                    }
                }

                backtrack(results, copyTempList, copyPossible)
            }
        }
        fun subsetsWithDup(nums: IntArray): List<List<Int>> {
            nums.sort()
            val results = HashMap<Int, ArrayList<List<Int>>>()
            val possible = ArrayList<Int>()
            nums.forEach { possible.add(it) }
            backtrack(results, ArrayList(), possible)
            val res = ArrayList<List<Int>>()
            results.forEach { _, value -> res.addAll(value) }
            return res
        }
    }
}