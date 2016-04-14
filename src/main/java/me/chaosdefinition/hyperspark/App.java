package me.chaosdefinition.hyperspark;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import me.chaosdefinition.hyperspark.common.HypersparkException;
import me.chaosdefinition.hyperspark.core.WirelengthCalculator;
import me.chaosdefinition.hyperspark.optparse.OptionParser;
import me.chaosdefinition.hyperspark.util.MathUtils;

/**
 * Application starting point.
 *
 * @author Chaos Shen
 */
public class App {
	public static void main(String[] args) {
		try {
			/* Parse command line options. */
			OptionParser parser = new OptionParser();
			parser.parse(args);

			/* Get options. */
			int dimension = parser.getDimension();
			int length = parser.getLength();
			String outdir = parser.getOutdir();
			boolean verbose = parser.isVerbose();

			/* Initialize Spark environment. */
			SparkConf conf = new SparkConf().setAppName("hyperspark");
			JavaSparkContext ctx = new JavaSparkContext(conf);

			/*
			 * Generate a list of lexicographic ordered integers, each
			 * representing an index in all permutations of specified length,
			 * and parallelize them to JavaRDD.
			 */
			JavaRDD<Integer> indices = ctx.parallelize(MathUtils.lexicode(length));

			if (verbose) {
				/*
				 * In verbose mode, first convert each index to corresponding
				 * initial permutation, and then construct a
				 * WirelengthCalculator with specified dimension and the initial
				 * permutation, finally call findMinimumMappings() which returns
				 * the result by this permutation.
				 * 
				 * We don't need to reduce all the results since they are
				 * exactly what we want. Save them to the output directory.
				 */
				indices.flatMap(index -> {
					List<Integer> initial = MathUtils.indexToPermutation(index, length);
					WirelengthCalculator calculator = new WirelengthCalculator(dimension, initial);
					calculator.setVerbose(verbose);
					return calculator.findMinimumMappings();
				}).saveAsTextFile(outdir);
			} else {
				/*
				 * Otherwise, we just need to know the minimum wirelength, which
				 * can be acquired by following the above steps but finally
				 * calling calculateMinimumWirelength() instead.
				 * 
				 * Reduce the results by taking the minimum and print the final
				 * minimum.
				 */
				int minimum = indices.map(index -> {
					List<Integer> initial = MathUtils.indexToPermutation(index, length);
					WirelengthCalculator calculator = new WirelengthCalculator(dimension, initial);
					return calculator.calculateMinimumWirelength();
				}).reduce((v1, v2) -> Math.min(v1, v2));
				System.out.println("Minimum: " + minimum);
			}
			ctx.close();
		} catch (Exception e) {
			if (e instanceof HypersparkException) {
				e.printStackTrace();
			} else {
				new HypersparkException(e).printStackTrace();
			}
			System.exit(1);
		}
	}
}
