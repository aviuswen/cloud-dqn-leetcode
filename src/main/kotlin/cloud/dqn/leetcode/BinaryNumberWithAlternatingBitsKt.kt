package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/binary-number-with-alternating-bits/description/

    Given a positive integer, check whether it has alternating
    bits: namely, if two adjacent bits will always have
    different values.

    Example 1:
        Input: 5
        Output: True
        Explanation:
            The binary representation of 5 is: 101
    Example 2:
        Input: 7
        Output: False
        Explanation:
            The binary representation of 7 is: 111.
    Example 3:
        Input: 11
        Output: False
        Explanation:
            The binary representation of 11 is: 1011.
    Example 4:
        Input: 10
        Output: True
        Explanation:
            The binary representation of 10 is: 1010.
 */
class BinaryNumberWithAlternatingBitsKt {
    class Solution {
        /**

            get the least sig value
            copy n - least sig bit
            while (copyN > 0) {
                if (leastSigBit(copyN) == leastSigBit) {
                    return false
                }
                leastSigBit = leastSigBit(copyN)
                copyN = copyN shr 1
            }
            return true
        */
        fun hasAlternatingBits(n: Int): Boolean {
            var leastSig = n and 1
            var copyN = n shr 1
            var temp: Int = 0
            while (copyN > 0) {
                temp = copyN and 1
                if (temp == leastSig) {
                    return false
                }
                leastSig = copyN and 1
                copyN = copyN shr 1
            }
            return true
        }
    }
}