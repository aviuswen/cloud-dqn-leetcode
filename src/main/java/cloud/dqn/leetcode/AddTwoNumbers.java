package cloud.dqn.leetcode;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Java Implementation
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

public class AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
            use a carry;
            for each list {
                get the head value and progress it; if head is null; just use zero
                add each head value and add the carry as well
                modulo 10 to get the value to append to result
                divide by 10 to write to carry
            }

        */
            int carryOver = 0;

            ListNode result = null;
            ListNode tail = null;
            // don't want to write over existing values
            ListNode list1 = l1;
            ListNode list2 = l2;
            while (list1 != null || list2 != null) {
                int val1 = 0;
                int val2 = 0;
                if (list1 != null) {
                    val1 = list1.val;
                    list1 = list1.next;
                }
                if (list2 != null) {
                    val2 = list2.val;
                    list2 = list2.next;
                }
                int combinedVal = val1 + val2 + carryOver;
                carryOver = combinedVal / 10;
                ListNode newNode = new ListNode(combinedVal % 10);
                if (result == null) {
                    result = newNode;
                } else {
                    tail.next = newNode;
                }
                tail = newNode;
            }

            // if carryOver != 0; then write the last node
            // assumed that result should not be null as the givens stated that
            // the two lists being added where non empty
            if (carryOver != 0) {
                ListNode lastNode = new ListNode(carryOver);
                tail.next = lastNode;
            }

            return result;
        }
    }

    // not needed but nice to have
    public class Stack {
        ListNode top;
        public Stack() {
            this.top = null; // class attributes default to null, but I want to be verbose
        }
        public Stack(ListNode listNode) {
            this.top = null; // being verbose
            while (listNode != null) {
                push(listNode.val);
            }
        }
        // null on empty rather than throwing an 'EmptyStackException'
        public Integer pop() {
            if (isEmpty()) {
                return null;
            } else {
                int value = top.val;
                top = top.next;
                return value;
            }
        }
        public void push(int val) {
            ListNode listNode = new ListNode(val);
            listNode.next = top;
            top = listNode;
        }
        public boolean isEmpty() {
            return top == null;
        }
    }
}
