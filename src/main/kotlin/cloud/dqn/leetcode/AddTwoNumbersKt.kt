package cloud.dqn.leetcode

/**
 * Kotlin Implementation
 * Problem: https://leetcode.com/problems/add-two-numbers/description/
 * Description:
    You are given two non-empty linked lists representing two non-negative
    integers. The digits are stored in reverse order and each of their
    nodes contain a single digit. Add the two numbers and return it as
    a linked list.

    You may assume the two numbers do not contain any leading zero,
    except the number 0 itself.

    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8


 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class AddTwoNumbersKt {

    class ListNode {
        var value: Int // Kotlin Primative type
        var next: ListNode?
        constructor(x: Int) {
            this.value = x
            this.next = null
        }
    }

    class Solution {
        fun addTwoNumbers(l1: ListNode, l2: ListNode) {
            TODO("finish")
        }
    }
}