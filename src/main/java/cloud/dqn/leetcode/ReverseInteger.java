package cloud.dqn.leetcode;

/**
 * Java Implementation
 * https://leetcode.com/problems/reverse-integer/description/
     Reverse digits of an integer.

     Example1: x = 123, return 321
     Example2: x = -123, return -321

     click to show spoilers.

     Note:
     The input is assumed to be a 32-bit signed integer. Your function
     should return 0 when the reversed integer overflows.
 */
public class ReverseInteger {
    class Solution {
        public int reverse(int x) {
            // initial implementation:
        /*
            store the sign
            make into positive int
            while (value has something at tens places) {
                val tens = modulo by 10
                value /= 10
                result = result * 10
                result = result + tens
            }
            check bounds of result
            fix for sign
            return result
        */
            long result = 0;
            if (x > -10 && x < 10) {
                result = x;
            } else {
                int sign = 1;
                if (x < 0) {
                    sign = -1;
                    x *= -1; // unknown behaviour when working with modulo and negatives
                }

                while (x > 0) {
                    int tensPlace = x % 10; //  1
                    x /= 10; //

                    result *= 10; //  320
                    result += tensPlace;  //  321
                }

                if (result > Integer.MAX_VALUE) { // possible syntax error
                    result = 0;
                }

                result *= sign;
            }
            return (int)result;
        }
    }
}
