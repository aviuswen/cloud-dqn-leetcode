package cloud.dqn.leetcode;

import org.junit.Test;

import java.util.List;

public class NQueensTest {
    @Test
    public void leetcode0() {
        int n = 1;

        NQueens.Solution sl = new NQueens.Solution();
        long start = System.currentTimeMillis();
        List<List<String>> p =  sl.solveNQueens(n);
        long end = System.currentTimeMillis() - start;
        System.out.println((end));

    }
}
