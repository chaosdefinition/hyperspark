package me.chaosdefinition.hyperspark.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.chaosdefinition.hyperspark.common.HypersparkException;

/**
 * @author Chaos Shen
 *
 */
public class WirelengthCalculator {

	private int dimension;
	private int vertices;
	private List<Integer> initial;
	private int minWirelength;
	private List<List<Integer>> minMappings;

	private boolean verbose = false;

	/**
	 * Determines if two vertices have an edge between them.
	 * 
	 * @param v1
	 *            the integer representation of a vertex
	 * @param v2
	 *            the same as {@code v1}
	 * @return {@code true} if the two vertices have an edge between them,
	 *         otherwise {@code false}
	 */
	public static boolean hasEdge(int v1, int v2) {
		/*
		 * An egde exists if only one bit differs, so we do this check by
		 * determing if their xor sum is a power of two.
		 */
		return ((v1 ^ v2) & ((v1 ^ v2) - 1)) == 0;
	}

	/**
	 * Returns the known minimum wirelength of circular mappings of hypercube of
	 * specified dimension, which typically is the wirelength of the graycode
	 * mapping.
	 * 
	 * @param dimension
	 *            the dimension of the hypercube
	 * @return the known minimum circular wirelength
	 */
	public static int knownMinimumWirelength(int dimension) {
		return 3 * (1 << ((dimension << 1) - 3)) - (1 << (dimension - 1));
	}

	/**
	 * Constructs a {@link WirelengthCalculator} for hypercube of specified
	 * dimension.
	 * 
	 * @param dimension
	 *            the dimension of the hypercube
	 */
	public WirelengthCalculator(int dimension) {
		this(dimension, null);
	}

	/**
	 * Constructs a {@link WirelengthCalculator} for hypercube of specified
	 * dimension with a given initial list to start with.
	 * 
	 * @param dimension
	 *            the dimension of the hypercube
	 * @param initial
	 *            the initial list to start with
	 */
	public WirelengthCalculator(int dimension, List<Integer> initial) {
		this.dimension = dimension;
		this.vertices = 1 << dimension;
		if (initial != null) {
			/* check duplication of each entry */
			boolean[] presence = new boolean[initial.size()];
			for (int i : initial) {
				if (i >= 0 && i < initial.size()) {
					presence[i] = true;
				}
			}
			for (boolean b : presence) {
				if (!b) {
					throw new HypersparkException("Illegal value in initial list!");
				}
			}
			this.initial = new ArrayList<>(initial);
		} else {
			/* [0, 1, 2] will produce all permutations without symmetry */
			this.initial = new ArrayList<>(Arrays.asList(0, 1, 2));
		}

		this.minWirelength = knownMinimumWirelength(dimension);
	}

	/**
	 * Calculates the minimum circular wirelength on all mappings under the
	 * initial list.
	 * 
	 * @return the minimum circular wirelength
	 */
	public int calculateMinimumWirelength() {
		backtrack(this.initial, this.initial.size());
		return this.minWirelength;
	}

	/**
	 * Finds all the mappings of minimum circular wirelength under the initial
	 * list.
	 * 
	 * @return a list of minimum mappings
	 */
	public List<List<Integer>> findMinimumMappings() {
		this.minMappings = new ArrayList<>();
		backtrack(this.initial, this.initial.size());
		return this.minMappings;
	}

	/**
	 * Calculates the complete circular wirelength of a list.
	 * 
	 * @param list
	 *            a list (an initial segment or a complete mapping of hypercube)
	 * @return the complete circular wirelength
	 */
	public int calculateWirelength(List<Integer> list) {
		return calculateWirelength(list, 0, list.size());
	}

	/**
	 * Calculates the partial circular wirelength of a list.
	 * 
	 * @param list
	 *            a list (an initial segment or a complete mapping of hypercube)
	 * @param start
	 *            the starting position in the list
	 * @param end
	 *            the ending position in the list
	 * @return the partial circular wirelength, or -1 when partial wirelength is
	 *         already larger than the current minimum
	 */
	public int calculateWirelength(List<Integer> list, int start, int end) {
		int wirelength = 0;

		for (int i = start; i < end; ++i) {
			for (int j = i + 1; j < end; ++j) {
				if (hasEdge(list.get(i), list.get(j))) {
					if ((j - i) * 2 > this.vertices) {
						wirelength += this.vertices - (j - i);
					} else {
						wirelength += j - i;
					}
					if (wirelength > this.minWirelength) {
						return -1;
					}
				}
			}
		}

		return wirelength;
	}

	/**
	 * Performs backtracking to generate all the mappings under the initial
	 * list.
	 * 
	 * @param list
	 *            a list (an initial segment or a complete mapping of hypercube)
	 * @param element
	 *            next element to add to the list
	 */
	private void backtrack(List<Integer> list, int element) {
		if (element >= this.vertices) {
			this.minWirelength = calculateWirelength(list);
			if (this.verbose) {
				this.minMappings.add(new ArrayList<>(list));
			}
		} else {
			for (int i = 1; i <= list.size(); ++i) {
				list.add(i, element);
				if (calculateWirelength(list) != -1) {
					backtrack(list, element + 1);
				}
				list.remove(i);
			}
		}
	}

	/* getters and setters */
	public int getDimension() {
		return dimension;
	}

	public int getVertices() {
		return vertices;
	}

	public List<Integer> getInitial() {
		return initial;
	}

	public int getMinWirelength() {
		return minWirelength;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
}
