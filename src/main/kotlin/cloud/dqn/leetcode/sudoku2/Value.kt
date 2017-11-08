package cloud.dqn.leetcode.sudoku2

class Value {
    var possible: HashSet<Int>

    constructor() {
        possible = fullSetFactory()
    }

    fun setSolved(num: Int) {
        possible = hashSetOf(num)
    }

    fun getSolved(): Int? {
        return if (possible.size == 1) {
            possible.first()
        } else {
            null
        }
    }

    fun isNotSolved(): Boolean = (possible.size != 1)

    override fun toString(): String {
        return getSolved()?.toString() ?: WILDCARD_STR
    }

    companion object {
        fun fullSetFactory(): HashSet<Int> = hashSetOf(1,2,3,4,5,6,7,8,9)
        val WILDCARD_CHAR = '.'
        private val WILDCARD_STR = WILDCARD_CHAR.toString()
        fun rowFactory(columnCount: Int): Array<Value> {
            return if (columnCount <= 0) {
                emptyArray()
            } else {
                Array(
                    size = columnCount,
                    init = { Value() }
                )
            }
        }
    }
}