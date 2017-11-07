package cloud.dqn.leetcode.sudoku

import cloud.dqn.leetcode.sudoku.base.GridValue
import cloud.dqn.leetcode.sudoku.base.ThreeXGrid

class SubCharGrid: ThreeXGrid<Int> {

    constructor(wildcard: GridValue<Int>) : super(wildcard)

    fun setDigit(loc: ThreeXGrid.Location, digit: Digit) {
        set(loc, digit)
    }

    fun setDigit(row: Row, digitRow: DigitRow) {
        
    }

    fun getDigit(loc: ThreeXGrid.Location): Digit {
        return (get(loc) as Digit)
    }

    companion object {

    }
}