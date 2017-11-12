package cloud.dqn.leetcode.self

/**
 * Nov 12, 2017
 * Self generated problem
 *
 * Find the islands in a (0,1)
 *
 * Island is defined as a 1 surrounded by 0's
 *      - ?? Are pairs found on edges considered Islands (Peninsula)
 * Planned improvements:
 *      Handle easily convertable resutl
 *      Handle non NxN array
 *      Handle island of not a single 1 case
 *      Handle islands made up of 0's or 1's
 */


/*
    Algo 0:
        val map = CoordinateMap()

        fun isIsland(x: Int, y: Int, map: CoordinateMap): Boolean

        map.forEach {
            if (isIsland(x, y) {
                Add to result
            }
        }
 */
class IslandFinder: Iterable<Pair<Int, Int>> {
    var map: Array<IntArray>
    var countPeninsulas: Boolean
    var islandValueIsValid: (Int) -> Boolean

    constructor(
            map: Array<IntArray>,
            islandValueIsValid: (Int) -> Boolean = { true },
            countPeninsulas: Boolean = false) {

        this.map = map
        this.countPeninsulas = countPeninsulas
        this.islandValueIsValid = islandValueIsValid
    }

    fun mapToString(): String {
        val s = StringBuilder(map.size * map.size)
        map.forEach {
            it.forEach { s.append(it) }
            s.append("\n")
        }
        return s.toString()
    }

    fun findAllIslands(): Answer {
        val answer = Answer()
        this.forEach { (rowIndex, colIndex) ->
            val pointValue = PointValue(rowIndex, colIndex, map[rowIndex][colIndex])
            if (isIsland(pointValue) && islandValueIsValid(pointValue.value)) {
                answer.pairs.add(pointValue)
            }
        }
        return answer
    }

    override fun iterator(): Iterator<Pair<Int, Int>> {
        return IslandFinderIterator(this)
    }

    class IslandFinderIterator: Iterator<Pair<Int, Int>> {
        private val islandFinder: IslandFinder
        private var rowIndex: Int
        private var colIndex: Int

        constructor(islandFinder: IslandFinder) {
            this.islandFinder = islandFinder
            this.rowIndex = 0
            this.colIndex = 0
        }

        override fun hasNext(): Boolean {
            return islandFinder.onMap(rowIndex, colIndex)
        }

        override fun next(): Pair<Int, Int> {
            val pair = Pair(rowIndex, colIndex)
            val row = islandFinder.map[rowIndex]
            colIndex++
            if (colIndex >= row.size) {
                rowIndex++
                colIndex = 0
            }
            return pair
        }
    }

    private fun isIsland(p: PointValue): Boolean {
        if (!countPeninsulas && isOnBoarder(p)) {
            return false
        }

        var itsAnIsland = true
        iterateAroundPoint(p, body = { pointValue ->
            itsAnIsland = itsAnIsland && (p.value != pointValue.value)
        } )
        return itsAnIsland
    }

    inline private fun iterateAroundPoint(p: PointValue, body: (PointValue) -> Unit) {
        p.getBoarderPoints().forEach { point ->
            pointValueFactory(point)?.let { body(it) }
        }
    }

    private fun pointValueFactory(row: Int, col: Int): PointValue? {
        return getValue(row, col)?.let { PointValue(row, col, it) }
    }

    private fun pointValueFactory(p: Point): PointValue? {
        return pointValueFactory(p.row, p.col)
    }

    private fun getValue(p: Point): Int? {
        return getValue(p.row, p.col)
    }

    private fun getValue(row: Int, col: Int): Int? {
        if (onMap(row, col)) {
            return map[row][col]
        }
        return null
    }

    private fun onMap(row: Int, col: Int): Boolean {
        if (row >= 0 && row < map.size) {
            val r = map[row]
            if (col >= 0 && col < r.size) {
                return true
            }
        }
        return false
    }

    private fun isOnBoarder(p: PointValue): Boolean {
        if (onMap(p.row, p.col)) {
            val row = map[p.row]
            return p.row == 0
                    || p.row == map.size - 1
                    || p.col == 0
                    || p.col == row.size - 1
        }
        return false
    }

    /*************************************************
     *  Sub Classes (Usually to be devided into it's own file)
     **************************************************/
    /*
        Class for storing an answer to get all answers
        Used to convert answer to that required by program
     */
    class Answer {
        val pairs = HashSet<PointValue>()

        fun toFormat() {
            TODO("finish")
        }
    }

    data class Point (
        val row: Int,
        val col: Int
    ) {
        fun toPair(): Pair<Int, Int> = Pair(row, col)
    }

    class PointValue {
        val row: Int
        val col: Int
        var value: Int

        constructor(row: Int, col: Int, value: Int) {
            this.row = row
            this.col = col
            this.value = value
        }

        private constructor(p: Point, value: Int) {
            this.row = p.row
            this.col = p.col
            this.value = value
        }

        override fun equals(other: Any?): Boolean {
            return other is PointValue
                    && other.row == row
                    && other.col == col
                    && other.value == value
        }

        override fun hashCode(): Int {
            return "r${row}c$col{$}v${value}".hashCode()
        }
        // topLeft, topCenter, topRight
        private fun topLeft(): Point = Point(row - 1, col - 1)
        private fun topCenter(): Point = Point( row -1, col)
        private fun topRight(): Point = Point( row -1, col + 1)

        // middleLeft, middleRight
        private fun middleLeft(): Point = Point(row, col - 1)
        private fun middleRight(): Point = Point(row, col + 1)

        // bottomLeft, bottomCenter, bottomRight
        private fun bottomLeft(): Point = Point(row + 1, col - 1)
        private fun bottomMiddle(): Point = Point(row + 1, col)
        private fun bottomRight(): Point = Point(row + 1, col + 1)

        fun getBoarderPoints(): Array<Point> {
            return arrayOf(
                    topLeft(), topCenter(), topRight(),
                    middleLeft(), middleRight(),
                    bottomLeft(), bottomMiddle(), bottomRight()
            )
        }
    }
}