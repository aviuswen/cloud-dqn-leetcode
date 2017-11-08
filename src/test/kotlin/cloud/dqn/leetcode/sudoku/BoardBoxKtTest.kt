package cloud.dqn.leetcode.sudoku

import org.junit.Assert
import org.junit.Test

class BoardBoxKtTest {
    @Test
    fun reducePossiblesTest() {
        val board = Board(BoardKtTest.TEST_ARRAY)
        val boardStr = board.toString()

        (0..2).forEach { row ->
            (0..2).forEach { col ->
                val boardBox = BoardBox(board.grids, (row * 3), (col * 3))
                boardBox.reducePossibles()
            }
        }

        val updatedBoard = board.toString()

        println("OLD\n$boardStr")
        println("\nNEW\n$updatedBoard")
    }

    @Test
    fun factorialTest() {
        val intToFactorial = HashMap<Int, Int>()
        (0..9).forEach {
            intToFactorial[it] = BoardBox.factorial(it)
        }
        Assert.assertTrue(intToFactorial[9] == 362880)
        Assert.assertTrue(intToFactorial[0] == 1)
        Assert.assertTrue(intToFactorial[5] == 120)

    }
}