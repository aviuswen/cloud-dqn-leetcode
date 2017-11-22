package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/repeated-substring-pattern/description/
    Given a non-empty string check if it can be constructed by taking a
    substring of it and appending multiple copies of the substring together.
    You may assume the given string consists of lowercase English letters
    only and its length will not exceed 10000.

    Example 1:
    Input: "abab"

    Output: True

    Explanation: It's the substring "ab" twice.
    Example 2:
    Input: "aba"

    Output: False
    Example 3:
    Input: "abcabcabcabc"

    Output: True

    Explanation: It's the substring "abc" four times.
    (And the substring "abcabc" twice.)
 */
class RepeatedSubstringPatternKt {
    class Solution {
        private fun genRegex(subStr: String): Regex {
            return Regex("\\A($subStr){2,}\\Z")
        }

        fun repeatedSubstringPattern(s: String): Boolean {
            var endIndex = 0
            while (endIndex <= s.length / 2) {
                if (s.length % (endIndex + 1) == 0) {
                    if (genRegex(s.substring(0, endIndex + 1)).matches(s)) {
                        return true
                    }
                }
                endIndex++
            }
            return false
        }
    }
}