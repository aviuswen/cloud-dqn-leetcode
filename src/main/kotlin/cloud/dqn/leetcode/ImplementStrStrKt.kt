package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/implement-strstr/description/
    Implement strStr().

    Return the index of the first occurrence of needle in haystack,
    or -1 if needle is not part of haystack.

    Example 1:

    Input: haystack = "hello", needle = "ll"
    Output: 2
    Example 2:

    Input: haystack = "aaaaa", needle = "bba"
    Output: -1
 */
class ImplementStrStrKt {
    class Solution {
        // 2, "hello", "ll"
        fun isSubstr(index: Int, haystack: String, needle: String): Boolean {
            var tempIndex = index   // 2, 3, 4
            var needleIndex = 0     // 0, 1, 2
            val maxIndex = tempIndex + needle.length - 1 // 3
            if (tempIndex >= 0 && maxIndex < haystack.length) {
                while (tempIndex <= maxIndex) {
                    if (haystack[tempIndex] != needle[needleIndex]) {
                        return false
                    }
                    needleIndex++
                    tempIndex++
                }
                return true
            }
            return false
        }

        fun strStr(haystack: String, needle: String): Int {
            if (needle.isEmpty()) {
                return 0
            }
            if (needle.length <= haystack.length) {
                var index = 0
                while (index < haystack.length) {
                    if (isSubstr(index, haystack, needle)) {
                        return index
                    }
                    index++
                }
            }
            return -1
        }
    }
}