package cloud.dqn.leetcode.sudoku

class Value {
    var actual: Int?
    private var possible: HashSet<Int>

    constructor(actual: Int? = null, possible: HashSet<Int>? = null) {
        this.actual = actual
        this.possible = when {
            actual != null -> EMPTY_SET
            possible == null -> fullSet()
            possible.size == 1 -> {
                this.actual = possible.first()
                EMPTY_SET
            }
            else -> HashSet(possible) // this is a copy; with primative it is a "deep" copy
        }
    }

    constructor(exclude: IntArray) {
        this.actual = null
        this.possible = fullSetFactory( *exclude )
    }

    fun isSolved(): Boolean = (actual != null)

    companion object {

        val EMPTY_SET = HashSet<Int>(0)
        private val SUDOKU_ROW_SIZE = 9
        private val SUDOKU_VALID_RANGE = 1..9

        fun fullSetFactory(vararg exclude: Int): HashSet<Int> {
            val set = fullSet()
            exclude.forEach { set.remove(it) }
            return set
        }

        private fun fullSet(): HashSet<Int> = hashSetOf(1,2,3,4,5,6,7,8,9)

        @Throws(IndexOutOfBoundsException::class)
        fun rowFactory(row: CharArray?): Array<Value>? {
            return if (row == null || row.size != SUDOKU_ROW_SIZE) {
                null
            } else {
                val possible = fullSet()
                Array(SUDOKU_ROW_SIZE, init = { index ->
                    val element = row[index].toInt()
                    possible.remove(element)
                    if (SUDOKU_VALID_RANGE.contains(element)) {
                        Value(element)
                    } else {
                        Value(null, possible)
                    }
                })
            }
        }
    }
}