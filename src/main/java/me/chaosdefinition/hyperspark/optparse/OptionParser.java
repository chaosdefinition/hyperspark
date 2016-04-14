package me.chaosdefinition.hyperspark.optparse;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import me.chaosdefinition.hyperspark.common.HypersparkException;

/**
 * A command line argument parser for hyperspark.
 *
 * @author Chaos Shen
 */
public class OptionParser {

	@Option(name = "-d",
			aliases = "--dimension",
			metaVar = "DIM",
			usage = "specify dimension",
			required = true)
	private int dimension;

	@Option(name = "-l",
			aliases = "--length",
			metaVar = "LENGTH",
			usage = "specify the length of initial permutation",
			required = true)
	private int length;

	@Option(name = "-o",
			aliases = "--outdir",
			metaVar = "DIR",
			usage = "specify the directory to contain output",
			depends = "-v")
	private String outdir = "./result";

	@Option(name = "-v",
			aliases = "--verbose",
			usage = "verbose mode")
	private boolean verbose = false;

	@Option(name = "-h",
			aliases = "--help",
			usage = "show this message and exit",
			help = true)
	private boolean help;

	/**
	 * Parses the command line arguments and stores the results in its fields.
	 *
	 * @param args
	 *            the original command line arguments
	 */
	public void parse(String[] args) {
		CmdLineParser parser = new CmdLineParser(this);

		try {
			parser.parseArgument(args);
			if (help) {
				System.out.println("Available options:");
				System.out.println();
				parser.printUsage(System.out);
				System.exit(0);
			}
		} catch (CmdLineException e) {
			throw new HypersparkException("Failed to parse cmd line!", e);
		}
	}

	public int getDimension() {
		return dimension;
	}

	public int getLength() {
		return length;
	}

	public String getOutdir() {
		return outdir;
	}

	public boolean isVerbose() {
		return verbose;
	}
}
