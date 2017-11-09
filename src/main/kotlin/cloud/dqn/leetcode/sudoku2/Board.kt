package cloud.dqn.leetcode.sudoku2

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
            collectAllPossibles(row, col).forEach { possibleSet ->
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
                    digitValue.removeAllPossible(collectAllSolved(rowIndex, colIndex))
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

    private fun collectAllSolved(row: Int, col: Int): DigitSet {
        val solutions = DigitSet.emptyDigitSet()

        // collect solved for containing 3x3 box
        val rowOrigin = (row / 3) * 3
        val colOrigin = (col / 3) * 3
        THREE_TIMES.forEach { rowOffset ->
            THREE_TIMES.forEach { colOffset ->
                val digitValue = grids[rowOrigin + rowOffset][colOrigin + colOffset]
                digitValue.getSolved()?.let { solutions.add(it) }
            }
        }

        BOARD_INDEX_RANGE.forEach {
            // collect solved by Column
            grids[it][col].getSolved()?.let {
                solutions.add(it)
            }

            // collect solved by Row
            grids[row][it].getSolved()?.let {
                solutions.add(it)
            }
        }

        return solutions
    }

    /********************************************************
     * POSSIBLES COLLECTORS
     ********************************************************/
    private fun collectAllPossibles(rowExclude: Int, colExclude: Int): Array<DigitSet> {
        val possibles = Array(3, {DigitSet.emptyDigitSet()})

        // collect possibles from contain 3x3 box
        val rowOrigin = (rowExclude / 3) * 3
        val colOrigin = (colExclude / 3) * 3
        THREE_TIMES.forEach { rowOffset ->
            THREE_TIMES.forEach { colOffset ->
                val rowActual = rowOrigin + rowOffset
                val colActual = colOrigin + colOffset
                if (rowActual != rowExclude || colActual != colExclude) {
                    val digitValue = grids[rowActual][colActual]
                    if (digitValue.isNotSolved()) {
                        possibles[0].addAll(digitValue)
                    }
                }
            }
        }

        BOARD_INDEX_RANGE.forEach {
            // collect possibles from column
            if (it != rowExclude) {
                val digitValue = grids[it][colExclude]
                if (digitValue.isNotSolved()) {
                    possibles[1].addAll(digitValue)
                }
            }

            // collect possibles from row
            if (it != colExclude) {
                val digitValue = grids[rowExclude][it]
                if (digitValue.isNotSolved()) {
                    possibles[2].addAll(digitValue)
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
        THREE_TIMES.forEach { rowFactor -> THREE_TIMES.forEach { colFactor ->
            val allValues = DigitSet.emptyDigitSet()
            THREE_TIMES.forEach{ rowOffSet -> THREE_TIMES.forEach { colOffset ->
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
            } }
        } }
        return null
    }

    private fun findInvalidPairByCol(col: Int): Pair<Int, Int>? {
        val allValues = DigitSet.emptyDigitSet()
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
        val allValues = DigitSet.emptyDigitSet()
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

    /********************************************************
     *
     *
     *                  NESTED SUB CLASSES
     *
     *
     ********************************************************/

    class DigitSet: Iterable<Int> {

        private var internalSet: Int
        private var size: Int

        constructor(setFull: Boolean = true) {
            if (setFull) {
                size = MAX_SIZE
                internalSet = NINE_ONES
            } else {
                size = 0
                internalSet = EMPTY_SET
            }
        }

        constructor(digitSet: DigitSet) {
            this.internalSet = digitSet.internalSet
            this.size = digitSet.size
        }

        fun getSize() = size

        fun isEmpty() = size == 0

        fun getFirst(): Int {
            return getRandom() ?: -1
        }

        fun getRandom(): Int? {
            this.forEach { return it }
            return null
        }

        fun add(value: Int): Boolean {
            var valueWasMissing = false
            if (validDigit(value)) {
                val internalValue = convertToInternal(value)
                valueWasMissing = !containsInternal(internalValue)
                if (valueWasMissing) {
                    size++
                }
                internalSet = internalSet or internalValue
            }
            return valueWasMissing
        }

        fun addAll(iterable: Iterable<Int>) = iterable.forEach { add(it) }

        fun remove(value: Int): Boolean {
            var setChanged = false
            if (validDigit(value)) {
                val internalValue = convertToInternal(value)
                setChanged = containsInternal(internalValue)
                if (setChanged) {
                    size--
                }
                internalSet = internalSet and (internalValue.inv())
            }
            return setChanged
        }

        fun removeAll(iterable: Iterable<Int>) = iterable.forEach { remove(it) }

        fun clear() {
            size = 0
            internalSet = EMPTY_SET
        }

        fun contains(value: Int): Boolean = containsInternal(convertToInternal(value))

        private fun containsInternal(internalValue: Int): Boolean {
            return (internalValue and internalSet) > 0
        }

        private fun convertToInternal(value: Int): Int = (1 shl (value - 1))

        override fun iterator(): Iterator<Int> {
            return DigitIterator(internalSet)
        }

        private class DigitIterator: Iterator<Int> {
            val set: Int
            var nextValue: Int

            constructor(set: Int) {
                this.set = set
                nextValue = 1
                walkNextValueToExisting()
            }

            private fun walkNextValueToExisting() {
                while (nextValue <= 9) {
                    if (1.shl(nextValue - 1).and(set) > 0) {
                        break
                    } else {
                        nextValue++
                    }
                }
            }

            override fun hasNext(): Boolean = (nextValue <= MAX_SIZE)

            override fun next(): Int {
                val element = nextValue++
                walkNextValueToExisting()
                return element
            }
        }

        companion object {
            private val ALL_ONES: Int = (-1) ushr 1
            private val NINE_ONES: Int =  511 //(Math.pow(2.0, 9.0) - 1).toInt()
            private val EMPTY_SET: Int = 0
            private val MAX_SIZE = 9

            private fun validDigit(digit: Int): Boolean {
                return digit >= 1 && digit <= MAX_SIZE
            }

            fun emptyDigitSet(): DigitSet {
                return DigitSet(setFull = false)
            }
        }
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