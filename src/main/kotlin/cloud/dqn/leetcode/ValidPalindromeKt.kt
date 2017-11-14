package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/valid-palindrome/description/

    Given a string, determine if it is a palindrome, considering only
    alphanumeric characters and ignoring cases.

    For example,
        "A man, a plan, a canal: Panama" is a palindrome.
        "race a car" is not a palindrome.

    Note:
        Have you consider that the string might be empty? This is a good
        question to ask during an interview.

    For the purpose of this problem, we define empty string as valid
    palindrome.
 */
class ValidPalindromeKt {
    class Solution {
        /**
            Use indexing at beginning and end
            walk beginning up and end down to valid char
            while (beginnning > end) {
                if (s[beginning] != s[end]) {
                    return false
                }
                walk beginning up and end down to valid char
            }
            return true
         */

        object ValidChar {
            var set: HashSet<Char>
            init {
                val size = ((26*2+10) * 0.75 + 8).toInt()
                set = HashSet(size)
                set.addAll('0'..'9')
                set.addAll('A'..'Z')
                set.addAll('a'..'z')
            }
        }

        companion object {
            val VALID_CHAR = Regex("[a-zA-Z0-9]")
        }

        private fun caseInsensitiveNotEqual(s: String, i: Int, j: Int): Boolean {
            return s[i].toLowerCase() != s[j].toLowerCase()
        }

        private fun findNextIncreasingValidIndex(s: String, currentIndex: Int = -1): Int {
            var j = if (currentIndex <= -1) {
                0
            } else {
                currentIndex + 1
            }

            while (j < s.length) {
                if (ValidChar.set.contains(s[j])) {
//                if (VALID_CHAR.matches(s[j].toString())) {
                    return j
                }
                j++
            }
            return s.length
        }

        private fun findNextDecreasingValidIndex(s: String, currentIndex: Int? = null): Int {
            var j = if (currentIndex == null || currentIndex > s.length) {
                s.length
            } else {
                currentIndex - 1
            }

            while (j > 0) {
                if (ValidChar.set.contains(s[j])) {
//                if (VALID_CHAR.matches(s[j].toString())) {
                    return j
                }
                j--
            }
            return -1
        }

        fun isPalindrome(s: String): Boolean {
            var beginningIndex = findNextIncreasingValidIndex(s, -1)
            var endingIndex = findNextDecreasingValidIndex(s, s.length)
            while (beginningIndex < endingIndex) {
                if (caseInsensitiveNotEqual(s, beginningIndex, endingIndex)) {
                    return false
                }
                beginningIndex = findNextIncreasingValidIndex(s, beginningIndex)
                endingIndex = findNextDecreasingValidIndex(s, endingIndex)
            }
            return true
        }
    }
}