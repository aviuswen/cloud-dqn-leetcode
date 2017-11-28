package cloud.dqn.leetcode

import org.junit.Assert
import org.junit.Test

class LRUCacheTest {
    /**
     *                          g0         g1           g2    g3   g4
     * ["LRUCache","put","put","get","put","get","put","get","get","get"]
     * [[2],       [1,1],[2,2],[1],  [3,3],[2],  [4,4],[1],  [3],  [4]  ]
     *
     * Expected
     * [null,      null, null, 1,    null, -1,   null, -1,   3,    4    ]
     */
    @Test fun leetCode() {
        val cache = LRUCache(2)
        cache.put(1,1)
        cache.put(2,2)
        val g0 = cache.get(1)
        Assert.assertTrue(g0 == 1)
        cache.put(3,3)
        val g1 = cache.get(2)
        Assert.assertTrue(g1 == -1)
        cache.put(4,4)
        val g2 = cache.get(1)
        Assert.assertTrue(g2 == -1)
        val g3 = cache.get(3)
        Assert.assertTrue(g3 == 3)
        val g4 = cache.get(4)
        Assert.assertTrue(g4 == 4)
        println("hi")
    }
    /**
    Input:
                g0          g1                g2    g3
    ["LRUCache","get","put","get","put","put","get","get"]
    [[2],       [2],  [2,6],[1],  [1,5],[1,2],[1],  [2]]

    Expected:
    [null,      -1,   null, -1,   null, null, 2,    6  ]
     */
    @Test fun leetCode2() {
        val cache = LRUCache(2)
        val g0 = cache.get(2)
        Assert.assertTrue(g0 == -1)
        cache.put(2, 6)
        val g1 = cache.get(1)
        Assert.assertTrue(g1 == -1)
        cache.put(1, 5)
        cache.put(1, 2)
        val g2 = cache.get(1)
        Assert.assertTrue(g2 == 2)
        val g3 = cache.get(2)
        Assert.assertTrue(g3 == 6)
    }
}