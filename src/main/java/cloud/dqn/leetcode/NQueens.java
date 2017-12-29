package cloud.dqn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/description/
 *
    The n-queens puzzle is the problem of placing n queens on an
    n×n chessboard such that no two queens attack each other.

    Given an integer n, return all distinct solutions to the
    n-queens puzzle.

    Each solution contains a distinct board configuration of the
    n-queens' placement, where 'Q' and '.' both indicate a queen
    and an empty space respectively.

    For example,
    There exist two distinct solutions to the 4-queens puzzle:

    [
     [".Q..",  // Solution 1
      "...Q",
      "Q...",
      "..Q."],

     ["..Q.",  // Solution 2
      "Q...",
      "...Q",
      ".Q.."]
    ]
 */
public class NQueens {
    public static class Solution {

        private static final char QUEEN = 'Q';
        private static final char OPEN = '\u0000';
        private static final char CLOSED = 'X';

        class Board {
            private int numQueens;
            private int size;
            private char[][] board;

            public Board(int n) {
                this.numQueens = 0;
                this.size = n;
                this.board = new char[n][n];
            }

            public Board(Board b) {
                this.numQueens = b.numQueens;
                this.size = b.size;
                this.board = new char[b.size][b.size];
                for (int row = 0; row < size; row++) {
                    for (int col = 0; col < size; col++) {
                        this.board[row][col] = b.board[row][col];
                    }
                }
            }

            public boolean isSolved() {
                return numQueens == size;
            }

            public int size() {
                return size;
            }

            public ArrayList<String> toArrayString() {
                char[] str = new char[size];
                ArrayList<String> result = new ArrayList<>(size);
                for (int row = 0; row < size; row++) {
                    for (int col = 0; col < size; col++) {
                        if (board[row][col] == QUEEN) {
                            str[col] = QUEEN;
                        } else {
                            str[col] = '.';
                        }
                    }
                    result.add(new String(str));
                }
                return result;
            }

            /**
             * @return a copy of the previous board or null if no queen can be placed

            XXXXXX
            XXXXXX
            XXXQXX  2,3
            XXXXXX
            XXXXXX
            XXXXXX
             */
            public Board placeQueen(int row, int col) {
                if (isQueenPlaceable(row, col)) {
                    Board copy = new Board(this);
                    placeQueen(row, col, true);
                    return copy;
                }
                return null;
                //return placeQueen(row, col, true);
            }

            private boolean placeQueen(int row, int col, boolean force) {
                if (force || isQueenPlaceable(row, col)) {
                    board[row][col] = QUEEN;
                    replaceRow(row, OPEN, CLOSED);
                    replaceColumn(col, OPEN, CLOSED);
                    replaceDiagonals(row, col, OPEN, CLOSED);
                    numQueens++;
                    return true;
                }
                return false;
            }

            private boolean isQueenPlaceable(int row, int col) {
                return inBoard(row, col) && locationOpen(row, col);
            }

            // Warning, no bounds checking
            private void replaceDiagonals(int row, int col, char match, char replace) {
                int tempRow = row - 1;
                int tempCol = col + 1;
                // upperRight
                while (tempRow >= 0 && tempCol < size) {
                    if (board[tempRow][tempCol] == match) {
                        board[tempRow][tempCol] = replace;
                    }
                    tempRow--;
                    tempCol++;
                }

                tempRow = row + 1;
                tempCol = col + 1;
                // lowerRight
                while (tempRow < size && tempCol < size) {
                    if (board[tempRow][tempCol] == match) {
                        board[tempRow][tempCol] = replace;
                    }
                    tempRow++;
                    tempCol++;
                }

                tempRow = row + 1;
                tempCol = col - 1;
                // lowerLeft
                while (tempRow < size && tempCol >= 0) {
                    if (board[tempRow][tempCol] == match) {
                        board[tempRow][tempCol] = replace;
                    }
                    tempRow++;
                    tempCol--;
                }

                tempRow = row - 1;
                tempCol = col - 1;
                // upperLeft
                while (tempRow >= 0 && tempCol >= 0) {
                    if (board[tempRow][tempCol] == match) {
                        board[tempRow][tempCol] = replace;
                    }
                    tempRow--;
                    tempCol--;
                }
            }

            // Warning, no bounds checking
            private void replaceRow(int row, char match, char replace) {
                for (int i = 0; i < size; i++) {
                    if (board[row][i] == match) {
                        board[row][i] = replace;
                    }
                }
            }

            // Warning, no bounds checking
            private void replaceColumn(int col, char match, char replace) {
                for (int i = 0; i < size; i++) {
                    if (board[i][col] == match) {
                        board[i][col] = replace;
                    }
                }
            }

            // Warning, no bounds checking
            private boolean locationOpen(int row, int col) {
                return board[row][col] == OPEN;
            }

            private boolean inBoard(int row, int col) {
                return row >= 0 && row < size && col >= 0 && col < size;
            }
        }

        private void solveNQueens(Board board, ArrayList<List<String>> result, int row, int col) {
            if (board.isSolved()) {
                result.add(board.toArrayString());
            } else {
                int size = board.size();
                Board oldBoard;

                for (; row < size; row++) {
                    for (; col < size; col++) {
                        oldBoard = board.placeQueen(row, col);
                        if (oldBoard != null) {
                            solveNQueens(board, result, row + 1, 0);
                            board = oldBoard;
                        }
                    }
                }

            }
        }

        public List<List<String>> solveNQueens(int n) {
            if (n < 0) {
                return null;
            } else if (n == 0) {
                return new ArrayList<List<String>>();
            }

            ArrayList<List<String>> result = new ArrayList<>();
            solveNQueens(new Board(n), result, 0, 0);
            return result;
        }
    }
}
