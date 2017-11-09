package cloud.dqn.leetcode.sudoku2

class DigitSet: Iterable<Int> {

    private var internalSet: Int
    private var size: Int

    constructor(setFull: Boolean = true) {
        if (setFull) {
            size = MAX_SIZE
            internalSet = NINE_ONES
        } else {
            size = 0
            internalSet = EMPTY_SET
        }
    }

    constructor(digitSet: DigitSet) {
        this.internalSet = digitSet.internalSet
        this.size = digitSet.size
    }

    fun getSize() = size

    fun isEmpty() = size == 0

    fun getFirst(): Int {
        return getRandom() ?: -1
    }

    fun getRandom(): Int? {
        this.forEach { return it }
        return null
    }

    fun add(value: Int): Boolean {
        var valueWasMissing = false
        if (validDigit(value)) {
            val internalValue = convertToInternal(value)
            valueWasMissing = !containsInternal(internalValue)
            if (valueWasMissing) {
                size++
            }
            internalSet = internalSet or internalValue
        }
        return valueWasMissing
    }

    fun addAll(iterable: Iterable<Int>) = iterable.forEach { add(it) }

    fun remove(value: Int): Boolean {
        var setChanged = false
        if (validDigit(value)) {
            val internalValue = convertToInternal(value)
            setChanged = containsInternal(internalValue)
            if (setChanged) {
                size--
            }
            internalSet = internalSet and (internalValue.inv())
        }
        return setChanged
    }

    fun removeAll(iterable: Iterable<Int>) = iterable.forEach { remove(it) }

    fun clear() {
        size = 0
        internalSet = EMPTY_SET
    }

    fun contains(value: Int): Boolean = containsInternal(convertToInternal(value))

    private fun containsInternal(internalValue: Int): Boolean {
        return (internalValue and internalSet) > 0
    }

    private fun convertToInternal(value: Int): Int = (1 shl (value - 1))

    override fun iterator(): Iterator<Int> {
        return DigitIterator(internalSet)
    }

    private class DigitIterator: Iterator<Int> {
        val set: Int
        var nextValue: Int

        constructor(set: Int) {
            this.set = set
            nextValue = 1
            walkNextValueToExisting()
        }

        private fun walkNextValueToExisting() {
            while (nextValue <= 9) {
                if (1.shl(nextValue - 1).and(set) > 0) {
                    break
                } else {
                    nextValue++
                }
            }
        }

        override fun hasNext(): Boolean = (nextValue <= MAX_SIZE)

        override fun next(): Int {
            val element = nextValue++
            walkNextValueToExisting()
            return element
        }
    }

    companion object {
        private val ALL_ONES: Int = (-1) ushr 1
        private val NINE_ONES: Int =  511 //(Math.pow(2.0, 9.0) - 1).toInt()
        private val EMPTY_SET: Int = 0
        private val MAX_SIZE = 9

        private fun validDigit(digit: Int): Boolean {
            return digit >= 1 && digit <= MAX_SIZE
        }

        fun emptyDigitSet(): DigitSet {
            return DigitSet(setFull = false)
        }
    }
}