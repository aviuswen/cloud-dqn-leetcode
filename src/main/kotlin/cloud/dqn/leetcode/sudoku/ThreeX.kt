package cloud.dqn.leetcode.sudoku

class ThreeX {
    var firstRow: Triple
    var secondRow: Triple
    var thirdRow: Triple

    constructor(firstRow: Triple, secondRow: Triple, thirdRow: Triple) {
        this.firstRow = firstRow
        this.secondRow = secondRow
        this.thirdRow = thirdRow
    }

    fun set(row: ROW, triple: Triple) {
        when (row) {
            ROW.TOP ->      firstRow = triple
            ROW.MIDDLE ->   secondRow = triple
            ROW.BOTTOM ->   thirdRow = triple
        }
    }

    /**
     * Note: WILDCARD_INT == 0
     [                              [
        [n0,n1],  IS A SUBSET OF        [n0, 0],
        [n2,n3]                         [n2,n3]
     ]                              ]
     */
    fun isSubset(other: ThreeX): Boolean {
        return firstRow.isSubsetOf(other.firstRow)
                && secondRow.isSubsetOf(other.secondRow)
                && thirdRow.isSubsetOf(other.thirdRow)
    }

    companion object {
        enum class ROW { TOP, MIDDLE, BOTTOM }

    }

    private object ROW_INDEX {
        val first = 0
        val second = 1
        val third = 2
    }
    private object COL_INDEX {
        val first = 0
        val second = 1
        val third = 2
    }
}