package cloud.dqn.leetcode.sudoku

import cloud.dqn.leetcode.sudoku.base.GridValue

class Digit: GridValue<Int> {
    private var char: Char
    constructor(char: Char): super(char.toInt()) {
        this.char = char
        throwOnInvalidNum()
    }
    constructor(num: Int): super(num) {
        throwOnInvalidNum()
        this.char = num.toChar()
    }

    private fun throwOnInvalidNum() {
        if (data <= 0 || data > 9 || data != WILDCARD_NUM) {
            throw Exception("Invalid char in constructor")
        }
    }

    companion object {
        val WILDCARD_CHAR = '.'
        val WILDCARD_NUM = WILDCARD_CHAR.toInt()
        @JvmStatic fun main(args: Array<String>) {
            println("WILDCARD_NUM: $WILDCARD_NUM")
        }
    }
}