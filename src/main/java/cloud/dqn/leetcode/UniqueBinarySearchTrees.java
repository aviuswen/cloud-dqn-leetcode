package cloud.dqn.leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/description/
 *
    Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

    For example,
    Given n = 3, there are a total of 5 unique BST's.

       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3
 */
public class UniqueBinarySearchTrees {
    class Solution {
        private int numTrees(int n, HashMap<Integer, Integer> numToTotal) {
            if (n <= 1) {
                return 1;
            } else if (numToTotal.containsKey(n)) {
                return numToTotal.get(n);
            }

            int total = 0;
            int tempSize;
            int leftTotal;
            int rightTotal;
            for (int i = 1; i <= n; i++) {
                tempSize = i - 1;
                if (numToTotal.containsKey(tempSize)) {
                    leftTotal = numToTotal.get(tempSize);
                } else {
                    leftTotal = numTrees(tempSize, numToTotal);
                }

                tempSize = n - i;
                if (numToTotal.containsKey(tempSize)) {
                    rightTotal = numToTotal.get(tempSize);
                } else {
                    rightTotal = numTrees(tempSize);
                }

                total += leftTotal * rightTotal;
            }
            numToTotal.put(n, total);
            return total;
        }

        public int numTrees(int n) {
            HashMap<Integer, Integer> nToTotal = new HashMap<>();
            nToTotal.put(0, 1);
            nToTotal.put(1, 1);
            for (int i = 2; i < n; i++) {
                numTrees(i, nToTotal);
            }
            return numTrees(n, nToTotal);
        }
    }
}
