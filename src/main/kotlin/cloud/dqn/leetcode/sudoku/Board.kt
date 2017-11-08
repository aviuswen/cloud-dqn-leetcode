package cloud.dqn.leetcode.sudoku

import java.math.BigInteger

@Deprecated("see sudoku2 package")
class Board {
    val grids: Array<Array<Value>>      // 9x9 values
    val boxes: Array<Array<BoardBox>>   // 3x3 of Boarded boxes(3x3 values)

    /*
        WARNING: you need to check that it is a 9x9 array beforehand!!!!
        Would normally throw or use null values from factories
             but leetcode (I love you leetcode) has been shown to compile
             poorly with Kotlin code when it comes to throwing errors

        3 options on bad data:
         1) Transform bad data into proper form replacing as best you can.
             bad for development,
             as unit testing will not be helpful when doing integration
             testing.  Signaling to caller prior to transformation
             is preferable.
         2) Make assumption that all data is good no matter what.
             Should you ever do this?!?!?!  .... NO!!!!!... unless params
             are checked beforehand
         3) do nothing


         Note from problem: "You may assume that there will be only one unique solution."
         Implies that input parameters are ALWAYS VALID
    */
    @Throws(IndexOutOfBoundsException::class)
    constructor(board: Array<CharArray>) {
        grids = Array(NUM_ROWS, init = { rowIndex ->
            // done without elvis operator to show where to throw in future updates
            // Value.rowFactory(board[rowIndex]) ?: emptyArray()
            val row = Value.rowFactory(board[rowIndex])
            if (row == null) {
                // throw here instead as board[rowIndex] is bad
                emptyArray()
            } else {
                row
            }
        })

        boxes = Array(
            size = 3,
            init = { rowIndex ->
                arrayOf(
                    BoardBox(grids, rowIndex * 3, 0),
                    BoardBox(grids, rowIndex * 3, 3),
                    BoardBox(grids, rowIndex * 3, 6)

                )
            }
        )
    }

    /**
     * Only iterates the boxes once
     * @return true when a new value was found
     */
    fun boardedBoxesReducePossibles(): Boolean {
        var newValueSolved = false
        iterateBoxes {
            newValueSolved = newValueSolved || it.reducePossibles()
        }
        return newValueSolved
    }

    fun lineReduceAll() {
        NINE_TIMES.forEach { row ->
            NINE_TIMES.forEach { col ->
                lineReduce(row, col)
            }
        }
    }

    /**
     * @return true if solution found for value
     */
    private fun lineReduce(row: Int, col: Int): Boolean {
        val value = grids[row][col]
        if (!value.isSolved()) {
            val impossibleVertical = HashSet<Int>()
            // col stays constant
            NINE_TIMES.forEach {
                if (it != row) {
                    grids[it][col].actual?.let {
                        impossibleVertical.add(it)
                    }
                }
            }
            val impossibleHorizontal = HashSet<Int>()
            // row stays constant
            NINE_TIMES.forEach {
                if (it != col) {
                    grids[row][it].actual?.let {
                        impossibleHorizontal.add(it)
                    }
                }
            }
            println("hi")
            value.possible.removeAll(impossibleVertical)
            value.possible.removeAll(impossibleHorizontal)
            return value.isSolved()
        } else {
            return false
        }
    }

    fun boxSolverAll() {
        NINE_TIMES.forEach { row ->
            NINE_TIMES.forEach { col ->
                possibleBoxSolver(row, col)
            }
        }
    }

    /**
     * @return true if solved
     */
    private fun possibleBoxSolver(row: Int, col: Int): Boolean {
        val value = grids[row][col]
        if (!value.isSolved()) {
            val box = getContainingBox(row, col)
            value.possible.forEach {
                if (!box.canNumExistAnywhereElse(it, row, col)) {
                    value.actual = it
                    value.possible = hashSetOf(it)
                    box.solved.add(it)
                    box.reducePossibles()
                    return true
                }
            }
        }
        return false
    }

    private fun getContainingBox(row: Int, col: Int): BoardBox {
        return boxes[row / 3][col / 3]
    }

    fun calculateBruteForceIterations(): BigInteger {
        var iterations = BigInteger.valueOf(1)
        iterateBoxes {
            val littleIt = BigInteger.valueOf(it.calculateBruteForceIterations().toLong())
            val multValue =  iterations.multiply(littleIt)
            iterations = multValue
        }
        return iterations
    }

    inline fun iterateBoxes(body: (BoardBox) -> Unit) {
        THREE_TIMES.forEach { row ->
            THREE_TIMES.forEach { col ->
                body(boxes[row][col])
            }
        }
    }

    inline fun iterateGrids(body: (Value) -> Unit) {
        NINE_TIMES.forEach { row ->
            NINE_TIMES.forEach { col ->
                body(grids[row][col])
            }
        }
    }

    /** Format
     *
        5 3 1 | 3 3 3 | 3 3 3
        5 3 1 | 3 3 3 | 3 3 3
        5 3 1 | 3 3 3 | 3 3 3
        ---------------------
        5 3 1 | 3 3 3 | 3 3 3
        5 3 1 | 3 3 3 | 3 3 3
        5 3 1 | 3 3 3 | 3 3 3
        ---------------------
        5 3 1 | 3 3 3 | 3 3 3
        5 3 1 | 3 3 3 | 3 3 3
        5 3 1 | 3 3 3 | 3 3 3

        Note: each row is 22 chars (w/ newline) in length with 11 total ==> 242 char
     */
    override fun toString(): String {
        val s = StringBuilder(DEFAULT_STRING_BUILDER_CAPACITY)
        (0..8).forEach {
            appendRow(s, it)
            if (it == 2 || it == 5) {
                s.append(HORIZONTAL_DIVIDER)
            }
        }
        return s.toString()
    }

    private fun appendRow(s: StringBuilder, startRow: Int) {
        val row: Array<Value> = grids[startRow]
        THREE_TIMES.forEach {
            append3GridValues(s, row, it * 3)
            if (it != LAST_TIME) {
                s.append(" | ")
            }
        }
        s.append("\n")
    }

    private fun append3GridValues(s: StringBuilder, row: Array<Value>, startCol: Int) {
        s.append("${row[startCol]} ${row[startCol + 1]} ${row[startCol + 2]}")
    }

    companion object {
        val NUM_ROWS = 9
        val NUM_COL = 9
        val CRAP_GRID: Array<CharArray> = Array(0, {charArrayOf()})
        private val DEFAULT_STRING_BUILDER_CAPACITY = 256
        private val BOARDED_BOXES_MAX_REDUCE = 81
        private val LAST_TIME = 2
        val THREE_TIMES = 0..LAST_TIME
        val NINE_TIMES = 0..8
        private val HORIZONTAL_DIVIDER = "---------------------\n"
        fun genEmptyBoard(): Array<CharArray> {
            return arrayOf(
                    charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'),
                    charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'),
                    charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'),

                    charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'),
                    charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'),
                    charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'),

                    charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'),
                    charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'),
                    charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.')
            )
        }
    }

}