package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
    Given a sorted array, remove the duplicates in-place such that
    each element appear only once and return the new length.

    Do not allocate extra space for another array, you must do this
    by modifying the input array in-place with O(1) extra memory.

    Example:

    Given nums = [1,1,2],

    Your function should return length = 2, with the first two elements
    of nums being 1 and 2 respectively.
    It doesn't matter what you leave beyond the new length.
 */
class RemoveDuplicatesFromSortedArrayKt {
    class Solution {
        fun removeDuplicates(nums: IntArray): Int {
            if (nums.size <= 1) {
                return nums.size
            }
            // [1,1,2]
            var insertionIndex = 0              // 0,1
            var temp = nums[insertionIndex]     // 2
            var workingIndex = 1                // 1,2
            while(workingIndex < nums.size) {
                val workingIndexValue = nums[workingIndex] // 2
                if (workingIndexValue != temp) {
                    nums[insertionIndex] = temp
                    insertionIndex++
                    temp = nums[workingIndex]
                }
                workingIndex++
            }
            nums[insertionIndex] = temp
            return insertionIndex + 1
        }
    }
}