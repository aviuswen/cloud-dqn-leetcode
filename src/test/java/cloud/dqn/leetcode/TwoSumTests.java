package cloud.dqn.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class TwoSumTests {

    @Test public void fooTest() {
        Assert.assertTrue(true);
    }

    @Test public void exampleTest() {
        /*  indices   0  1  2  3  4   5 */
        int[] nums = {1, 0, 2, 7, 11, 15};
        int target = 9;

        int[] solution = (new TwoSum.Solution()).twoSum(nums, target);
        Assert.assertNotNull(solution);
        Assert.assertTrue(solution.length == 2);

        int solutionFirstValue = solution[0];
        int solutionSecondValue = solution[1];

        Assert.assertTrue(solutionFirstValue == 2);
        Assert.assertTrue(solutionSecondValue == 3);
    }
}
