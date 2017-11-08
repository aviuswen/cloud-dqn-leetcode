package cloud.dqn.leetcode.sudoku

@Deprecated("see sudoku2 package")
class Value {
    var actual: Int?
    // TODO HashSet is overkill, used smaller spaced data type
    // WARNING:: when reducing possible, you may find 'actual'
    var possible: HashSet<Int>

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

    /**
     * Also updates actual if needed
     *
     * @return num of elements removed from possible
     */
    fun removePossible(iterable: Iterable<Int>): Int {
        var numRemoved = 0
        iterable.forEach {
            if (possible.remove(it)) {
                numRemoved++
            }
        }
        if (possible.size == 1 && actual == null) {
            actual = possible.first()
        }
        return numRemoved
    }

    override fun toString(): String = (actual ?: WILDCARD_CHAR).toString()

    fun isSolved(): Boolean = (actual != null)

    companion object {

        val EMPTY_SET = HashSet<Int>(0)
        private val SUDOKU_ROW_SIZE = 9
        private val WILDCARD_CHAR = '.'
        private val CHAR_TO_VALID_INT = hashMapOf(
                '1' to 1, '2' to 2, '3' to 3,
                '4' to 4, '5' to 5, '6' to 6,
                '7' to 7, '8' to 8, '9' to 9
        )

        fun fullSetFactory(vararg exclude: Int): HashSet<Int> {
            val set = fullSet()
            exclude.forEach { set.remove(it) }
            return set
        }

        fun fullSet(): HashSet<Int> = hashSetOf(1,2,3,4,5,6,7,8,9)

        @Throws(IndexOutOfBoundsException::class)
        fun rowFactory(row: CharArray?): Array<Value>? {
            return if (row == null || row.size != SUDOKU_ROW_SIZE) {
                null
            } else {
                val possible = fullSet()
                Array(
                    size = SUDOKU_ROW_SIZE,
                    init = { index ->
                        val rawChar = row[index]
                        val element: Int? = CHAR_TO_VALID_INT[rawChar]
                        possible.remove(element)
                        Value(element, possible)
                    }
                )
            }
        }
    }
}