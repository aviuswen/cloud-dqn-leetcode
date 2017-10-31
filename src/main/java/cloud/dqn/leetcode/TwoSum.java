package cloud.dqn.leetcode;

/**
 *
 * Java Implementation
 * Problem: https://leetcode.com/problems/two-sum/description/
    Given an array of integers, return indices of the two
    numbers such that they add up to a specific target.

    You may assume that each input would have exactly one solution,
    and you may not use the same element twice.

    Example:
    Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
 */

public class TwoSum {
    // solution to provide to leetcode has signature class Solution
    public static class Solution {
        /* Array allocations in java

        int[] myIntArray = new int[3];
        int[] myIntArray = {1,2,3};
        int[] myIntArray = new int[]{1,2,3};

        String[] myStringArray = new String[3];
        String[] myStringArray = {"a","b","c"};
        String[] myStringArray = new String[]{"a","b","c"};

        String[] myStringArray;
        myStringArray = new String[]{"a","b","c"};

         */

        public int[] twoSum(int[] nums, int target) {
            // warning no sanity check for givens
            for (int i = 0; i + 1 < nums.length; i++) {
                for(int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                         return new int[]{i, j};
                    }
                }
            }
            return null;
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        return (new Solution()).twoSum(nums, target);
    }
}
