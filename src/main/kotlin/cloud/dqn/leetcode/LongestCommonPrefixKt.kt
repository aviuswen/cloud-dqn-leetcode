package cloud.dqn.leetcode

class LongestCommonPrefixKt {
    class Solution {
        /**
            var largestPrefix = getLongestString(strs)
            strs.forEach {
                largestPrefix = longestPrefix(largestPrefix, it)
            }
            return largestPrefix
        */

        private fun longestPrefix(largestPrefix: StringBuilder, str: String): StringBuilder {
            val builder = StringBuilder()
            largestPrefix.forEachIndexed { index, character ->
                if (index >= str.length || str[index] != character) {
                    return builder
                } else {
                    builder.append(character)
                }
            }
            return builder
        }

        private fun longestStringIndex(strs: Array<String>): Int {
            var longestIndex = -1
            var longestLength = -1
            strs.forEachIndexed { index, str ->
                if (str.length > longestLength) {
                    longestLength = str.length
                    longestIndex = index
                }
            }
            return longestIndex
        }

        fun longestCommonPrefix(strs: Array<String>): String {
            val longestIndex = longestStringIndex(strs)
            if (longestIndex < 0) {
                return ""
            }
            var longestStrBuilder = StringBuilder(strs[longestIndex])
            strs.forEach {
                longestStrBuilder = longestPrefix(longestStrBuilder, it)
            }
            return longestStrBuilder.toString()
        }
    }
}