package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/combination-sum/description/
    Given a set of candidate numbers (C) (without duplicates) and a
    target number (T), find all unique combinations in C where the
    candidate numbers sums to T.

    The same repeated number may be chosen from C unlimited number of times.

    Note:
        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        For example, given candidate set [2, 3, 6, 7] and target 7,
        A solution set is:
            [
                [7],
                [2, 2, 3]
            ]
 */
class CombinationSumKt {
    class Factor(val multiple: Int, val value: Int) {
        fun total(): Int = multiple * value

        companion object {
            fun factory(value: Int, maxValueInclusive: Int): ArrayList<Factor> {
                val res = ArrayList<Factor>()
                (1..(maxValueInclusive / value)).forEach {
                    res.add(Factor(it, value))
                }
                return res
            }
        }

        fun toArray(): IntArray? {
            if (multiple > 0) { return IntArray(multiple, { value }) }
            return null
        }
    }

    class FactorCollection {
        val list: ArrayList<Factor> = ArrayList()
        var total: Int = 0
        fun add(factor: Factor, maxValueInclusive: Int) {
            if (factor.total() + total <= maxValueInclusive && factor.multiple != 0) {
                list.add(factor)
                total += factor.total()
            }
        }
        fun toList(): ArrayList<Int> {
            val res = ArrayList<Int>()
            list.forEach {
                it.toArray()?.let { it.forEach {
                    res.add(it)
                } }
            }
            return res
        }
    }

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val listFactorCollection = ArrayList<FactorCollection>()
        candidates.forEach { factor ->
            val subFactor = Factor.factory(factor, target)
            val appendList = ArrayList<FactorCollection>()
            listFactorCollection.forEach { factorCollection ->
                subFactor.forEach {
                    if (factorCollection.total + it.total() <= target) {
                        val newFactorCollection = FactorCollection()
                        newFactorCollection.add(it, target)
                        factorCollection.list.forEach { newFactorCollection.add(it, target) }
                        appendList.add(newFactorCollection)
                    }
                }
            }
            appendList.forEach { listFactorCollection.add(it) }
            subFactor.forEach {
                val factorCollection = FactorCollection()
                factorCollection.add(it, target)
                listFactorCollection.add(factorCollection)
            }
        }
        val result = ArrayList<ArrayList<Int>>()
        listFactorCollection.forEach { factorCollection ->
            if (factorCollection.total == target) {
                result.add(
                        factorCollection.toList()
                )
            }
        }
        return result
    }
}