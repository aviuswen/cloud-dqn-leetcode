package cloud.dqn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/description/
 *
    Given a collection of distinct numbers, return all possible permutations.

    For example,
    [1,2,3] have the following permutations:
    [
      [1,2,3],
      [1,3,2],
      [2,1,3],
      [2,3,1],
      [3,1,2],
      [3,2,1]
    ]
 */
public class Permutations {
    class Solution {
        /**
         recurse
            exit case: temp is full, add to result
            other case:
                for each in possible:
                 copyTemp
                 add it to copyTemp
                 reducePossible
                 recurse(copyTemp, reducePossible, result)
         */
        public List<List<Integer>> permute(
                int[] nums
        ) {
            ArrayList<Integer> temp = new ArrayList<>();
            ArrayList<Integer> possible = new ArrayList<>();
            ArrayList<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                possible.add(nums[i]);
            }
            backtrace(temp, possible, res);
            return res;
        }


        private void backtrace(
                ArrayList<Integer> temp,
                ArrayList<Integer> possible,
                ArrayList<List<Integer>> res
        ) {
            if (possible.size() == 0) {
                res.add(temp);
            } else {
                for (Integer p : possible) {
                    ArrayList<Integer> copyTemp = new ArrayList<>(temp);
                    copyTemp.add(p);

                    ArrayList<Integer> copyPossible = new ArrayList<>(possible);
                    copyPossible.remove(p);

                    backtrace(copyTemp, copyPossible, res);
                }
            }
        }
    }
}
