package cloud.dqn.leetcode.sudoku

import org.junit.Assert
import org.junit.Test

class BoardKtTest {

    companion object {
        val TEST_ARRAY = arrayOf(
            // COL       0    1    2      3    4    5      6    7    8       row
            charArrayOf('2', '.', '.',   '8', '.', '4',   '.', '.', '6'), // 0
            charArrayOf('.', '.', '6',   '.', '.', '.',   '5', '.', '.'), // 1
            charArrayOf('.', '7', '4',   '.', '.', '.',   '9', '2', '.'), // 2

            charArrayOf('3', '.', '.',   '.', '4', '.',   '.', '.', '7'), // 3
            charArrayOf('.', '.', '.',   '3', '.', '5',   '.', '.', '.'), // 4
            charArrayOf('4', '.', '.',   '.', '6', '.',   '.', '.', '9'), // 5

            charArrayOf('.', '1', '9',   '.', '.', '.',   '7', '4', '.'), // 6
            charArrayOf('.', '.', '8',   '.', '.', '.',   '2', '.', '.'), // 7
            charArrayOf('5', '.', '.',   '6', '.', '8',   '.', '.', '1')  // 8
        )
    }

    @Test
    fun constructorTest() {
        val board = Board(TEST_ARRAY)
        (0..8).forEach { i ->
            (0..8).forEach { j ->
                val testArrayChar: Char = TEST_ARRAY[i][j]
                val boardValue: Value = board.grids[i][j]
                Assert.assertTrue("$testArrayChar" == "$boardValue")
            }
        }
    }


    @Test
    fun toStringTest() {
        val board = Board(TEST_ARRAY)
        val str = board.toString()
        println(str)
    }

    @Test
    fun calculateBruteForceIterationsTest() {
        val board = Board(TEST_ARRAY)
        val calc = board.calculateBruteForceIterations()
        board.boardedBoxesReducePossibles()
        val calcAfter = board.calculateBruteForceIterations()
        println("hi")
    }

    @Test
    fun lineReduceTest() {
        val board = Board(TEST_ARRAY)
        val calc = board.calculateBruteForceIterations()
        board.boardedBoxesReducePossibles()
        board.lineReduceAll()
        board.boardedBoxesReducePossibles()
        val stdReductions = board.calculateBruteForceIterations()

        println("\nstdReduction\n$board\n")

        board.boxSolverAll()

        val afterBoxSolver = board.calculateBruteForceIterations()

        println("post\n$board")

        board.boardedBoxesReducePossibles()
        board.lineReduceAll()

        println("stdDeduction\n$board")

        val afterReStandardDeduction = board.calculateBruteForceIterations()

        println("afterReStandardDeduction\n$board")
        println("hi")
    }
}