package cloud.dqn.leetcode;

import java.util.HashSet;

/**
 * "Problem from after lunch"
 * Designer: JN
 */
public class CoinsFromEarlyFall {
    public static void main(String[] args) {
        int[] coinValues = new int[]{5, 25, 50, 100};
        int[] quantities = new int[]{4,  3,  2,   1};
        int possibles = possibleSums(coinValues, quantities);
        System.out.println(possibles);
    }
    /**
     * Example coinValues = [5, 10, 25], quantity = [1, 3, 2]
     * Calulate the number of possible sums
     * so
     *  coins in hand == [5, 10, 10, 10, 25, 25]
     *  possibles = [
     *      0,
     *      (5), (5,10), (5, 10, 10), (5, 10, 10, 25), (5, 10, 10, 25, 25),
     *          (5, 25), (5, 25, 25), (5, 10) ...
     *      ...
     *      ]
     *  return possibles.uniqueCount
     * @param coinValues value of each coin
     * @param quantity number of coins
     */
    public static int possibleSums(int[] coinValues, int[] quantity) {
        if (coinValues.length == 0 || quantity.length == 0 || coinValues.length != quantity.length) {
            return 0;
        }
        int[] allCoins = createFullCoins(coinValues, quantity);
        HashSet<Integer> totals = new HashSet<>();
        possibleSumsHelper(0, allCoins, totals);
        return totals.size();
    }

    private static int[] createFullCoins(int[] coinValues, int[] quantity) {
        int totalSize = 0;
        for (int i = 0; i < quantity.length; i++) {
            totalSize += quantity[i];
        }
        int[] allCoins = new int[totalSize];
        int allCoinsInsertIndex = 0;
        for (int i = 0; i < coinValues.length; i++) {
            for (int j = 0; j < quantity[i]; j++) {
                allCoins[allCoinsInsertIndex] = coinValues[i];
                allCoinsInsertIndex++;
            }
        }
        return allCoins;
    }

    private static void possibleSumsHelper(int total, int[] arr, HashSet<Integer> totals) {
        totals.add(total);
        if (arr.length != 0) {
            for (int i = 0; i < arr.length; i++) {
                possibleSumsHelper(
                        total + arr[i],
                        cloneAndRemoveSingleAt(i, arr),
                        totals
                );
            }
        }
    }

    private static int[] cloneAndRemoveSingleAt(int index, int[] arr) {
        if (index < 0 || index >= arr.length) {
            return null;
        }
        int[] result = new int[arr.length - 1];
        int insertionIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                result[insertionIndex] = arr[i];
                insertionIndex++;
            }
        }
        return result;
    }
}
