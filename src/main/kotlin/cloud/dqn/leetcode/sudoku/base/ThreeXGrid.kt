package cloud.dqn.leetcode.sudoku.base

class ThreeXGrid {
    private val grid: Array<GridValue>

    constructor(wildcard: GridValue) {
        this.grid = Array(9, {wildcard})
    }

    fun set(loc: Location, value: GridValue) {
        grid[locationToIndex[loc]!!] = value
    }

    fun get(loc: Location): GridValue {
        return grid[locationToIndex[loc]!!]
    }

    fun get(row: Row): GridRow<GridValue> {
        return when (row) {
            Row.upper ->    GridRow(
                                left = get(Location.upperLeft),
                                center = get(Location.upperCenter),
                                right = get(Location.upperRight)
                            )
            Row.middle ->   GridRow(
                                left = get(Location.middleLeft),
                                center = get(Location.middleCenter),
                                right = get(Location.middleRight)
                            )
            Row.bottom ->   GridRow(
                                left = get(Location.bottomLeft),
                                center = get(Location.bottomCenter),
                                right = get(Location.bottomRight)
                            )
        }
    }

    fun get(col: Column): GridColumn<GridValue> {
        return when (col) {
            Column.left ->       GridColumn(
                                    upper = get(Location.upperLeft),
                                    middle = get(Location.middleLeft),
                                    bottom = get(Location.bottomLeft)
                                )
            Column.center ->   GridColumn(
                                    upper = get(Location.upperCenter),
                                    middle = get(Location.middleCenter),
                                    bottom = get(Location.bottomCenter)
                                )
            Column.right ->   GridColumn(
                                    upper = get(Location.upperRight),
                                    middle = get(Location.middleRight),
                                    bottom = get(Location.bottomRight)
                                )
        }
    }

    enum class Location {
        upperLeft, upperCenter, upperRight,
        middleLeft, middleCenter, middleRight,
        bottomLeft, bottomCenter, bottomRight
    }

    enum class Row { upper, middle, bottom }

    enum class Column { left, center, right }

    companion object {
        private val locationToIndex = hashMapOf(
                Location.upperLeft to 0,
                Location.upperCenter to 1,
                Location.upperRight to 2,

                Location.middleLeft to 3,
                Location.middleCenter to 4,
                Location.middleRight to 5,

                Location.bottomLeft to 6,
                Location.bottomCenter to 7,
                Location.bottomRight to 8
        )
    }
}