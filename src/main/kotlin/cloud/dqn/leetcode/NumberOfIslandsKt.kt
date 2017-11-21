package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/number-of-islands/description/
 *
    Given a 2d grid map of '1's (land) and '0's (water),
    count the number of islands. An island is surrounded by
    water and is formed by connecting adjacent lands horizontally
    or vertically. You may assume all four edges of the grid
    are all surrounded by water.

    Example 1:

    11110
    11010
    11000
    00000
    Answer: 1

    Example 2:

    11000
    11000
    00100
    00011
    Answer: 3
 */
class NumberOfIslandsKt {
    /**
        copy char array
        for each grid {
            if grid == '1' {
                change self and all surrounding to 'x'
                add one to count
            }
        }
    */

    class Solution {
        companion object {
            val LAND = '1'
            private val MARKED = 'x'
        }

        private fun inRange(row: Int, col: Int, grid: Array<CharArray>): Boolean {
            return row >= 0
                    && col >= 0
                    && row < grid.size
                    && col < grid[row].size
        }

        private fun convertToX(row: Int, col: Int, grid: Array<CharArray>) {
            if (inRange(row, col, grid)) {
                val value = grid[row][col]
                if (value == LAND) {
                    grid[row][col] = MARKED
                    // upper
                    convertToX(row - 1, col, grid)
                    // left
                    convertToX(row, col - 1, grid)
                    // right
                    convertToX(row, col + 1, grid)
                    // bottom
                    convertToX(row + 1, col, grid)
                }
            }
        }

        private fun charArrCopy(grid: Array<CharArray>): Array<CharArray> {
            return Array(grid.size, { row ->
                CharArray(grid[row]!!.size, { col ->
                    grid[row][col]
                })
            })
        }

        fun numIslands(grid: Array<CharArray>): Int {
            val gridCopy = charArrCopy(grid)
            var count = 0
            gridCopy.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, charAt ->
                    if (charAt == LAND) {
                        count++
                        convertToX(rowIndex, colIndex, gridCopy)
                    }
                }
            }
            return count
        }
    }
}