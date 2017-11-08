package cloud.dqn.leetcode.sudoku


/**
 * This is a sub 3x3 grid inside of a Board
 */
@Deprecated("see sudoku2 package")
class BoardBox {
    val row: Int
    val col: Int
    val grids: Array<Array<Value>>
    val solved: HashSet<Int>

    constructor(grids: Array<Array<Value>>, row: Int, col: Int) {
        this.grids = grids
        this.row = row
        this.col = col
        this.solved = HashSet()
        populateSolved()
    }

    private fun populateSolved() {
        iterate {
            if (it.isSolved()) { it.actual?.let { solved.add(it) } }
        }
    }

    fun isSolved(): Boolean {
        var allValuesSolved = true
        iterate { value ->
            if (!value.isSolved()) {
                allValuesSolved = false
            }
        }
        return allValuesSolved
    }

    fun calculateBruteForceIterations(): Int {
        var iterations = 1
        iterate {
            if (!it.isSolved()) {
                iterations *= it.possible.size
            }
        }
        return iterations
    }

    /**
     * Iterates up to a fixed amount reducing the possibles.
     * Only iterates a second (or more) time when a new
     * value is found.
     * @return true when a new value was found
     */
    fun reducePossibles(): Boolean {
        val maxIterations = 9 - solved.size
        var iterations = 0
        var newValueSolved = false
        while (iterations < maxIterations) {
            newValueSolved = updateValuesPossible()
            if (!newValueSolved) {
                break
            }
            iterations++
        }
        return newValueSolved
    }
    /**
     * Simple algorithm to update the possibles
     * for each value in the box by collecting all the solved values
     * and redistributing them to the values in the box
     * @return true when a new value was found
     */
    private fun updateValuesPossible(): Boolean {
        var found = false
        iterate {
            it.actual?.let {solved.add(it)}
        }
        iterate {
            if (!it.isSolved()) {
                it.removePossible(solved)
                if (it.isSolved()) {
                    found = true
                    it.actual?.let {solved.add(it)}
                }
            }
        }
        return found
    }

    inline fun iterate(body: (Value) -> Unit) {
        THREE_TIME.forEach { rowOffset ->
            THREE_TIME.forEach { columnOffset ->
                body( grids[row + rowOffset][col + columnOffset] )
            }
        }
    }

    inline fun iterate(body: (Value) -> Unit, rowExclude: Int, colExclude: Int) {
        THREE_TIME.forEach { rowOffset ->
            THREE_TIME.forEach { columnOffset ->
                val actualRow = row + rowOffset
                val actualCol = col + columnOffset
                if (actualRow != rowExclude || actualCol != colExclude) {
                    body(grids[row + rowOffset][col + columnOffset])
                }
            }
        }
    }

    fun canNumExistAnywhereElse(num: Int, rowExclude: Int, colExclude: Int): Boolean {

        iterate(
            rowExclude = rowExclude,
            colExclude = colExclude,
            body = {
                if (!it.isSolved()) {
                    if (it.possible.contains(num)) {
                        return true
                    }
                }
            }
        )
        return false
    }

    companion object {
        val THREE_TIME = 0..2


        fun factorial(n: Int): Int {
            return if (n <= 1) {
                1
            } else {
                var fact = 1
                (1..n).forEach { fact *= it }
                fact
            }
        }
    }
}