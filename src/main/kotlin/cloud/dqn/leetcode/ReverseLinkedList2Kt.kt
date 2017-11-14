package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 *
    Reverse a linked list from position m to n. Do it in-place and in one-pass.

    For example:
    Given 1->2->3->4->5->NULL, m = 2 and n = 4,

    return 1->4->3->2->5->NULL.

    Note:
    Given m, n satisfy the following condition:
    1 ≤ m ≤ n ≤ length of list.
 */
class ReverseLinkedList2Kt {
    class ListNode(var `val`: Int = 0) {
        var next: ListNode? = null
    }
    /**
        Algo0:
            Go down the list to land on node(m - 1)
            save off append = node(m-1)
            reverse in place (n - m + 1) nodes getting the new subHead and subTail, and remainingList
            append.next = subHead
            subTail.next = remainingList

        Special case:
            First part is reversal
            last part is reversal
    */
    class Solution {
        private fun reverseUpToAndAppendTheRest(numNodes: Int, node: ListNode?): ListNode? {
            if (node == null || numNodes <= 1) {
                return node
            } else {
                var newHead: ListNode? = null
                var tail: ListNode? = null
                var current = node
                var tempNode: ListNode? = null
                var count = 0
                while (count < numNodes) {
                    tempNode = current
                    current = current?.next
                    if (newHead == null) {
                        newHead = tempNode
                        tail = tempNode
                    } else {
                        tempNode?.next = newHead
                        newHead = tempNode
                    }
                    count++
                }
                tail?.next = current
                return newHead
            }

        }

        fun reverseBetween(head: ListNode?, m: Int, n: Int): ListNode? {
            val numNodesToReverse = (n - m + 1)
            if (head == null || numNodesToReverse <= 1) {
                return head
            } else {
                var nodeBeforeReversing = head
                var count = 1 // 1
                // m = 1
                // *
                // c
                // a->b->c->d-
                while (count < m - 1) {
                    count++
                    nodeBeforeReversing = nodeBeforeReversing?.next
                }

                return if (m == 1) {
                    // special case of starting at beginning
                    reverseUpToAndAppendTheRest(numNodesToReverse, head)
                } else {
                    nodeBeforeReversing?.next = reverseUpToAndAppendTheRest(numNodesToReverse, nodeBeforeReversing?.next)
                    head
                }
            }
        }
    }
}