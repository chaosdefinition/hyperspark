package me.chaosdefinition.hyperspark.core;

import static me.chaosdefinition.hyperspark.core.WirelengthCalculator.*;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * Unit tests for {@link WirelengthCalculator}.
 *
 * @author Chaos Shen
 */
public class TestWirelengthCalculator {

	/**
	 * Test method for {@link WirelengthCalculator#hasEdge(int, int)}.
	 */
	@Test
	public final void testHasEdge() {
		/* true cases */
		assertTrue(hasEdge(0, 1));
		assertTrue(hasEdge(0, 2));
		assertTrue(hasEdge(0, 4));
		assertTrue(hasEdge(0, 8));
		assertTrue(hasEdge(1, 3));
		assertTrue(hasEdge(1, 5));
		assertTrue(hasEdge(2, 6));
		assertTrue(hasEdge(3, 11));
		assertTrue(hasEdge(4, 12));

		/* false cases */
		assertFalse(hasEdge(0, 0));
		assertFalse(hasEdge(1, 1));
		assertFalse(hasEdge(1, 2));
		assertFalse(hasEdge(1, 4));
		assertFalse(hasEdge(2, 4));
		assertFalse(hasEdge(2, 5));
		assertFalse(hasEdge(2, 7));
		assertFalse(hasEdge(3, 5));
		assertFalse(hasEdge(3, 9));
	}

	/**
	 * Test method for {@link WirelengthCalculator#knownMinimumWirelength(int)}.
	 */
	@Test
	public final void testKnownMinimumWirelength() {
		assertEquals(0, knownMinimumWirelength(0));
		assertEquals(1, knownMinimumWirelength(1));
		assertEquals(4, knownMinimumWirelength(2));
		assertEquals(20, knownMinimumWirelength(3));
		assertEquals(88, knownMinimumWirelength(4));
		assertEquals(368, knownMinimumWirelength(5));
	}

