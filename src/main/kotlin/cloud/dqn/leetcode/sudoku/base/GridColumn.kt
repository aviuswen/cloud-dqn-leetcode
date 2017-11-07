package cloud.dqn.leetcode.sudoku.base

class GridColumn<T> {
    var upper: T
    var middle: T
    var bottom: T

    constructor(upper: T, middle: T, bottom: T) {
        this.upper = upper
        this.middle = middle
        this.bottom = bottom
    }


    override fun equals(other: Any?): Boolean {
        if (other is GridColumn<*>) {
            other.upper?.let { oUpper ->
                other.middle?.let { oMiddle ->
                    other.bottom?.let { oBottom ->
                        return oUpper == upper && oMiddle == middle && oBottom == bottom
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
        return "($upper,$middle,$bottom)"
    }
}