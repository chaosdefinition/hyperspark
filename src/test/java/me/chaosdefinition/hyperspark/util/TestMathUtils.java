package me.chaosdefinition.hyperspark.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for {@link MathUtils}.
 * 
 * @author Chaos Shen
 */
public class TestMathUtils {

	/**
	 * Test method for {@link MathUtils#factorial(int)}.
	 */
	@Test
	public void testFactorial() {
		assertEquals(1, MathUtils.factorial(0));
		assertEquals(1, MathUtils.factorial(1));
		assertEquals(2, MathUtils.factorial(2));
		assertEquals(120, MathUtils.factorial(5));
		assertEquals(40320, MathUtils.factorial(8));
		assertEquals(3628800, MathUtils.factorial(10));
		assertEquals(39916800, MathUtils.factorial(11));
		assertEquals(20922789888000l, MathUtils.factorial(16));
	}

	/**
	 * Test method for {@link MathUtils#indexToPermutation(int, int)}.
	 */
	@Test
	public void testIndexToPermutation() {
		assertArrayEquals(new int[] { 0 }, MathUtils.indexToPermutation(0, 1).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 0, 1 }, MathUtils.indexToPermutation(0, 2).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 1, 0 }, MathUtils.indexToPermutation(1, 2).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 0, 1, 2 },
				MathUtils.indexToPermutation(0, 3).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 0, 2, 1 },
				MathUtils.indexToPermutation(1, 3).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 2, 1, 0 },
				MathUtils.indexToPermutation(5, 3).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 0, 1, 2, 3 },
				MathUtils.indexToPermutation(0, 4).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 1, 3, 0, 2 },
				MathUtils.indexToPermutation(10, 4).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 3, 2, 1, 0 },
				MathUtils.indexToPermutation(23, 4).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 2, 1, 4, 0, 3 },
				MathUtils.indexToPermutation(58, 5).stream().mapToInt(i -> i).toArray());
	}

	/**
	 * Test method for {@link MathUtils#lexicode(int)}.
	 */
	@Test
	public void testLexicode() {
		assertArrayEquals(new int[] { 0 }, MathUtils.lexicode(1).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 0, 1 }, MathUtils.lexicode(2).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 0, 1, 2, 3 }, MathUtils.lexicode(4).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 0, 1, 2, 3, 4, 5 }, MathUtils.lexicode(6).stream().mapToInt(i -> i).toArray());
	}

}
