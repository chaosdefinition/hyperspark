package me.chaosdefinition.hyperspark.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.chaosdefinition.hyperspark.common.HypersparkException;

/**
 * Relevant mathematical utilities.
 *
 * @author Chaos Shen
 */
public class MathUtils {

	/**
	 * Generates the factorial of a given integer.
	 * 
	 * @param n
	 *            an integer
	 * @return its factorial
	 */
	public static int factorial(int n) {
		final int[] factorials = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600 };

		if (n >= factorials.length) {
			throw new HypersparkException("Factorial of " + n + "too large!");
		}

		return factorials[n];
	}

	/**
	 * Converts an integer to a permutation of given length without rotation and
	 * flip symmetries.
	 * 
	 * @param index
	 *            an integer ranging from 0 to ((n - 1)! / 2 - 1)
	 * @param length
	 *            the length of the target permutation
	 * @return the target permutation
	 */
	public static List<Integer> indexToPermutation(int index, int length) {
		List<Integer> code = new ArrayList<>(Arrays.asList(0, 1, 2));
		int[] position = new int[length];

		for (int i = length - 1; i >= 3; --i) {
			/* 0 is always fixed, so we need to shift others each by 1 */
			position[i] = index % i + 1;
			index /= i;
		}
		for (int i = 3; i < length; ++i) {
			code.add(position[i], i);
		}

		return code;
	}

	/**
	 * Generates the lexicographic code of given length.
	 * 
	 * @param length
	 *            the length of the code
	 * @return the lexicographic code
	 */
	public static List<Integer> lexicode(int length) {
		List<Integer> code = new ArrayList<>(length);
		for (int i = 0; i < length; ++i) {
			code.add(i);
		}
		return code;
	}
}
