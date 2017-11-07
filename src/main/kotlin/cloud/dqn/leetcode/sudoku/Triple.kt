package cloud.dqn.leetcode.sudoku

@Deprecated("Moving to singleCelStyle")
class Triple {
    var first: Int
    var second: Int
    var third: Int
    constructor(first: Int, second: Int, third: Int) {
        this.first = first
        this.second = second
        this.third = third
    }

    /**
     * [n0, n1, n2].isSubsetOf( [n0, WILDCARD)INT, n2] )
     */
    fun isSubsetOf(other: Triple): Boolean {
        return firstIsSubset(other.first)
                && secondIsSubset(other.second)
                && thirdIsSubset(other.third)
    }

    private fun firstIsSubset(other: Int): Boolean {
        return first == WILDCARD_INT || first == other || other == WILDCARD_INT
    }
    private fun secondIsSubset(other: Int): Boolean {
        return second == WILDCARD_INT || second == other || other == WILDCARD_INT
    }
    private fun thirdIsSubset(other: Int): Boolean {
        return third == WILDCARD_INT || third == other || other == WILDCARD_INT
    }

    override fun equals(other: Any?): Boolean {
        return other is Triple && equals(triple = other)
    }

    fun equals(triple: Triple): Boolean {
        return equals(triple.first, triple.second, triple.third)
    }

    fun equals(first: Int, second: Int, third: Int): Boolean {
        return first == this.first && second == this.second && third == this.third
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "($first,$second,$third)"
    }

    companion object {
        val WILDCARD_INT = 0
    }
}