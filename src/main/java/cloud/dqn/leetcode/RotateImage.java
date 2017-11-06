package cloud.dqn.leetcode;

/**
 * https://leetcode.com/problems/rotate-image/description/
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:
    You have to rotate the image in-place, which means you have to
    modify the input 2D matrix directly. DO NOT allocate another
    2D matrix and do the rotation.
 */
public class RotateImage {
    class Solution {
        public void rotate(int[][] matrix) {
            // sanity check for n x n matrix, account for 1x1 || 0 x 0 for now;
            // remove with algorithm
            if (matrix.length <= 1 || matrix[0].length != matrix.length) {
                return;
            }

        /*
            take each for corner (upperLeft, upperRight, lowerRight, lowerLeft)
            temp = upperLeft
            upperLeft = lowerLeft
            lowerLeft = lowerRight
            lowerRight = upperRight
            upperRight = temp

            [n,n] // n is the index!!!!
            [row][col] -> [n-][]
            upper left -> upperRight :: [a][b] -> [b][n-a] -> [col][n-row]

            upperLeft -> lowerRight :: [a][b] -> [n-a][n-b] -> [n-row][n-col]

            1,2 n-2
            upperLeft -> lowerLeft  :: [a][b] -> [n-b][a] -> [n-col][row]

        */
            int maxIndex = matrix.length - 1;

            for (int row = 0; row < maxIndex; row++) {
                for (int col = row; col < maxIndex - row; col++) {

                    /*
                        int upperLeft = matrix[row][col];
                        int upperRight = matrix[col][maxIndex-row];
                        int lowerRight = matrix[maxIndex-row][maxIndex-col];
                        int lowerLeft = matrix[maxIndex-col][row];

                        matrix[row][col] = lowerLeft; // upperLeft = lowerLeft
                        matrix[maxIndex-col][row] = lowerRight; // lowerLeft = lowerRight
                        matrix[maxIndex-row][maxIndex-col] = upperRight; // lowerRight = upperRight
                        matrix[col][maxIndex-row] = upperLeft; // upperRight = upperLeft
                    */

                    int temp = matrix[row][col]; // upperLeft
                    matrix[row][col] = matrix[maxIndex-col][row]; // upperLeft = lowerLeft
                    matrix[maxIndex-col][row] = matrix[maxIndex-row][maxIndex-col]; // lowerLeft = lowerRight
                    matrix[maxIndex-row][maxIndex-col] = matrix[col][maxIndex-row]; // lowerRight = upperRight
                    matrix[col][maxIndex-row] = temp;// upperRight = upperLeft

                }
            }
        }
    }
}
