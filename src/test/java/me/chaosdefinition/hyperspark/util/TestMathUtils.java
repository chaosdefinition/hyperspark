package me.chaosdefinition.hyperspark.util;

import static me.chaosdefinition.hyperspark.util.MathUtils.*;

import static org.junit.Assert.*;

import java.util.Arrays;

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
	public final void testFactorial() {
		assertEquals(1, factorial(0));
		assertEquals(1, factorial(1));
		assertEquals(2, factorial(2));
		assertEquals(120, factorial(5));
		assertEquals(40320, factorial(8));
		assertEquals(3628800, factorial(10));
		assertEquals(39916800, factorial(11));
		assertEquals(39916800 * 12, factorial(12));
	}

	/**
	 * Test method for {@link MathUtils#indexToPermutation(int, int)}.
	 */
	@Test
	public final void testIndexToPermutation() {
		assertArrayEquals(Arrays.asList(0, 1, 2).toArray(), indexToPermutation(0, 3).toArray());
		assertArrayEquals(Arrays.asList(0, 3, 1, 2).toArray(), indexToPermutation(0, 4).toArray());
		assertArrayEquals(Arrays.asList(0, 1, 2, 3).toArray(), indexToPermutation(2, 4).toArray());
		assertArrayEquals(Arrays.asList(0, 4, 3, 1, 2).toArray(), indexToPermutation(0, 5).toArray());
		assertArrayEquals(Arrays.asList(0, 1, 3, 2, 4).toArray(), indexToPermutation(7, 5).toArray());
		assertArrayEquals(Arrays.asList(0, 5, 4, 3, 1, 2).toArray(), indexToPermutation(0, 6).toArray());
		assertArrayEquals(Arrays.asList(0, 4, 5, 1, 3, 2).toArray(), indexToPermutation(21, 6).toArray());
		assertArrayEquals(Arrays.asList(0, 5, 1, 3, 4, 2).toArray(), indexToPermutation(30, 6).toArray());
		assertArrayEquals(Arrays.asList(0, 4, 1, 2, 5, 3).toArray(), indexToPermutation(43, 6).toArray());
		assertArrayEquals(Arrays.asList(0, 1, 2, 3, 4, 5).toArray(), indexToPermutation(59, 6).toArray());
	}

	/**
	 * Test method for {@link MathUtils#lexicode(int)}.
	 */
	@Test
	public final void testLexicode() {
		assertArrayEquals(Arrays.asList(0).toArray(), lexicode(1).toArray());
		assertArrayEquals(Arrays.asList(0, 1).toArray(), lexicode(2).toArray());
		assertArrayEquals(Arrays.asList(0, 1, 2, 3).toArray(), lexicode(4).toArray());
		assertArrayEquals(Arrays.asList(0, 1, 2, 3, 4, 5).toArray(), lexicode(6).toArray());
	}
}
