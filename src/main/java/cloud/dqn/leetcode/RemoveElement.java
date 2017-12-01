package cloud.dqn.leetcode;

/**
 *  https://leetcode.com/problems/remove-element/description/
    Given an array and a value, remove all instances of that value
    in-place and return the new length.

    Do not allocate extra space for another array, you must do this
    by modifying the input array in-place with O(1) extra memory.

    The order of elements can be changed. It doesn't matter what you
    leave beyond the new length.
 */
public class RemoveElement {
    class Solution {
        /**
            insertIndex at 0
            walker at 0
            while (walker < nums.size) {
                currentElement = nums[walker]
                if (currentElement is not val) {
                    nums[insertIndex] = currentElement
                    insertIndex++
                }
                walker++
            }
        */
        public int removeElement(int[] nums, int val) {
            int insertIndex = 0;
            int current = 0;
            for (int i = 0; i < nums.length; i++) {
                current = nums[i];
                if (current != val) {
                    nums[insertIndex++] = current;
                }
            }

            return insertIndex;
        }
    }
}
