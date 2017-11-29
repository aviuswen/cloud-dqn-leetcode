package cloud.dqn.leetcode.self

import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.Comparator

class MyHeapTest {
    class Value(val height: Long): Comparator<Long> {
        override fun compare(o1: Long, o2: Long): Int {
            return (o1 - o2).toInt()
        }
    }

    @Test fun foo() {
        val testArr = arrayOf(
                longArrayOf(0,1,2,3,4),
                longArrayOf(4,3,2,1,0),
                longArrayOf(0,1,2,2,2,3),
                longArrayOf(0,1,2,2,2,1,0),
                longArrayOf(6,5,2,2,2,1,0)

        )
        val comparator = Comparator<Value>{ o1, o2 -> (o1.height - o2.height).toInt() }
        val minQueue = PriorityQueue<Value>(comparator)
        val heap = MyHeap<Value>(comparator)
        testArr.forEach { arr ->
            arr.forEach {
                minQueue.add(Value(it))
                heap.push(Value(it))
            }
            arr.forEach {
                val m = minQueue.poll()
                val h = heap.pop()
                if (m.height != h?.height) {
                    println("omg")
                }
                Assert.assertTrue(m.height == h?.height)
            }
        }
        /*
         *    0
         *  1   2
         * 3 4
         *
         * pop 0
         *    4
         *  1   2
         * 3
         */
    }

}