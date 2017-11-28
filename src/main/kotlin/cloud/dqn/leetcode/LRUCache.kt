package cloud.dqn.leetcode

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */

/**
 * https://leetcode.com/problems/lru-cache/discuss/
    Design and implement a data structure for Least Recently Used
    (LRU) cache. It should support the following operations: get and put.

    get(key) - Get the value (will always be positive) of the key if
        the key exists in the cache, otherwise return -1.

    put(key, value) - Set or insert the value if the key is not already
        present. When the cache reached its capacity, it should invalidate
        the least recently used item before inserting a new item.

    Follow up:
        Could you do both operations in O(1) time complexity?

    Algo0
    use hashmap for puts and gets, also to
    reference nodes in double linked list
    use doubly linked list for keeping up the freshness
 */
class LRUCache(capacity: Int) {
    private class Node {
        var key: Int
        var value: Int
        var newer: Node?
        var older: Node?

        constructor(key: Int, value: Int) {
            this.key = key
            this.value = value
            newer = null
            older = null
        }

        override fun equals(other: Any?): Boolean = (other is Node) && (other.key == key) && (other.value == value)
        override fun hashCode(): Int = "$key:$value".hashCode()

        fun nullLinks(): Node {
            newer = null
            older = null
            return this
        }
    }
    private class LRUList {
        private var oldestNode: Node? = null
        private var newestNode: Node? = null

        fun makeNewest(node: Node) {
            remove(node)?.let {
                insertNew(it)
            }
        }

        fun insertNew(node: Node) {
            node.nullLinks()
            if (oldestNode == null && newestNode == null) {
                oldestNode = node
                newestNode = node
            } else {
                val oNode = newestNode
                newestNode = node
                newestNode?.older = oNode
                oNode?.newer = newestNode
            }
        }

        fun removeOldest(): Node? {
            return remove(oldestNode)
        }
        /**
        removes the node from the list and corrects the chains
         */
        private fun remove(node: Node?): Node? {
            if (node != null) {
                val older = node.older
                val newer = node.newer
                newestNode?.let {
                    if (it.equals(node)) {
                        newestNode = node.older
                    }
                }
                oldestNode?.let {
                    if (it.equals(node)) {
                        oldestNode = node.newer
                    }
                }

                older?.newer = newer
                newer?.older = older

                newestNode?.newer = null
                oldestNode?.older = null
            }
            return node?.nullLinks()
        }
    }

    private val keyToNode: HashMap<Int, Node>
    private val list: LRUList
    private val capacity: Int


    // todo handle negative capacity
    init {
        keyToNode = HashMap(capacity)
        list = LRUList()
        this.capacity = capacity
    }

    /**
    get the value and if found
    remove it from list and push it to newest
    @return -1 if not found, value otherwise
     */
    fun get(key: Int): Int {
        val node = keyToNode[key]
        if (node == null) {
            return -1
        } else {
            list.makeNewest(node)
            return node.value
        }
    }

    // if capacity reached evict from oldestNode unless it is already in hash
    fun put(key: Int, value: Int) {
        val existingNode = keyToNode[key]
        if (existingNode != null) {
            existingNode.value = value
            list.makeNewest(existingNode)
        } else {
            val newNode = Node(key, value)
            if (keyToNode.size >= capacity) {
                list.removeOldest()?.let { oldestNode ->
                    keyToNode.remove(oldestNode.key)
                }
            }
            list.insertNew(newNode)
            keyToNode[newNode.key] = newNode
        }
    }

}