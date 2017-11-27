package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/valid-parentheses/description/
    Given a string containing just the characters
    '(', ')', '{', '}', '[' and ']', determine if the input
    string is valid.

    The brackets must close in the correct order,
    "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
class ValidParenthesesKt {
    class Solution {
        private class CharStack {
            private data class Node(
                val value: Char,
                var next: Node? = null
            )

            private var top: Node?
            private var count: Int

            init {
                top = null
                count = 0
            }

            fun isEmpty(): Boolean = (top == null)
            fun peek(): Char? = top?.value
            // returns new count
            fun push(value: Char): Int {
                val node = Node(value, top)
                top = node
                return ++count
            }
            fun pop(): Char? {
                val value = top?.value
                top = top?.next
                return value
            }

        }
        companion object {
            val pushValue = hashSetOf('(', '[', '{')
            val popValue = hashSetOf(')', '}', ']')
            val validPairing = hashMapOf(
                '(' to ')',
                '{' to '}',
                '[' to ']'
            )
        }
        fun isValid(s: String): Boolean {
            val stack = CharStack()
            s.forEach { char ->
                if (pushValue.contains(char)) {
                    stack.push(char)
                } else if (popValue.contains(char)) {
                    val popped = stack.pop()
                    if (popped == null || validPairing[popped] != char) {
                        return false
                    }
                } else {
                    return false // not valid character
                }
            }
            return stack.isEmpty()
        }
    }
}