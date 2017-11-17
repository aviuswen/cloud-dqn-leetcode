package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/description/

Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
1
\
2
/
2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 *
 */
class FindModeInBinarySearchTreeKt {
    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    class Solution {
        fun inOrderTraversal(root: TreeNode?, body: (TreeNode) -> Unit) {
            root?.left?.let { inOrderTraversal(it, body) }
            root?.let { body(it) }
            root?.right?.let { inOrderTraversal(it, body) }
        }

        fun findMode(root: TreeNode?): IntArray {
            val valueToCount = HashMap<Int, Int>()
            // 1,2,2
            inOrderTraversal(root, body = {
                valueToCount[it.`val`] = (valueToCount[it.`val`] ?: 0) + 1
            })
            if (valueToCount.isEmpty()) {
                return IntArray(0)
            } else {
                val maxes = ArrayList<Int>()
                var count = 0
                valueToCount.forEach { value, countOfValue ->
                    if (countOfValue > count) {
                        count = countOfValue
                        maxes.clear()
                        maxes.add(value)
                    } else if (countOfValue == count) {
                        maxes.add(value)
                    }
                }
                return maxes.toIntArray()
            }
        }
    }
}