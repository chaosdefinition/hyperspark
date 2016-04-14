package me.chaosdefinition.hyperspark.util;

import java.util.ArrayList;
import java.util.List;

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
	public static long factorial(int n) {
		/* pre-calculated values to speed up calculation */
		final int[] factorials = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800 };

		if (n < factorials.length) {
			return factorials[n];
		} else {
			long product = factorials[factorials.length - 1];
			for (; n >= factorials.length; --n) {
				product *= n;
			}
			return product;
		}
	}

	/**
	 * Converts an integer to a permutation of given length in lexicographic
	 * order.
	 * 
	 * @param index
	 *            an integer ranging from 0 to (n! - 1)
	 * @param length
	 *            the length of the target permutation
	 * @return the target permutation
	 */
	public static List<Integer> indexToPermutation(int index, int length) {
		List<Integer> permutation = new ArrayList<>(length);
		List<Integer> lexicode = lexicode(length);
		for (int i = length - 1; i >= 0; --i) {
			long factorial = factorial(i);
			permutation.add(lexicode.remove((int) (index / factorial)));
			index %= factorial;
		}
		return permutation;
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
