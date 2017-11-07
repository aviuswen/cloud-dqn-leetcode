package cloud.dqn.leetcode.sudoku.base

open class GridRow <T>{
    var left: T
    var center: T
    var right: T

    constructor(left: T, center: T, right: T) {
        this.left = left
        this.center = center
        this.right = right
    }

    override fun equals(other: Any?): Boolean {
        if (other is GridRow<*>) {
            other.left?.let { oLeft ->
                other.center?.let { oCenter ->
                    other.right?.let { oRight ->
                        return oLeft == left && oCenter == center && oRight == right
                    }
                }
            }
        }
        return false
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "($left,$center,$right)"
    }
}