package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/

    Given a Binary Search Tree and a target number,
    return true if there exist two elements in the BST
    such that their sum is equal to the given target.

        Example 1:
        Input:
            5
           / \
          3   6
         / \   \
        2   4   7

        Target = 9

        Output: True
        Example 2:
        Input:
            5
           / \
          3   6
         / \   \
        2   4   7

        Target = 28

        Output: False
 */
class TwoSum4Kt {
    class Solution {
        fun findButNotSelf(root: TreeNode, src: TreeNode, value: Int): TreeNode? {
            var currentNode: TreeNode? = root

            while (currentNode != null) {
                currentNode = when {
                    (value < currentNode.`val`) -> currentNode.left
                    (value > currentNode.`val`) -> currentNode.right
                    else -> {
                        return when {
                            (src !== currentNode) -> currentNode
                            (currentNode.left?.`val` == src.`val`) -> currentNode.left
                            (currentNode.right?.`val` == src.`val`) -> currentNode.right
                            else -> null
                        }
                    }
                }
            }
            return null
        }

        fun iterateInOrder(currentNode: TreeNode?, body: (TreeNode) -> Unit) {
            currentNode?.let {
                it.left?.let { iterateInOrder(it, body) }
                body(it)
                it.right?.let { iterateInOrder(it, body) }
            }
        }

        fun findTarget(root: TreeNode?, k: Int): Boolean {
            var found = false
            root?.let {
                iterateInOrder(it, body = { node ->
                    findButNotSelf(it, node, k - node.`val`)?.let {
                        found = true
                    }
                })
            }
            return found
        }

        fun findTargetFaster(root: TreeNode?, k: Int): Boolean {
            var found = false
            root?.let {
                val targetToNode = HashMap<Int, TreeNode>()
                iterateInOrder(it, body = { currentNode ->
                    targetToNode[k - currentNode.`val`] = currentNode
                } )
                iterateInOrder(it, body = { currentNode ->
                    targetToNode[currentNode.`val`]?.let { targetNode ->
                        if (currentNode !== targetNode) {
                            found = true
                        }
                    }
                })

            }
            return found
        }
    }

    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}