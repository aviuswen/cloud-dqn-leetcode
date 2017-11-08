package cloud.dqn.leetcode.sudoku2

import org.junit.Assert
import org.junit.Test

class BoardKtTest {
    /** input
     *  [
     *      [".",".","9", "7","4","8", ".",".","."],
     *      ["7",".",".", ".",".",".", ".",".","."],
     *      [".","2",".", "1",".","9", ".",".","."],
     *
     *      [".",".","7", ".",".",".", "2","4","."],
     *      [".","6","4", ".","1",".", "5","9","."],
     *      [".","9","8", ".",".",".", "3",".","."],
     *
     *      [".",".",".", "8",".","3", ".","2","."],
     *      [".",".",".", ".",".",".", ".",".","6"],
     *      [".",".",".", "2","7","5", "9",".","."]
     *  ]
     *
     *  Your answer
     *  [
     *      [".",".","9", "7","4","8", ".",".","2"],
     *      ["7",".",".", "6",".","2", ".",".","9"],
     *      [".","2",".", "1",".","9", ".",".","."],
     *
     *      [".",".","7", "9","8","6", "2","4","1"],
     *      ["2","6","4", "3","1","7", "5","9","8"],
     *      ["1","9","8", "5","2","4", "3","6","7"],
     *
     *      ["9",".",".", "8","6","3", ".","2","."],
     *      [".",".","2", "4","9","1", ".",".","6"],
     *      [".",".",".", "2","7","5", "9",".","."]
     *  ]
     *
     *  Expected
     *  [
     *      ["5","1","9", "7","4","8", "6","3","2"],
     *      ["7","8","3", "6","5","2", "4","1","9"],
     *      ["4","2","6", "1","3","9", "8","7","5"],
     *
     *      ["3","5","7", "9","8","6", "2","4","1"],
     *      ["2","6","4", "3","1","7", "5","9","8"],
     *      ["1","9","8", "5","2","4", "3","6","7"],
     *
     *      ["9","7","5", "8","6","3", "1","2","4"],
     *      ["8","3","2", "4","9","1", "7","5","6"],
     *      ["6","4","1", "2","7","5", "9","8","3"]
     *  ]




     */
    companion object {
        val TEST_ARRAY:Array<CharArray> = arrayOf(
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

        val LEET_EXAMPLE: Array<CharArray> = arrayOf(
                // COL       0    1    2      3    4    5      6    7    8       row
                charArrayOf('5', '3', '.',   '.', '7', '.',   '.', '.', '.'), // 0
                charArrayOf('6', '.', '.',   '1', '9', '5',   '.', '.', '.'), // 1
                charArrayOf('.', '9', '8',   '.', '.', '.',   '.', '6', '.'), // 2

                charArrayOf('8', '.', '.',   '.', '6', '.',   '.', '.', '3'), // 3
                charArrayOf('4', '.', '.',   '8', '.', '3',   '.', '.', '1'), // 4
                charArrayOf('7', '.', '.',   '.', '2', '.',   '.', '.', '6'), // 5

                charArrayOf('.', '6', '.',   '.', '.', '.',   '2', '8', '.'), // 6
                charArrayOf('.', '.', '.',   '4', '1', '9',   '.', '.', '5'), // 7
                charArrayOf('.', '.', '.',   '.', '8', '.',   '.', '7', '9')  // 8
        )

        val HARD_EXAMPLE: Array<CharArray> = arrayOf(
                // COL       0    1    2      3    4    5      6    7    8       row
                charArrayOf('.', '.', '5',   '.', '.', '8',   '1', '.', '3'), // 0
                charArrayOf('7', '.', '.',   '.', '.', '2',   '.', '.', '.'), // 1
                charArrayOf('.', '.', '6',   '.', '9', '.',   '.', '.', '.'), // 2

                charArrayOf('9', '.', '.',   '.', '2', '.',   '.', '.', '5'), // 3
                charArrayOf('.', '2', '1',   '.', '3', '.',   '8', '9', '.'), // 4
                charArrayOf('4', '.', '.',   '.', '8', '.',   '.', '.', '6'), // 5

                charArrayOf('.', '.', '.',   '.', '6', '.',   '9', '.', '.'), // 6
                charArrayOf('.', '.', '.',   '8', '.', '.',   '.', '.', '7'), // 7
                charArrayOf('3', '.', '9',   '7', '.', '.',   '4', '.', '.')  // 8
        )

        val LEETCODE2:  Array<CharArray> = arrayOf(
                // COL       0    1    2      3    4    5      6    7    8       row
                charArrayOf('.', '.', '9',   '7', '4', '8',   '.', '.', '.'), // 0
                charArrayOf('7', '.', '.',   '.', '.', '.',   '.', '.', '.'), // 1
                charArrayOf('.', '2', '.',   '1', '.', '9',   '.', '.', '.'), // 2

                charArrayOf('.', '.', '7',   '.', '.', '.',   '2', '4', '.'), // 3
                charArrayOf('.', '6', '4',   '.', '1', '.',   '5', '9', '.'), // 4
                charArrayOf('.', '9', '8',   '.', '.', '.',   '3', '.', '.'), // 5

                charArrayOf('.', '.', '.',   '8', '.', '3',   '.', '2', '.'), // 6
                charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '6'), // 7
                charArrayOf('.', '.', '.',   '2', '7', '5',   '9', '.', '.')  // 8
        )

        val OUTPUT: Array<CharArray> = arrayOf(
                // COL       0    1    2      3    4    5      6    7    8       row
                charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'), // 0
                charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'), // 1
                charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'), // 2

                charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'), // 3
                charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'), // 4
                charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'), // 5

                charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'), // 6
                charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.'), // 7
                charArrayOf('.', '.', '.',   '.', '.', '.',   '.', '.', '.')  // 8
        )
    }

    @Test
    fun constructorTest() {
        val board = Board(TEST_ARRAY)
        Board.BOARD_INDEX_RANGE.forEach { row ->
            Board.BOARD_INDEX_RANGE.forEach { col ->
                val char = TEST_ARRAY[row][col]
                val value = board.grids[row][col]
                Assert.assertTrue("[$row][$col] :: $char != $value", char.toString() == value.toString() )
            }
        }
    }

    @Test
    fun solveTest() {
        val boards = arrayOf(
            Board(TEST_ARRAY),
            Board(LEET_EXAMPLE),
            Board(HARD_EXAMPLE)
        ).forEach {
            val success = it.solve(OUTPUT)
            Assert.assertTrue(success)
            Assert.assertTrue(it.allDone())
            Assert.assertNull(it.findInvalid())
        }
    }

    @Test
    fun leetCode2() {
        val board = Board(LEETCODE2)
        val solved = board.solve(OUTPUT)
        if (!solved) {
            val guessBoard = board.guessOneAndSeeIfItWorks(OUTPUT)
            Assert.assertNotNull(guessBoard)
        }
    }

    @Test
    fun bitMaskingDeveloping() {

    }
}