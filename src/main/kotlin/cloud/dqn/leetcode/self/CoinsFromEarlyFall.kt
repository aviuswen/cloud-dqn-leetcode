package cloud.dqn.leetcode.self


/**
 * "Problem from after lunch"
 * Designer: JN
 */
object CoinsFromEarlyFall {
    @JvmStatic fun main(args: Array<String>) {
        val coinValues = intArrayOf(5, 25, 50, 100)
        val possible = possibleSums(coinValues, intArrayOf(4, 3, 2, 1))
        println("hi")
    }

    /**
     * Example coinValues = [5, 10, 25], quantity = [1, 3, 2]
     * Calulate the number of possible sums
     * so
     *  coins in hand == [5, 10, 10, 10, 25, 25]
     *  possibles = [
     *      0,
     *      (5), (5,10), (5, 10, 10), (5, 10, 10, 25), (5, 10, 10, 25, 25),
     *          (5, 25), (5, 25, 25), (5, 10) ...
     *      ...
     *      ]
     *  return possibles.uniqueCount
     * @param coinValues value of each coin
     * @param quantity number of coins
     */
    fun possibleSums(coinValues: IntArray, quantity: IntArray): Int {
        // sanity
        if (coinValues.isEmpty() || quantity.isEmpty() || coinValues.size != quantity.size) {
            return 0
        }
        val totals = HashSet<Int>()

        // create unified list
        var totalSize = 0
        quantity.forEach { totalSize += it }

        val allCoins = IntArray(totalSize, { 0 })
        var allCoinsIndex = 0
        coinValues.forEachIndexed { index, value ->
            val quant = quantity[index]
            if (quant > 0) {
                var inner = 0
                while (inner < quant) {
                    allCoins[allCoinsIndex] = value
                    allCoinsIndex++
                    inner++
                }
            }
        }

        possibleSumsHelper(0, allCoins, totals)
        return totals.size
    }

    private fun possibleSumsHelper(total: Int, coinsList: IntArray, totals: HashSet<Int>) {
        totals.add(total)
        if (coinsList.isEmpty()) {
            return
        } else {
            coinsList.forEachIndexed { index, value ->
                removeSingle(index, coinsList)?.let {
                    possibleSumsHelper(total + value, it, totals)
                }
            }
        }
    }

    // alternative is to use System.arrayCopy
    private fun removeSingle(indexToRemove: Int, arr: IntArray): IntArray? {
        return if (indexToRemove < 0 || indexToRemove >= arr.size) {
            null
        } else {
            var index = 0
            var insertionIndex = 0
            val result = IntArray(arr.size - 1)
            while (index < arr.size) {
                if (index != indexToRemove) {
                    result[insertionIndex] = arr[index]
                    insertionIndex++
                }
                index++
            }
            result
        }
    }

}