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
		assertEquals(39916800 * 12, MathUtils.factorial(12));
	}

	/**
	 * Test method for {@link MathUtils#indexToPermutation(int, int)}.
	 */
	@Test
	public void testIndexToPermutation() {
		assertArrayEquals(new int[] { 0, 1, 2 },
				MathUtils.indexToPermutation(0, 3).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 3, 1, 2 },
				MathUtils.indexToPermutation(0, 4).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 1, 2, 3 },
				MathUtils.indexToPermutation(2, 4).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 4, 3, 1, 2 },
				MathUtils.indexToPermutation(0, 5).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 1, 3, 2, 4 },
				MathUtils.indexToPermutation(7, 5).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 5, 4, 3, 1, 2 },
				MathUtils.indexToPermutation(0, 6).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 4, 5, 1, 3, 2 },
				MathUtils.indexToPermutation(21, 6).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 5, 1, 3, 4, 2 },
				MathUtils.indexToPermutation(30, 6).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 4, 1, 2, 5, 3 },
				MathUtils.indexToPermutation(43, 6).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 1, 2, 3, 4, 5 },
				MathUtils.indexToPermutation(59, 6).stream().mapToInt(i -> i).toArray());
	}

	/**
	 * Test method for {@link MathUtils#lexicode(int)}.
	 */
	@Test
	public void testLexicode() {
		assertArrayEquals(new int[] { 0 },
				MathUtils.lexicode(1).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 1 },
				MathUtils.lexicode(2).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 1, 2, 3 },
				MathUtils.lexicode(4).stream().mapToInt(i -> i).toArray());

		assertArrayEquals(new int[] { 0, 1, 2, 3, 4, 5 },
				MathUtils.lexicode(6).stream().mapToInt(i -> i).toArray());
	}

}
