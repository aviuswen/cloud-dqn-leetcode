package cloud.dqn.leetcode.sudoku.base

open class GridValue<T> {
    var data: T
    constructor(data: T) {
        this.data = data
    }
}