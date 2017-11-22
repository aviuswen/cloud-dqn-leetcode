package cloud.dqn.leetcode

/**
 * https://leetcode.com/problems/trapping-rain-water/description/
    Given n non-negative integers representing an elevation map where
    the width of each bar is 1, compute how much water it is able to
    trap after raining.

    For example,
    Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
class TrappingRainWaterKt {
    class Solution {
        /**
            Algo0:
            time: O(n^2)
            space: O(1)
            For each bucket {
                Compute the max height of left
                compute the max height of right
                min(maxLeft, maxRight)
                summation(max(min - height, 0)
            }

        */
        private fun max(left: Int, right: Int): Int {
            return if (left < right) right else left
        }

        private fun min(left: Int, right: Int): Int {
            return if (left < right) left else right
        }

        private fun maxLeft(index: Int, arr: IntArray): Int {
            var height = 0
            var leftIndex = index - 1
            while (leftIndex >= 0) {
                height = max(arr[leftIndex], height)
                leftIndex--
            }
            return height
        }

        private fun maxRight(index: Int, arr: IntArray): Int {
            var height = 0
            var rightIndex = index + 1
            while (rightIndex < arr.size) {
                height = max(height, arr[rightIndex])
                rightIndex++
            }
            return height
        }

        private fun heightAt(index: Int, arr: IntArray): Int {
            return arr[index]
        }

        fun trap(height: IntArray): Int {
            /**
            For each bucket {
                Compute the max height of left
                compute the max height of right
                min(maxLeft, maxRight)
                summation(max(min - height, 0)
            }
            */
            var total = 0
            var left = 0
            var right = 0
            var minLeftRight = 0
            height.forEachIndexed { index, indexHeight ->
                left = maxLeft(index, height)
                right = maxRight(index, height)
                minLeftRight = min(left, right)
                if (minLeftRight > indexHeight) {
                    total += (minLeftRight - indexHeight)
                }
            }
            return total
        }

        fun trapFaster(height: IntArray): Int {
            /**
                time: O(n)
                size: O(n)

                maxRight: IntArray = Compute max right for all by walking from right to left
                maxLeft: IntArray = compute max left for all by walking from left to right

                for each index {
                    maxAtIndex = min(maxRight[index], maxLeft[index])
                    if (maxAtIndex > indexHeight) {
                        summation(maxAtIndex - indexHeight)
                    }
                }
             */
            if (height.size <= 2) {
                return 0
            }

            var maxRightArray = IntArray(height.size)
            // maxRightArray[maxRightArray.size - 1] = 0 // defaults at this
            var maxOfTwoOver = height[maxRightArray.size - 1]
            maxRightArray[maxRightArray.size - 2] = maxOfTwoOver
            var i = height.size - 3
            while (i >= 0) {
                maxOfTwoOver = max(height[i + 1], maxOfTwoOver)
                maxRightArray[i] = maxOfTwoOver
                i--
            }

            var maxLeftArray = IntArray(height.size)
            maxLeftArray[1] = height[0]
            maxOfTwoOver = height[0]
            i = 2
            while (i < height.size) {
                maxOfTwoOver = max(height[i - 1], maxOfTwoOver)
                maxLeftArray[i] = maxOfTwoOver
                i++
            }

            var total = 0
            height.forEachIndexed { index, indexHeight ->
                i = min(maxLeftArray[index], maxRightArray[index])
                if (i > indexHeight) {
                    total += (i - indexHeight)
                }
            }
            return total
        }
    }
}