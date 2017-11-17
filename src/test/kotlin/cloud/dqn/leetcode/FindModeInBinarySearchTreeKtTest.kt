package cloud.dqn.leetcode

import org.junit.Test

class FindModeInBinarySearchTreeKtTest {
    @Test
    fun fooo() {
        val root = FindModeInBinarySearchTreeKt.TreeNode(1)
        root.right = FindModeInBinarySearchTreeKt.TreeNode(2)
        root.right?.right = FindModeInBinarySearchTreeKt.TreeNode(2)
        val s = FindModeInBinarySearchTreeKt.Solution()
        val p = s.findMode(root)
    }
    @Test
    fun inOrderTraversalTet() {
        val root = FindModeInBinarySearchTreeKt.TreeNode(1)
        root.right = FindModeInBinarySearchTreeKt.TreeNode(2)
        root.right?.right = FindModeInBinarySearchTreeKt.TreeNode(2)
        val s = FindModeInBinarySearchTreeKt.Solution()
        s.inOrderTraversal(root, body = {
            println(it.`val`)
        })
    }
}