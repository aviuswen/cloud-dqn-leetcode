package cloud.dqn.leetcode

import java.util.Comparator
import java.util.PriorityQueue



/**
 * https://leetcode.com/problems/trapping-rain-water-ii/description/
    Given an m x n matrix of positive integers representing the height
    of each unit cell in a 2D elevation map, compute the volume of water
    it is able to trap after raining.

    Note:
    Both m and n are less than 110. The height of each unit cell is
    greater than 0 and is less than 20,000.

    Example:

    Given the following 3x6 height map:
    [
      [1,4,3,1,3,2],
      [3,2,1,3,2,4],
      [2,3,3,2,3,1]
    ]

    Return 4.
 */
class TrappingRainWater2Kt {
    /**
        Algo0:
            0. create visited array
            1. Create priority queue
            2. Add all boarder cells to priority queue and mark as visited
            3. while priority queue is not empty {
            4.    val cell = poll()
            5.    cell.forEach NorthSouthEastWest {
            6.        if (inside of heightMap && not visited) {
            7.            mark as visited
            8.            res += max(0, cell.height - it.height)
            9.            enqueue(new cell(it.row, it.col, max(it.height, cell.height)))
                      }
                  }
              }
        Why each step
            0. Gather water only once
            1. Accumulate water by account for the lowest point
            2. Border cells cannot contain any water and are already visited
            3-5. Grab the lowest point and visit the neighbor cells
            7-8. Only count the water once and accumlate the water
                    between the neighbor cell and origin cell
            9. Add cell to queue in order to visit its neighbors
    */
    class Solution {
        companion object {
            private val directions = arrayOf(
                    Pair(-1, 0),
                    Pair(0, 1),
                    Pair(1, 0),
                    Pair(0, -1)
            )
        }
        data class Cell (
                val row: Int,
                val col: Int,
                val height: Int = 0
        )

        private fun max(x: Int, y: Int): Int = if (x > y) x else y
        private fun min(x: Int, y: Int): Int = if (x > y) y else x
        private fun inRange(x: Int, y: Int, arr: Array<IntArray>): Boolean {
            return x >= 0 && y >= 0 && x < arr.size && y < arr[x].size
        }
        fun trapRainWater(heightMap: Array<IntArray>): Int {
            if (heightMap.size <= 2 || heightMap[0].size <= 2) {
                return 0
            }

            val visited = Array(
                    heightMap.size,
                    { BooleanArray(heightMap[0].size) }
            )

//            val queue = PriorityQueue<Cell>(Comparator<Cell> { o1, o2 ->
//                o1.height - o2.height
//            })

            val queue = PriorityQueue<Cell>(1, Comparator<Cell> { (_, _, h0), (_, _, h1) -> h0 - h1 })

            // add all borders
            val m = heightMap.size
            val n = heightMap[0].size
            var i = 0
            while (i < n) {
                visited[0][i] = true
                visited[m - 1][i] = true
                queue.add(Cell(0, i, heightMap[0][i]))
                queue.add(Cell(m - 1, i, heightMap[m - 1][i]))
                i++
            }
            i = 0
            while (i < m) {
                visited[i][0] = true
                visited[i][n - 1] = true
                queue.add(Cell(i, 0, heightMap[i][0]))
                queue.add(Cell(i, n - 1, heightMap[i][n - 1]))
                i++
            }

            var res = 0
            var row: Int
            var col: Int
            while (queue.isNotEmpty()) {
                val cell = queue.poll()
                directions.forEach { (rowAdd, colAdd) ->
                    row = cell.row + rowAdd
                    col = cell.col + colAdd
                    if (inRange(row, col, heightMap) && !visited[row][col]) {
                        visited[row][col] = true
                        res += max(0, cell.height - heightMap[row][col])
                        queue.offer(Cell(row, col, max(cell.height, heightMap[row][col])))
                    }
                }
            }
            return res
        }
    }

}