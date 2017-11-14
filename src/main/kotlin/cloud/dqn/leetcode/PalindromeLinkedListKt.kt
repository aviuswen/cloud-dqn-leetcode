package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/palindrome-linked-list/description/
 *
    Given a singly linked list, determine if it is a palindrome.

    Follow up:
    Could you do it in O(n) time and O(1) space?
 */
class PalindromeLinkedListKt {
    class ListNode(var `val`: Int = 0) {
        var next: ListNode? = null
    }

    /**
        Algo0:
            Assuption that negative values for a node cannot ever be a palindrome
                ASSUMPTION IS WRONG: if values are == then it is a palindrome
            Assumption that 1 is not the reverse of 100 as a node value

            Warning:
                Not a true O(n) solution as the
                   // once for center element if it exists
                    intIsAPalindrome(x:Int) is O(log(10)x)
                So runTime: O(n + log(10)(MiddleNode value if it exists))
                For all other elements a check must be made
                    for if (x:Int).equals(Reverse(x:Int))
                    O(n * ReversalTime(n/2))
                WARNING INCORRECT BASED UPON FIXED ASSUMPTION
                Runtime: O(n)


            Find length of list O(n)
            find halfway point
            make stack of list up to halfway point with the list the remainder
            if (length == odd) {
                newList = newList.next
            }
            while (stack.isNotEmpty()) {
                if (! pop() isPalendrom(newList.`val`) ){
                    return false
                }
            }
            return true

    */
    class Solution {
        private fun powerOfTen(exponent: Int): Long {
            var power: Long = 1
            var index = 0
            while (index < exponent) {
                index++
                power *= 10
            }
            return power
        }

        private fun digitAt(place: Int, x: Int): Long {
            return (x / powerOfTen(place)) % 10L
        }

        private fun getMostSignificantPlace(x: Int): Int {
            // place => 3210
            // x =>     abcd
            if (x < 10) {
                return 0
            } else {
                var p = 0
                while (powerOfTen(p) <= x) {
                    p++
                }
                return --p
            }
        }

        private fun intIsAPalindrome(x: Int): Boolean {
            if (x < 0) {
                return false
            }
            var mostSignificantPlace = getMostSignificantPlace(x)
            var leastSignificantPlace = 0
            while (mostSignificantPlace > leastSignificantPlace) {
                if (digitAt(mostSignificantPlace, x) != digitAt(leastSignificantPlace, x)) {
                    return false
                }
                mostSignificantPlace--
                leastSignificantPlace++
            }
            return true
        }

        private fun getListSize(head: ListNode?): Int {
            var headCopy: ListNode? = head // head is immutable
            var s = 0 // 0
            while (headCopy != null) {
                headCopy = headCopy.next
                s++
            }
            return s
        }

        /*
            Assumes:
                that neg values are never the reverse
                values > 10 && the tens place is a 0 can never be reversed
        */
        private fun isIntTheReverse(x: Int, y: Int): Boolean {
            if (x < 0 || y < 0) {
                return false
            } else if (x < 10) {
                return x == y
            } else if ( (x >= 10 || y >= 10)  && ( (x % 10 == 0) || (y % 10 == 0) ) ) {
                return false
            }

            var mostSignificantPlaceX = getMostSignificantPlace(x)

            // make sure they are the same length
            if (mostSignificantPlaceX != getMostSignificantPlace(y)) {
                return false
            }

            var leastSignificantPlaceY = 0
            while (mostSignificantPlaceX >= 0) {
                if (digitAt(mostSignificantPlaceX, x) != digitAt(leastSignificantPlaceY, y)) {
                    return false
                }
                mostSignificantPlaceX--
                leastSignificantPlaceY++
            }

            return true
        }

        fun isPalindrome(head: ListNode?): Boolean {
            var headCopy = head // head is immutable

            val size = getListSize(headCopy)

            if (size <= 1) {
                return true
            }

            var halfway = size / 2
            var stackTop: ListNode? = null
            while (halfway > 0) {
                headCopy?.let {
                    val tempNext = it.next
                    it.next = null
                    if (stackTop == null) {
                        stackTop = it
                    } else {
                        val temp = stackTop
                        stackTop = it
                        it.next = temp
                    }
                    headCopy = tempNext
                }
                halfway--
            }

            // check that the middle element is a palindrome
            if ((1 and size) == 1) { // it's odd
                headCopy?.let {
                    if (!intIsAPalindrome(it.`val`)) {
                        return false
                    }
                    headCopy = it.next
                }
            }

            // pop stack and iterate headCopy
            while (stackTop != null && headCopy != null) {
                stackTop?.`val`?.let { stackValue ->
                    headCopy?.`val`?.let { headValue ->
                        if (stackValue != headValue) {
                            return false
                        }
                        /*
                            // use this algorithm is the values are
                            // required to be reversed and non-negative
                            // and one of the values cannot end in zero
                        if (!isIntTheReverse(stackValue, headValue)) {
                            return false
                        }
                        */
                    }
                }
                stackTop = stackTop?.next
                headCopy = headCopy?.next
            }
            return true
        }
    }
}