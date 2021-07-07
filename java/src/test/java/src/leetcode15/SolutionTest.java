package src.leetcode15;

import junit.framework.TestCase;

public class SolutionTest extends TestCase {

    public void testHammingWeight() {
        System.out.println(new Solution().hammingWeight(0b111));
    }

    public void testHammingWeight2() {
        System.out.println(new Solution().hammingWeight2(0b1111001111));
    }
}