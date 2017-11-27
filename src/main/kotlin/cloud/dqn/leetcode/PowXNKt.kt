package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/powx-n/description/
    Implement pow(x, n).


    Example 1:

    Input: 2.00000, 10
    Output: 1024.00000
    Example 2:

    Input: 2.10000, 3
    Output: 9.26100
 */
class PowXNKt {
    class Solution {
        fun myPow(x: Double, n: Int): Double {
            if (n == 0) {
                return 1.0
            } else if (x == 0.0) {
                return 0.0
            }

            var xCopy = x
            var nCopy: Long = Math.abs(n.toLong())
            var total = 1.0
            while (nCopy > 0) {
                if ((nCopy and 1) == 1L) {
                    total *= xCopy
                }
                nCopy = nCopy shr 1
                xCopy *= xCopy
            }

            return if (n < 0) 1 / total else total
        }
    }
}