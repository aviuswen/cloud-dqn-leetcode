package cloud.dqn.leetcode;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/description/
    Remove all elements from a linked list of integers that have
    value val.

    Example
    Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
    Return: 1 --> 2 --> 3 --> 4 --> 5
 */
public class RemovedLinkedListElements {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            ListNode previous = null;
            ListNode current = head;
            head = null;
            ListNode temp = null;
            while (current != null) {
                if (current.val == val) {
                    if (previous != null) {
                        previous.next = current.next;
                    }
                    temp = current.next;
                    current.next = null;
                    current = temp;
                } else {
                    if (previous == null) {
                        head = current;
                    }
                    previous = current;
                    current = current.next;
                }
            }
            return head;
        }
    }
}
