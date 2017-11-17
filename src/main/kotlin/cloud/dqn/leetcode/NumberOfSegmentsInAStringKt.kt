package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/number-of-segments-in-a-string/description/
    Count the number of segments in a string, where a segment is defined to
    be a contiguous sequence of non-space characters.

    Please note that the string does not contain any non-printable characters.

    Example:

    Input: "Hello, my name is John"
    Output: 5
 */
class NumberOfSegmentsInAStringKt {
    class Solution {
        private fun walkToNextChar(s: String, index: Int): Int {
            var i = index
            while (i < s.length && s[i] == ' ') {
                i++
            }
            return i
        }

        private fun walkToNextWhiteSpace(s: String, index: Int): Int {
            var i = index
            while (i < s.length && s[i] != ' ') {
                i++
            }
            return i
        }

        fun countSegments(s: String): Int {
            var index = 0
            var temp = 0
            var count = 0
            while (index < s.length) {
                temp = walkToNextChar(s, index)
                index = walkToNextWhiteSpace(s, temp)
                if (temp != index) {
                    count++
                }
            }
            return count
        }
    }
}