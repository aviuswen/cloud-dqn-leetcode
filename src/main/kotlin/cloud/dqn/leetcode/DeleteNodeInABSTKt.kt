package cloud.dqn.leetcode
/**
 * https://leetcode.com/problems/delete-node-in-a-bst/description/
    Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

    Basically, the deletion can be divided into two stages:

    Search for a node to remove.
    If the node is found, delete the node.
    Note: Time complexity should be O(height of tree).

    Example:

    root = [5,3,6,2,4,null,7]
    key = 3

        5
       / \
      3   6
     / \   \
    2   4   7

    Given key to delete is 3. So we find the node with value 3 and delete it.

    One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

        5
       / \
      4   6
     /     \
    2       7

    Another valid answer is [5,2,6,null,4,null,7].

        5
       / \
      2   6
       \   \
        4   7
 */
class DeleteNodeInABSTKt {
    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    class Solution {
        data class NodeAndParent (
                val node: TreeNode?,
                val parent: TreeNode?
        )
        private fun findNode(root: TreeNode?, key: Int): NodeAndParent {
            var parent: TreeNode? = null
            var node: TreeNode? = root
            var nodeValue = 0
            while (node != null) {
                nodeValue = node.`val`
                if (key == nodeValue) {
                    break
                } else if (key < nodeValue) {
                    parent = node
                    node = node.left
                } else {
                    parent = node
                    node = node.right
                }
            }
            return if (node == null) {
                NodeAndParent(node, null)
            } else {
                NodeAndParent(node, parent)
            }
        }

        fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
            val (foundNode, parent) = findNode(root, key)
            if (foundNode != null) {
                var rightBranch = foundNode.right
                var leftBranch = foundNode.left

                val newSubHead = if (rightBranch == null) {
                    leftBranch
                } else if (leftBranch == null) {
                    rightBranch
                } else {
                    var greatestOfLeftBranch = leftBranch
                    while (greatestOfLeftBranch?.right != null) {
                        greatestOfLeftBranch = greatestOfLeftBranch.right
                    }
                    greatestOfLeftBranch?.right = foundNode.right
                    leftBranch
                }

                if (parent == null) {
                    return newSubHead // remove the root
                } else if (parent.left?.`val` == key) {
                    parent.left = newSubHead // replace the left node of parent
                } else {
                    parent.right = newSubHead  // replace the right node of parent
                }
                    String
            }
            return root
        }
    }
}