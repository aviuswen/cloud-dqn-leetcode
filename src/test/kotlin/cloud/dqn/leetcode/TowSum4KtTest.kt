package cloud.dqn.leetcode

import org.junit.Assert
import org.junit.Test

class TowSum4KtTest {
    @Test
    fun findTargetTest() {
        /**
         *
            Input:
                5
               / \
              3   6
             / \   \
            2   4   7
        */
        val root = TwoSum4Kt.TreeNode(5)
        root.left = TwoSum4Kt.TreeNode(3)
        root.left?.left = TwoSum4Kt.TreeNode(2)
        root.left?.right = TwoSum4Kt.TreeNode(4)

        root.right = TwoSum4Kt.TreeNode(6)
        root.right?.right = TwoSum4Kt.TreeNode(7)
        Assert.assertTrue(TwoSum4Kt.Solution().findTarget(root,9))
        Assert.assertFalse(TwoSum4Kt.Solution().findTarget(root,900))

    }
}