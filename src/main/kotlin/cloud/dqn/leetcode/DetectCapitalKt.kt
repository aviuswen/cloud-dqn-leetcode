package cloud.dqn.leetcode

class DetectCapitalKt {
    class Solution {
        /**
        Algo0:
        if (first leter is Capital) {
        the rest must be capital

        or

        the rest must be lower case
        } else {
        // all must be lower case
        }
         */
        companion object {
            val allUpperCaseRegex = Regex("[A-Z]+")
            val allLowerCaseRegex = Regex("[a-z]+")
        }
        private fun allLowerCase(subStr: String): Boolean {
            subStr.forEach {
                if (!it.isLowerCase()) {
                    return false
                }
            }
            return true
        }

        private fun allUpperCase(subStr: String): Boolean {
            subStr.forEach {
                if (!it.isUpperCase()) {
                    return false
                }
            }
            return true
        }

        fun detectCapitalUse(word: String): Boolean {
            if (word.length > 1) {
                val firstChar = word[0]
                val subStr = word.substring(1, word.length)
                return if (firstChar.isUpperCase()) {
                    // return allUpperCase(subStr) || allLowerCase(subStr)
                    allUpperCaseRegex.matches(subStr) || allLowerCaseRegex.matches(subStr)
                } else {
                    // return allLowerCase(subStr)
                    allLowerCaseRegex.matches(subStr)
                }
            }
            return true
        }
    }
}