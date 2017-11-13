package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/palindrome-number/description/
 *
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
class PalindromeNumberKt {
    class Solution {
        /**
            Assumption: "Do this without extra space" =>
            no other classes or arrays but primative variables allowed

            Algo0: use start and end; walk to middle comparing values
            p                                              q
            n0 n1 ... n(m/2-1) n(m/2)? n(m/2+1)... n(m-1) n(m)

            p at begining
            q at end

            special?:
            negative numbers?; zero
            p && q are not null-ish

            while (p != q) {
            if (x[p] != x[q]) {
            return false
            }
            p++
            q--
            }
            return true
         */

        fun powerOfTen(exponent: Int): Long {
            // 10^0 = 1
            // 10^1 = 10
            // 10^2 = 100

            var power: Long = 1
            var index = 0
            while (index < exponent) {
                index++
                power *= 10
            }
            return power
        }

        fun digitAtPlace(place: Int, x: Int): Long {
            // place => 3210
            // x =>     abcd
            // return (x / (Math.pow(10.0, place.toDouble())).toLong()) % 10
            return (x / powerOfTen(place)) % 10L
        }

        fun placeOfMostSignificantBit(x: Int): Int {
            // place => 3210
            // x =>     abcd
            if (x < 10) {
                return 0
            } else {
                var p = 0
                // while ((Math.pow(10.0, p)).toLong() <= x) {
                while (powerOfTen(p) <= x) {
                    p++
                }
                p--
                return p
            }
        }

        // Place is based upon index numbering
        fun isPalindrome(x: Int): Boolean {
            if (x < 0) {
                return false
            }

            var p = placeOfMostSignificantBit(x)
            var q = 0 // make q == least significant bit

            while (p > q) {
                if (digitAtPlace(p, x) != digitAtPlace(q, x)) {
                    return false
                }
                p--
                q++
            }

            return true
        }
    }
}