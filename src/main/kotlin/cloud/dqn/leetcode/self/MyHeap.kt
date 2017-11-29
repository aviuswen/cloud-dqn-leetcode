package cloud.dqn.leetcode.self

import java.util.Comparator

class MyHeap<T> {
    private var heap: ArrayList<T> = ArrayList()
    private var comparator: Comparator<T>

    constructor(comparator: Comparator<T>) {
        this.comparator = comparator
    }

    // add to the end of the tree; heapUp
    fun push(t: T) {
        heap.add(t)
        heapUp(heap.size - 1)
    }

    // copy element from 0; switch last to top; heapDown
    fun pop(): T? {
        if (heap.isEmpty()) {
            return null
        }
        swap(0, heap.size - 1)
        val result = heap.removeAt(heap.size - 1)
        heapDown(0)
        return result
    }

    private fun heapUp(index: Int) {
        var i = index
        var parentI = parentIndex(i)
        var parent = parentI?.let { heap[it] }
        // possible boolean error (>= 0 ? instead)
        while (parent != null && comparator.compare(parent, heap[i]) > 0) {
            // swap
            parentI = parentI?.let {
                swap(it, i)
                i = it
                parentIndex(it)
            }
            parent = parentI?.let { heap[it] }
        }
    }

    private fun heapDown(index: Int) {
        var i = index
        var lowest = lowestOfChildren(i)
        while (lowest != null && comparator.compare(heap[i], heap[lowest]) > 0) {
            swap(i, lowest)
            i = lowest
            lowest = lowestOfChildren(i)
        }
    }

    private fun lowestOfChildren(index: Int?): Int? {
        return index?.let {
            childLeft(index)?.let { lowestIndex ->
                var result = lowestIndex
                childRight(index)?.let { rightIndex ->
                    // possible boolean error (<= 0 ? instead)
                    if (comparator.compare(heap[lowestIndex], heap[rightIndex]) > 0) {
                        result = rightIndex
                    }
                }
                result
            }
        }
    }

    /**
     *                0
     *            /     \
     *           1       2
     *          / \     /  \
     *         3  4     5   6
     *       7 8 9 10
     */
    private fun parentIndex(index: Int): Int? {
        val i = (index - 1) / 2
        return if (inHeap(i)) i else null
    }

    private fun childLeft(index: Int): Int? {
        val i = index * 2 + 1
        return if (inHeap(i)) i else null
    }

    private fun childRight(index: Int): Int? {
        val i = index * 2 + 2
        return if (inHeap(i)) i else null
    }

    private fun inHeap(index: Int) = (index >= 0 && index < heap.size)

    private fun swap(i: Int, j: Int) {
        val temp = heap[i]
        heap[i] = heap[j]
        heap[j] = temp
    }

    companion object {
        val DEFAULT_CAPACITY = 10


    }
}