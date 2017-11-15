package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/power-of-two/description/

    Given an integer, write a function to determine if it
    is a power of two.
 */
class PowerOfTwoKt {
    class Solution {
        /**
            Use bit shifiting
            Find the least significant 1 bit while shifting right
                and also shiftleft past it.
            If bit not found return false
            Shift right checking each bit
                If its 1 then return false
            return true
        */
        fun isPowerOfTwo(n: Int): Boolean {
            if (n <= 0) {
                return false
            }
            var current = n
            var bitFound = false

            // right shift until a 1 bit is found
            while (current > 0) {
                if ((current and 1) == 0) {
                    current = current shr 1
                } else {
                    bitFound = true
                    break
                }
            }

            if (!bitFound) {
                return false
            }

            current = current shr 1

            while (current > 0) {
                if ((current and 1) == 1) {
                    return false
                }
                current = current shr 1
            }

            return true
        }
    }
}