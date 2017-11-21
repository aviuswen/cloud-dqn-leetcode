package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/permutations-ii/description/
    Given a collection of numbers that might contain duplicates, return all possible unique permutations.

    For example,
    [1,1,2] have the following unique permutations:
    [
      [1,1,2],
      [1,2,1],
      [2,1,1]
    ]
 */
class Permutations2Kt {
    class Solution {
        fun permuteUnique(nums: IntArray): List<List<Int>> {
            val result = ArrayList<List<Int>>()
            val remainder = ArrayList<Int>(nums.size)
            nums.forEach { remainder.add(it) }
            backtrack(result, ArrayList(), nums.size, remainder)
            return result
        }

        private fun backtrack(res: ArrayList<List<Int>>, tempList: ArrayList<Int>, doneSize: Int, remainder: ArrayList<Int>) {
            if (tempList.size == doneSize) {
                var addIt = true
                var index = 0
                while (index < res.size) {
                    val current = res[index]
                    if (equalLists(current, tempList)) {
                        addIt = false
                        break
                    }
                    index++
                }
                if (addIt) {
                    res.add(ArrayList(tempList))
                }
            } else {
                remainder.forEachIndexed { index, value ->
                    val newTempList = ArrayList(tempList)
                    newTempList.add(value)
                    val newRemainder = ArrayList<Int>(remainder.size - 1)
                    remainder.forEachIndexed { i, v ->
                        if (i != index)  {
                            newRemainder.add(v)
                        }
                    }
                    backtrack(res, newTempList, doneSize, newRemainder)
                }
            }
        }

        private fun equalLists(l0: List<Int>, l1: List<Int>): Boolean {
            if (l0.size != l1.size) {
                return false
            }

            var index = 0
            while (index < l0.size) {
                if (l0[index] != l1[index]) {
                    return false
                }
                index++
            }
            return true
        }


    }
}