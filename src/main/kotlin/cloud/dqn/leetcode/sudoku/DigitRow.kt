package cloud.dqn.leetcode.sudoku

import cloud.dqn.leetcode.sudoku.base.GridRow

class DigitRow: GridRow<Digit> {
    constructor(left: Digit, center: Digit, right: Digit)
            : super(left, center, right)
    constructor(left: Int, center: Int, right: Int)
            : super(Digit(left), Digit(center), Digit(right))
}