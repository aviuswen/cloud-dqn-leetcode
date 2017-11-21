package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/permutations/description/
    Given a collection of distinct numbers, return all possible permutations.

    For example,
    [1,2,3] have the following permutations:
    [
      [1,2,3],
      [1,3,2],
      [2,1,3],
      [2,3,1],
      [3,1,2],
      [3,2,1]
    ]
 */
class PermutationsKt {
    class Solution {
        /*
            [a] = [a]
            [a,b] = [a,b], [b,a]
        */
        fun permute(nums: IntArray): List<List<Int>> {
            val result = ArrayList<List<Int>>()
            // backtrack(result, ArrayList(), nums)
            val possible = HashSet(nums.toSet())
            backAgain(result, ArrayList(), possible, nums.size)
            return result
        }
        private fun backtrack(list: ArrayList<List<Int>>, tempList: ArrayList<Int>, nums: IntArray) {
            if (tempList.size == nums.size) {
                list.add(ArrayList(tempList))
            } else {
                nums.forEach {
                    if (!tempList.contains(it)) {
                        val newTemp = ArrayList(tempList)
                        newTemp.add(it)
                        backtrack(list, newTemp, nums)
                    }
                }
            }
        }

        private fun backAgain(result: ArrayList<List<Int>>, current: ArrayList<Int>, possibles: HashSet<Int>, doneSize: Int) {
            if (current.size == doneSize) {
                result.add(current)
            } else {
                possibles.forEach {
                    val newPossible = HashSet(possibles)
                    newPossible.remove(it)
                    val newCurrent = ArrayList(current)
                    newCurrent.add(it)
                    backAgain(result, newCurrent, newPossible, doneSize)
                }
            }
        }


    }
}