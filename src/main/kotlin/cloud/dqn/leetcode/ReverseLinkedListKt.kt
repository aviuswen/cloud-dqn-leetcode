package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/reverse-linked-list/description/

    Reverse a singly linked list.

 */

class ReverseLinkedListKt {
    class ListNode(var `val`: Int = 0) {
        var next: ListNode? = null
    }
    /**
        Algo0:
            Reverse using single iteration by keeping a
            reference to a New Head and inserting the current
            node to the New Head and moving current
            down the original list
    */
    class Solution {
        fun reverseList(head: ListNode?): ListNode? {
            var newHead: ListNode? = null
            var current: ListNode? = head
            while (current != null) {
                val temp = current
                current = current.next
                temp.next = newHead
                newHead = temp
            }
            return newHead
        }
    }
}