package cloud.dqn.leetcode.sudoku2

import java.util.*

class Board {

    val grids: Array<Array<DigitValue>>

    /**
     * By default create an empty board
     */
    private constructor() {
        grids = Array(
            size = BOARD_SIZE,
            init = { DigitValue.rowFactory(BOARD_SIZE) }
        )
    }

    constructor(leetcodeBoard: Array<CharArray>): this() {
        leetcodeBoard.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, char ->
                CHAR_TO_VALID_INT[char]?.let { num ->
                    grids[rowIndex][colIndex].setSolved(num)
                }
            }
        }
        reduceAllPossibleFromSolved()
    }

    constructor(board: Board) {
        grids = Array(
            size = BOARD_SIZE,
            init = { index ->
                DigitValue.rowFactory(board.grids[index])
            }
        )
        reduceAllPossibleFromSolved()
    }

    fun allDone(): Boolean {
        var done = true
        BOARD_INDEX_RANGE.forEach { row ->
            BOARD_INDEX_RANGE.forEach { col ->
                if (grids[row][col].isNotSolved()) {
                    done = false
                }
            }
        }
        return done
    }

    /********************************************************
     * SOLVERS
     ********************************************************/
    fun solve(targetCopy: Array<CharArray>): Boolean {
        var solvedOne = false
        do {
            solvedOne = solveAll()
        } while(solvedOne)

        // copy over to targetCopy
        BOARD_INDEX_RANGE.forEach { row ->
            BOARD_INDEX_RANGE.forEach { col ->
                val num = grids[row][col].getSolved() ?: -1
                targetCopy[row][col] = INT_TO_CHAR[num] ?: DigitValue.WILDCARD_CHAR
            }
        }

        return allDone() && (findInvalid() == null)
    }

    /**
     * Iterates through all values once and tries to solve it
     * @return true if one value was solved
     */
    private fun solveAll(): Boolean {
        var solvedOne = false
        BOARD_INDEX_RANGE.forEach { row ->
            BOARD_INDEX_RANGE.forEach { col ->
                val foundOne = solver_boxAndLine(row, col)
                if (foundOne) {
                    findInvalid()?.let {
                        // println("invalid")
                    }
                }
                solvedOne = solvedOne || foundOne

            }
        }
        return solvedOne
    }

    /**
     * for that location, and for each possibility:
     *      if it cannot be anywhere else in a 3x3 box ||
     *          it cannot be in any other row or column then
     *          that location can only be that value from the possibility
     * @return true if solved
     */
    private fun solver_boxAndLine(row: Int, col: Int): Boolean {
        val digitValue = grids[row][col]
        if (digitValue.isNotSolved()) {
            arrayOf(
                    possibleCollectForContaining3x3Box(row, col),
                    possibleCollectForColumn(row, col),
                    possibleCollectForRow(row, col)
            ).forEach { possibleSet ->
                digitValue.forEach {
                    if (!possibleSet.contains(it)) {
                        digitValue.setSolved(it)
                        reduceAllPossibleFromSolved()
                        return true
                    }
                }
            }
        }
        return false
    }

    /**
     * Iterates through all non solved cells and tries
     * a possible value for a given cell and sees if it is
     * solvable.
     */
    fun guessOneAndSeeIfItWorks(targetCopy: Array<CharArray>): Board? {
        BOARD_INDEX_RANGE.forEach { row ->
            BOARD_INDEX_RANGE.forEach { col ->
                val digitValue = grids[row][col]
                if (digitValue.isNotSolved()) {
                    digitValue.forEach {
                        val testBoard = Board(this)
                        testBoard.grids[row][col].setSolved(it)
                        val solvable = testBoard.solve(targetCopy)
                        if (solvable) {
                            return testBoard
                        }
                    }
                }
            }
        }
        return null
    }

    /********************************************************
     * REDUCTION OF POSSIBLES BASED UPON SOLVED ONLY
     ********************************************************/
    private fun reduceAllPossibleFromSolved() {
        var newSolved = false
        BOARD_INDEX_RANGE.forEach { rowIndex ->
            BOARD_INDEX_RANGE.forEach { colIndex ->
                val digitValue = grids[rowIndex][colIndex]
                if (digitValue.isNotSolved()) {
                    digitValue.removeAllPossible(solvedCollectAll(rowIndex, colIndex))
                    // you just solved something, you need to update the ones
                    // you just updated
                    newSolved = newSolved || digitValue.isSolved()
                }
            }
        }
        if (newSolved) {
            reduceAllPossibleFromSolved()
        }
    }

    private fun solvedCollectAll(row: Int, col: Int): HashSet<Int> {
        val s = solvedCollectForContaining3x3Box(row, col)
        s.addAll(solvedCollectForColumn(col))
        s.addAll(solvedCollectedForRow(row))
        return s
    }

    private fun solvedCollectForContaining3x3Box(row: Int, col: Int): HashSet<Int> {
        // (0,1,2) -> 0
        // (3,4,5) -> 3
        // (6,7,8) -> 6
        val rowOrigin = (row / 3) * 3
        val colOrigin = (col / 3) * 3
        val solutions = HashSet<Int>()
        THREE_TIMES.forEach { rowOffset ->
            THREE_TIMES.forEach { colOffset ->
                val value = grids[rowOrigin + rowOffset][colOrigin + colOffset]
                value.getSolved()?.let {
                    solutions.add(it)
                }
            }
        }
        return solutions
    }

    private fun solvedCollectForColumn(col: Int): HashSet<Int> {
        val solutions = HashSet<Int>()
        BOARD_INDEX_RANGE.forEach { rowIndex ->
            grids[rowIndex][col].getSolved()?.let {
                solutions.add(it)
            }
        }
        return solutions
    }

    private fun solvedCollectedForRow(row: Int): HashSet<Int> {
        val solutions = HashSet<Int>()
        BOARD_INDEX_RANGE.forEach { colIndex ->
            grids[row][colIndex].getSolved()?.let {
                solutions.add(it)
            }
        }
        return solutions
    }

    /********************************************************
     * POSSIBLES COLLECTORS
     ********************************************************/
    private fun possibleCollectForContaining3x3Box(rowExclude: Int, colExclude: Int): HashSet<Int> {
        val rowOrigin = (rowExclude / 3) * 3
        val colOrigin = (colExclude / 3) * 3
        val possibles = HashSet<Int>()
        THREE_TIMES.forEach { rowOffset ->
            THREE_TIMES.forEach { colOffset ->
                val rowActual = rowOrigin + rowOffset
                val colActual = colOrigin + colOffset
                if (rowActual != rowExclude || colActual != colExclude) {
                    val digitValue = grids[rowActual][colActual]
                    if (digitValue.isNotSolved()) {
                        possibles.addAll(digitValue)
                    }
                }
            }
        }
        return possibles
    }

    // colExclude stays constant
    private fun possibleCollectForColumn(rowExclude: Int, colExclude: Int): HashSet<Int> {
        val possibles = HashSet<Int>()
        BOARD_INDEX_RANGE.forEach { rowIndex ->
            if (rowIndex != rowExclude) {
                val digitValue = grids[rowIndex][colExclude]
                if (digitValue.isNotSolved()) {
                    possibles.addAll(digitValue)
                }
            }
        }
        return possibles
    }

    // rowExclude stays constant
    private fun possibleCollectForRow(rowExclude: Int, colExclude: Int): HashSet<Int> {
        val possibles = HashSet<Int>()
        BOARD_INDEX_RANGE.forEach { colIndex ->
            if (colIndex != colExclude) {
                val digitValue = grids[rowExclude][colIndex]
                if (digitValue.isNotSolved()) {
                    possibles.addAll(digitValue)
                }
            }
        }
        return possibles
    }

    /********************************************************
     * FIND INVALIDATIONS
     ********************************************************/
    fun findInvalid(): Pair<Int, Int>? {
        BOARD_INDEX_RANGE.forEach { index ->
            (findInvalidPairByCol(index) ?: findInvalidPairByRow(index))?.let {
                return it
            }
        }
        return findInvalidBy3x3Box() ?: findByEmptyPossibles()
    }

    private fun findInvalidBy3x3Box(): Pair<Int, Int>? {
        THREE_TIMES.forEach { rowFactor ->
            THREE_TIMES.forEach { colFactor ->
                val allValues = HashSet<Int>()
                THREE_TIMES.forEach{ rowOffSet ->
                    THREE_TIMES.forEach { colOffset ->
                        val rowActual = rowFactor * 3 + rowOffSet
                        val colActual = colFactor * 3 + colOffset
                        val digitValue = grids[rowActual][colActual]
                        digitValue.getSolved()?.let {
                            if (allValues.contains(it)) {
                                return (rowActual to colActual)
                            } else {
                                allValues.add(it)
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    private fun findInvalidPairByCol(col: Int): Pair<Int, Int>? {
        val allValues = HashSet<Int>()
        BOARD_INDEX_RANGE.forEach { row ->
            grids[row][col].getSolved()?.let {
                if (allValues.contains(it)) {
                    return (row to col)
                } else {
                    allValues.add(it)
                }
            }

        }
        return null
    }

    private fun findInvalidPairByRow(row: Int): Pair<Int, Int>? {
        val allValues = HashSet<Int>()
        val workingRow = grids[row]
        workingRow.forEachIndexed { col, value ->
            value.getSolved()?.let {
                if (allValues.contains(it)) {
                    return (row to col)
                } else {
                    allValues.add(it)
                }
            }
        }
        return null
    }

    private fun findByEmptyPossibles(): Pair<Int, Int>? {
        BOARD_INDEX_RANGE.forEach { row ->
            BOARD_INDEX_RANGE.forEach { col ->
                val digitValue = grids[row][col]
                if (digitValue.isNotValid()) {
                    return (row to col)
                }
            }
        }
        return null
    }


    /********************************************************
     * COMPANION OBJECTS
     ********************************************************/
    companion object {
        val BOARD_SIZE = 9
        private val CHAR_TO_VALID_INT = hashMapOf(
                '1' to 1, '2' to 2, '3' to 3,
                '4' to 4, '5' to 5, '6' to 6,
                '7' to 7, '8' to 8, '9' to 9
        )
        private val INT_TO_CHAR = hashMapOf(
                1 to '1', 2 to '2', 3 to '3',
                4 to '4', 5 to '5', 6 to '6',
                7 to '7', 8 to '8', 9 to '9'
        )
        private val DEFAULT_STRING_BUILDER_CAPACITY = 256
        private val THIRD_TIME = 2
        private val THREE_TIMES = 0..THIRD_TIME
        val BOARD_INDEX_RANGE = 0..(BOARD_SIZE - 1)

        private val HORIZONTAL_DIVIDER = "---------------------\n"
    }

    /********************************************************
     * TO STRING
     ********************************************************/

    override fun toString(): String {
        val s = StringBuilder(DEFAULT_STRING_BUILDER_CAPACITY)
        BOARD_INDEX_RANGE.forEach {
            appendRow(s, it)
            if (it == 2 || it == 5) {
                s.append(HORIZONTAL_DIVIDER)
            }
        }
        return s.toString()
    }

    private fun appendRow(s: StringBuilder, startRow: Int) {
        val row: Array<DigitValue> = grids[startRow]
        THREE_TIMES.forEach {
            append3GridValues(s, row, it * 3)
            if (it != THIRD_TIME) {
                s.append(" | ")
            }
        }
        s.append("\n")
    }

    private fun append3GridValues(s: StringBuilder, row: Array<DigitValue>, startCol: Int) {
        s.append("${row[startCol]} ${row[startCol + 1]} ${row[startCol + 2]}")
    }

    class DigitValue: Iterable<Int> {
        private var solved: Int
        private val possible: DigitSet

        constructor() {
            solved = UNSOLVED_VALUE
            possible = DigitSet()
        }

        constructor(digitValue: DigitValue) {
            solved = digitValue.solved
            possible = DigitSet(digitValue.possible)
        }

        fun isNotValid(): Boolean = (possible.isEmpty() && isNotSolved())

        fun setSolved(num: Int) { solved = num }

        fun getSolved(): Int? = if (isSolved()) solved else null

        fun isSolved(): Boolean = (solved != UNSOLVED_VALUE)

        fun isNotSolved(): Boolean = !isSolved()

        fun removeAllPossible(iterable: Iterable<Int>) {
            possible.removeAll(iterable)
            if (possible.getSize() == 1 && isNotSolved()) {
                solved = possible.getFirst()
            }
        }

        override fun iterator(): Iterator<Int> {
            return possible.iterator()
        }

        override fun toString(): String = getSolved()?.toString() ?: WILDCARD_STR

        companion object {
            val UNSOLVED_VALUE = 0
            val WILDCARD_CHAR = '.'
            private val WILDCARD_STR = WILDCARD_CHAR.toString()

            fun rowFactory(columnCount: Int): Array<DigitValue> {
                return Array(
                        size = columnCount,
                        init = { DigitValue() }
                )
            }

            fun rowFactory(values: Array<DigitValue>): Array<DigitValue> {
                return Array(
                        size = values.size,
                        init = { DigitValue(values[it]) }
                )
            }
        }
    }
}