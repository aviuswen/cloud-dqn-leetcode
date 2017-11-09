package cloud.dqn.leetcode.sudoku2.old

@Deprecated("Replaced with DigitSet using bit operations")
class DigitSet: Iterable<Int> {
    private val set: BooleanArray
    private var size: Int

    private constructor() {
        size = SIZE
        set = BooleanArray(ACTUAL_SIZE)
    }

    constructor(value: Int? = null) {
        if (value == null || !inRange(value)) {
            set = BooleanArray(ACTUAL_SIZE, {true})
            size = SIZE
        } else {
            set = BooleanArray(ACTUAL_SIZE, {false})
            size = 1
            set[value] = true
        }
        set[0] = false
    }

    constructor(digitSet: DigitSet) {
        size = digitSet.size
        set = BooleanArray( ACTUAL_SIZE, { digitSet.set[it] } )
    }

    fun getSize() = size

    fun isEmpty() = (size == 0)

    fun getFirst(): Int {
        set.forEachIndexed { index, b ->
            if (b) {
                return index
            }
        }
        return -1
    }

    /**
     * @returns true if set was changed
     */
    fun add(value: Int?): Boolean {
        var valueWasMissing = false
        value?.let {
            if (inRange(it)) {
                valueWasMissing = !(set[it])
                set[it] = true
                if (valueWasMissing) {
                    size++
                }
            }
        }
        return valueWasMissing
    }

    fun addAll(iterable: Iterable<Int?>) = iterable.forEach { add(it) }

    /**
     * @return true if value was present in set
     */
    fun remove(value: Int?): Boolean {
        var setChanged = false
        value?.let {
            if (inRange(it)) {
                setChanged = set[it]
                set[it] = false
                if (setChanged) {
                    size--
                }
            }
        }
        return setChanged
    }

    fun removeAll(iterable: Iterable<Int?>) = iterable.forEach {remove(it)}

    fun clear() {
        set.forEachIndexed { index, _ -> set[index] = false }
        size = 0
    }

    fun contains(value: Int?): Boolean {
        value?.let {
            if (inRange(it)) {
                return set[it]
            }
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return DigitIterator(set)
    }

    private class DigitIterator: Iterator<Int> {

        val array: BooleanArray

        var nextValue: Int

        constructor(array: BooleanArray) {
            this.array = array
            this.nextValue = 1
            walkStartToNextValid()
        }

        private fun walkStartToNextValid() {
            while (nextValue < ACTUAL_SIZE) {
                if (!array[nextValue]) {
                    nextValue++
                } else {
                    break
                }
            }
        }

        override fun hasNext(): Boolean {
            return nextValue < ACTUAL_SIZE
        }

        override fun next(): Int {
            val element = nextValue
            nextValue++
            walkStartToNextValid()
            return element
        }
    }

    companion object {
        private val SIZE = 9
        private val ACTUAL_SIZE = 10
        private fun inRange(value: Int): Boolean =  value > 0 && value <= 9
        fun emptyDigitSet(): DigitSet {
            return DigitSet()
        }
    }
}