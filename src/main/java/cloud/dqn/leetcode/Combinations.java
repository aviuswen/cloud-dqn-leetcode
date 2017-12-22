package cloud.dqn.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/combinations/discuss/
    Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

    For example,
    If n = 4 and k = 2, a solution is:

    [
      [2,4],
      [3,4],
      [2,3],
      [1,2],
      [1,3],
      [1,4],
    ]
 */
public class Combinations {
    class Solution {
        /**
            handle k == 0 case
            nArr = [1...n]
            nArr.forEach {

            }

            recurse:
                temp = [...]
                if (temp.size() == k) {
                    add to result
                } else {
                    for each possible
                        copyTemp and append it
                        copyPossible and remove it
                        recurse(copyTemp, copyPossible, result)
                }
        */
        // 1...n; size == k

        public List<List<Integer>> combine(
            int n,
            int k
        ) {
            ArrayList<List<Integer>> res = new ArrayList<>();
            backtrace(new ArrayList<Integer>(), res, 1, n, k);
            return res;
        }

        private void backtrace(
            ArrayList<Integer> temp,
            ArrayList<List<Integer>> res,
            int start,
            int end,
            int size
        ) {
            if (temp.size() == size) {
                res.add(new ArrayList<>(temp));
            } else {
                for (int i = start; i <= end; i++) {
                    temp.add(i);
                    backtrace(temp, res, i+1, end, size);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }
}
