package cloud.dqn.leetcode.sudoku

class Board {
    val grids: Array<Array<Value>>



    // WARNING: you need to check that it is a 9x9 array beforehand!!!!
    // Would normally throw or use null values from factories
    //      but leetcode (I love you leetcode) has been shown to compile
    //      poorly with Kotlin code when it comes to throwing errors

    // 3 options on bad data:
    //  1) Transform bad data into proper form replacing as best you can.
    //      bad for development,
    //      as unit testing will not be helpful when doing integration
    //      testing.  Signaling to caller prior to transformation
    //      is preferable.
    //  2) Make assumption that all data is good no matter what.
    //      Should you ever do this?!?!?!  .... NO!!!!!... unless params
    //      are checked beforehand
    //  3) do nothing


    //  Note from problem: "You may assume that there will be only one unique solution."
    //  Implies that input parameters are ALWAYS VALID

    @Throws(IndexOutOfBoundsException::class)
    constructor(board: Array<CharArray>) {
        this.grids = Array(NUM_ROWS, init = { rowIndex ->
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
    }

    override fun toString(): String {
        /* Desired String:
        5 3 1 |  3 3 3 | 3 3 3
        5 3 1 |  3 3 3 | 3 3 3
        5 3 1 |  3 3 3 | 3 3 3
        ----------------------
        5 3 1 |  3 3 3 | 3 3 3
        5 3 1 |  3 3 3 | 3 3 3
        5 3 1 |  3 3 3 | 3 3 3
        ----------------------
        5 3 1 |  3 3 3 | 3 3 3
        5 3 1 |  3 3 3 | 3 3 3
        5 3 1 |  3 3 3 | 3 3 3

        Note: each row is 23 chars in length with 11 total ==> 253 char

         */
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
        private val LAST_TIME = 2
        private val THREE_TIMES = 0..LAST_TIME
        private val HORIZONTAL_DIVIDER = "----------------------\n"
        public fun genEmptyBoard(): Array<CharArray> {
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