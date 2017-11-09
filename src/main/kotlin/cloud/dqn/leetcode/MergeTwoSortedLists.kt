package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 * Merge two sorted linked lists and return it as a new list.
 *  The new list should be made by splicing together the nodes
 *  of the first two lists.
 *
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int = 0) {
 *     var next: ListNode? = null
 * }
 */
class MergeTwoSortedLists {
    /**
        // sanity check beforehande; if either null, empty

        Algorithm 0:
            Use current,previous snake method
            current0    -> l1.head
            current1    -> l1.head

            var head: ListNode? = null
            var tail: ListNode? = null // append area

            while (currents != null) {
                // warning: handling case of head == null
                get lowest from each current
                append lowestCurrent to result
                current = lowestCurrent.next
            }

            append the rest of the non-null current to the list

            return head
     */
    class ListNode(var `val`: Int = 0) {
        var next: ListNode? = null
    }
    class Solution {
        fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
            var head: ListNode? = null // (0a)->...
            var tail: ListNode? = null // (0a)->(2c)->(2d)->(3e)->(4b)-|

            var list1 = l1 // (4b)-|
            var list2 = l2 // null

            while (list1 != null && list2 != null) {
                val lowest = if (list1.`val` > list2.`val`) list2 else list1 // (3e)-|

                if (head == null) {
                    head = lowest
                }
                tail?.next = lowest
                tail = lowest


                if (list1.`val` > list2.`val`) {
                    list2 = list2.next
                } else {
                    list1 = list1.next
                }

            }

            tail?.next = list1 ?: list2

            return head ?: l1 ?: l2
        }
    }
}