package cloud.dqn.leetcode;

/**
 * https://leetcode.com/problems/word-search/description/
    Given a 2D board and a word, find if the word exists in the grid.

    The word can be constructed from letters of sequentially adjacent
    cell, where "adjacent" cells are those horizontally or vertically
    neighboring. The same letter cell may not be used more than once.

    For example,
    Given board =

    [
      ['A','B','C','E'],
      ['S','F','C','S'],
      ['A','D','E','E']
    ]
    word = "ABCCED", -> returns true,
    word = "SEE", -> returns true,
    word = "ABCB", -> returns false.
 */
public class WordSearch {
    class Solution {
        /**
         copy board
         find the first letter
         mark as used
         seek out from there
         */
        private boolean inBoard(char[][] board, int row, int col) {
            return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
        }

        private boolean exist(char[][] board, String word, int index, int row, int col, boolean[][] visited) {
            if (index == word.length()) {
                return true;
            } else if (!inBoard(board, row, col) || visited[row][col] || board[row][col] != word.charAt(index)) {
                return false;
            }

            visited[row][col] = true;
            index++;

            boolean doesExist = exist(board, word, index, row - 1, col, visited) || // up
                    exist(board, word, index, row, col + 1, visited) || // right
                    exist(board, word, index, row + 1, col, visited) || // down
                    exist(board, word, index, row, col - 1, visited); // left

            visited[row][col] = false;

            return doesExist;
        }

        public boolean exist(char[][] board, String word) {
            if (word.length() == 0) {
                return true;
            } else if (board.length == 0 || board[0].length == 0) {
                return false;
            }

            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (exist(board, word, 0, row, col, new boolean[board.length][board[0].length])) {
                        return true;
                    }
                }
            }
            return false;

        }
    }
}