	/**
	 * Test method for {@link WirelengthCalculator#calculateMinimumWirelength()}
	 * .
	 */
	@Test
	public final void testCalculateMinimumWirelength() {
		assertEquals(4, new WirelengthCalculator(2).calculateMinimumWirelength());
		assertEquals(20, new WirelengthCalculator(3).calculateMinimumWirelength());
		assertEquals(88, new WirelengthCalculator(4, Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 8, 9, 10, 11))
				.calculateMinimumWirelength());
	}

	/**
	 * Test method for {@link WirelengthCalculator#findMinimumMappings()}.
	 */
	@Test
	public final void testFindMinimumMappings() {
		assertArrayEquals(Arrays.asList(Arrays.asList(0, 1, 3, 2)).toArray(),
				new WirelengthCalculator(2).findMinimumMappings().toArray());
		assertArrayEquals(
				Arrays.asList(Arrays.asList(0, 6, 4, 7, 5, 3, 1, 2), Arrays.asList(0, 6, 4, 5, 7, 3, 1, 2),
						Arrays.asList(0, 4, 6, 7, 5, 3, 1, 2), Arrays.asList(0, 4, 6, 5, 7, 3, 1, 2),
						Arrays.asList(0, 3, 1, 7, 5, 6, 4, 2), Arrays.asList(0, 3, 1, 5, 7, 6, 4, 2),
						Arrays.asList(0, 3, 1, 7, 5, 4, 6, 2), Arrays.asList(0, 3, 1, 5, 7, 4, 6, 2),
						Arrays.asList(0, 6, 4, 7, 5, 1, 3, 2), Arrays.asList(0, 6, 4, 5, 7, 1, 3, 2),
						Arrays.asList(0, 4, 6, 7, 5, 1, 3, 2), Arrays.asList(0, 4, 6, 5, 7, 1, 3, 2),
						Arrays.asList(0, 4, 5, 1, 7, 3, 6, 2), Arrays.asList(0, 4, 5, 1, 3, 7, 6, 2),
						Arrays.asList(0, 4, 5, 1, 7, 3, 2, 6), Arrays.asList(0, 4, 5, 1, 3, 7, 2, 6),
						Arrays.asList(0, 4, 1, 5, 7, 3, 6, 2), Arrays.asList(0, 4, 1, 5, 3, 7, 6, 2),
						Arrays.asList(0, 4, 1, 5, 7, 3, 2, 6), Arrays.asList(0, 4, 1, 5, 3, 7, 2, 6),
						Arrays.asList(0, 1, 5, 4, 7, 6, 3, 2), Arrays.asList(0, 1, 5, 4, 6, 7, 3, 2),
						Arrays.asList(0, 1, 4, 5, 7, 6, 3, 2), Arrays.asList(0, 1, 4, 5, 6, 7, 3, 2),
						Arrays.asList(0, 1, 3, 7, 5, 6, 4, 2), Arrays.asList(0, 1, 3, 5, 7, 6, 4, 2),
						Arrays.asList(0, 1, 3, 7, 5, 4, 6, 2), Arrays.asList(0, 1, 3, 5, 7, 4, 6, 2),
						Arrays.asList(0, 5, 1, 7, 3, 6, 2, 4), Arrays.asList(0, 5, 1, 3, 7, 6, 2, 4),
						Arrays.asList(0, 5, 1, 7, 3, 2, 6, 4), Arrays.asList(0, 5, 1, 3, 7, 2, 6, 4),
						Arrays.asList(0, 1, 5, 7, 3, 6, 2, 4), Arrays.asList(0, 1, 5, 3, 7, 6, 2, 4),
						Arrays.asList(0, 1, 5, 7, 3, 2, 6, 4), Arrays.asList(0, 1, 5, 3, 7, 2, 6, 4),
						Arrays.asList(0, 1, 3, 2, 7, 6, 5, 4), Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4),
						Arrays.asList(0, 1, 3, 2, 7, 6, 4, 5), Arrays.asList(0, 1, 3, 2, 6, 7, 4, 5),
						Arrays.asList(0, 1, 5, 4, 7, 6, 2, 3), Arrays.asList(0, 1, 5, 4, 6, 7, 2, 3),
						Arrays.asList(0, 1, 4, 5, 7, 6, 2, 3), Arrays.asList(0, 1, 4, 5, 6, 7, 2, 3),
						Arrays.asList(0, 1, 2, 3, 7, 6, 5, 4), Arrays.asList(0, 1, 2, 3, 6, 7, 5, 4),
						Arrays.asList(0, 1, 2, 3, 7, 6, 4, 5), Arrays.asList(0, 1, 2, 3, 6, 7, 4, 5)).toArray(),
				new WirelengthCalculator(3).findMinimumMappings().toArray());
		assertArrayEquals(
				Arrays.asList(Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 15, 14, 13, 12, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 14, 15, 13, 12, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 15, 13, 14, 12, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 13, 15, 14, 12, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 15, 13, 12, 14, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 13, 15, 12, 14, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 13, 12, 15, 14, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 13, 12, 14, 15, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 15, 14, 12, 13, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 14, 15, 12, 13, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 14, 12, 15, 13, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 14, 12, 13, 15, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 12, 14, 15, 13, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 12, 14, 13, 15, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 8, 9, 10, 11),
						Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 14, 15, 8, 9, 10, 11)).toArray(),
				new WirelengthCalculator(4, Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 8, 9, 10, 11)).findMinimumMappings()
						.toArray());
	}

	/**
	 * Test method for
	 * {@link WirelengthCalculator#calculateWirelength(java.util.List)}.
	 */
	@Test
	public final void testCalculateWirelengthListOfInteger() {
		assertEquals(4, new WirelengthCalculator(2).calculateWirelength(Arrays.asList(0, 1, 3, 2)));
		assertEquals(-1, new WirelengthCalculator(2).calculateWirelength(Arrays.asList(0, 3, 1, 2)));
		assertEquals(20, new WirelengthCalculator(3).calculateWirelength(Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4)));
		assertEquals(-1, new WirelengthCalculator(3).calculateWirelength(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)));
		assertEquals(88, new WirelengthCalculator(4)
				.calculateWirelength(Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8)));
		assertEquals(-1, new WirelengthCalculator(4)
				.calculateWirelength(Arrays.asList(0, 3, 6, 9, 10, 14, 7, 4, 1, 2, 8, 11, 13, 12, 15, 5)));
	}

	/**
	 * Test method for
	 * {@link WirelengthCalculator#calculateWirelength(java.util.List, int, int)}
	 * .
	 */
	@Test
	public final void testCalculateWirelengthListOfIntegerIntInt() {
		assertEquals(4, new WirelengthCalculator(2).calculateWirelength(Arrays.asList(0, 1, 3, 2), 0, 4));
		assertEquals(1, new WirelengthCalculator(2).calculateWirelength(Arrays.asList(0, 1, 3, 2), 1, 3));
		assertEquals(-1, new WirelengthCalculator(2).calculateWirelength(Arrays.asList(0, 3, 1, 2), 0, 4));
		assertEquals(3, new WirelengthCalculator(2).calculateWirelength(Arrays.asList(0, 3, 1, 2), 1, 4));
		assertEquals(20, new WirelengthCalculator(3).calculateWirelength(Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4), 0, 8));
		assertEquals(7, new WirelengthCalculator(3).calculateWirelength(Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4), 2, 7));
		assertEquals(-1, new WirelengthCalculator(3).calculateWirelength(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7), 0, 8));
		assertEquals(8, new WirelengthCalculator(3).calculateWirelength(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7), 2, 7));
	}
}
